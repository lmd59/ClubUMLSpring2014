/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.Diagram;
import domain.DiagramPolicyScore;
import domain.Report;
import compareAlgorithm.CompareDiagrams;
import compareAlgorithm.DiagramCompare;
import compareAlgorithm.smartPolicy.PolicyScoreGenerator;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;

import controller.diagramparser.DiagramParser;
import controller.diagramparser.DiagramParserFactory;

import repository.CompareDAO;
import repository.DiagramDAO;
import repository.PolicyDAO;
import repository.RationaleDAO;
import repository.ReportDAO;


/**
 * 
 * @author Pratham
 */

/**
 * Information class that contains all the features of one Compare
 * @ doc author	Rui Hou
 */

@WebServlet(name = "Compare", urlPatterns = { "/Compare" })
public class Compare extends HttpServlet {

	private int diagramID1;
	private int diagramID2;
	private String diagram1RealPath;
	private String diagram2RealPath;
	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = this.getServletContext();

		this.diagramID1 = Integer.parseInt(request.getParameter("file1"));
		this.diagramID2 = Integer.parseInt(request.getParameter("file2"));

		Diagram diagram1 = DiagramDAO.getDiagram(this.diagramID1);
		Diagram diagram2 = DiagramDAO.getDiagram(this.diagramID2);
		
		String path = "";
		String reportText = "";
		
		//Lauren DiCristofaro: the below should probably be moved to find servlet context in which diagrams were initially created
		diagram1.setDiagramRealPath(context.getRealPath(diagram1.getFilePath()));
		diagram2.setDiagramRealPath(context.getRealPath(diagram2.getFilePath()));
		// setting context Paths for Diagrams
		diagram1.setConPath(context.getRealPath("/").toString());	
		diagram2.setConPath(context.getRealPath("/").toString());
		
		if(request.getParameter("smartsuggest") != null && 
				request.getParameter("smartsuggest").equals("Suggest Promote"))
		{
			// your code here

			DiagramParserFactory factory = new DiagramParserFactory();
 			DiagramParser diag1Parser = DiagramParserFactory.getDiagramParser(diagram1);
			DiagramParser diag2Parser = DiagramParserFactory.getDiagramParser(diagram2);
			PolicyScoreGenerator scorer = new PolicyScoreGenerator();
			try {
				DiagramPolicyScore diagram1Score = scorer.generateScore(PolicyDAO.getPolicy(diagramID1), diag1Parser);
				DiagramPolicyScore diagram2Score = scorer.generateScore(PolicyDAO.getPolicy(diagramID2), diag2Parser);
				//String justification = "Adding Suggestion Remarks";
				String justification = "";
				if(diagram1Score.getPolicyScore() > diagram2Score.getPolicyScore())
					justification = "Diagram 2 is preferred!! \n";
				else if (diagram1Score.getPolicyScore() < diagram2Score.getPolicyScore())
					justification = "Diagram 1 is preferred!! \n";
				else
					justification = "Diagram 1 nad Diagram 2 are equally preferred!! \n ";
				justification += "<b>SCORING DETAILS: </b> \n \n";	
				justification += "<b>Diagram1:</b> Score:" + diagram1Score.getPolicyScore() + "\n";
				justification +=	diagram1Score.getJustification();
				justification += "\n" +"<b>Diagram2:</b> Score:" + diagram2Score.getPolicyScore() + "\n";
				justification +=	diagram2Score.getJustification();
				reportText = (String) request.getParameter("reportText");
				reportText = justification + "\n\n" + reportText;
			} catch (Exception ex) {
				Logger.getLogger(Compare.class.getName()).log(Level.SEVERE, null, ex);
			}
			
		}
		else 
		{
			DiagramCompare compareObj = new DiagramCompare(diagram1, diagram2, context.getRealPath("/reports/"));
			
			try {
				
				path = compareObj.process();		
				reportText = PDFToText(path);
			} catch (Exception ex) {
				Logger.getLogger(Compare.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

			
		int compareId = searchAndLoadCompare(request,diagram1.getDiagramId(), diagram2.getDiagramId(), path);
		loadRationales(request, compareId);
		
		request.setAttribute("reportText", reportText);
		request.setAttribute("reportPath", path);
		request.setAttribute("path1", diagram1.getFilePath() + ".png");
		request.setAttribute("path2", diagram2.getFilePath() + ".png");
		request.setAttribute("diagramAId", diagram1.getDiagramId());
		request.setAttribute("diagramBId", diagram2.getDiagramId());
		request.setAttribute("diagramAName", diagram1.getDiagramName());
		request.setAttribute("diagramBName", diagram2.getDiagramName());
		//request.setAttribute("reportText", compareObj.getReportText());
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("WEB-INF/JSP/promote.jsp");
		dispatcher.forward(request, response);		
	}

	// <editor-fold defaultstate="collapsed"
	// desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Returns a short description of the servlet.
	 * 
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

	
	/**
	 * 
	 * @param path 
	 * the path which the report file was set
	 */
	private int saveReport(String path) {
		Report reportObj = new Report();
		reportObj.setDiagramA(this.diagramID1);
		reportObj.setDiagramB(this.diagramID2);
		reportObj.setReportFilePath(path);
		int reportId = ReportDAO.addReport(reportObj).getReportId();
		return reportId;
	}

	private String PDFToText(String filePath) throws IOException {
		PdfReader reader = new PdfReader(filePath);

		PdfReaderContentParser parser = new

		   PdfReaderContentParser(reader);

		TextExtractionStrategy strategy = null;
		StringBuffer text = new StringBuffer();

		for(int i = 1; i <= reader.getNumberOfPages(); i++) {

		       strategy = parser.processContent(i,

		          new SimpleTextExtractionStrategy());
		       
		       text.append(strategy.getResultantText());
		       //System.out.println(strategy.getResultantText());

		}
		return text.toString();
	}
	 /**
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	public void showPdf(String fileName, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		response.setContentType("application/pdf");
		response.setHeader("Content-disposition", "inline;attachment;filename="
				+ fileName);
		ByteArrayOutputStream baos = getByteArrayOutputStream(fileName);
		response.setContentLength(baos.size());
		ServletOutputStream sos = response.getOutputStream();
		baos.writeTo(sos);
		sos.flush();
	}

	
	/**
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	public ByteArrayOutputStream getByteArrayOutputStream(String fileName)
			throws IOException {
		File file = new File(fileName);
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] buf = new byte[1024];
		try {
			for (int readNum; (readNum = fis.read(buf)) != -1;) {
				bos.write(buf, 0, readNum); // no doubt here is 0
				// Writes len bytes from the specified byte array starting at
				// offset off to this byte array output stream.
				System.out.println("read " + readNum + " bytes,");
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return bos;
	}
	
	private int searchAndLoadCompare(HttpServletRequest request, int diagramAId, int diagramBId, String path) {
		domain.Compare compare = null;
		domain.Compare c = CompareDAO.searchCompare(diagramAId, diagramBId);
		if(c != null) {
			compare = c;
			
		}
		else {
			int reportId = saveReport(path);
			domain.Compare newCompare = new domain.Compare();
			newCompare.setDiagramAId(diagramAId);
			newCompare.setDiagramBId(diagramBId);
			newCompare.setReportId(reportId);
			newCompare = CompareDAO.addCompare(newCompare);
			if(newCompare != null) {
				compare = newCompare;
			}
			
		}
		request.setAttribute("compareId", compare.getCompareId());
		request.setAttribute("A", compare.getDiagramAId());
		request.setAttribute("B", compare.getDiagramBId());
		return compare.getCompareId();
	}
	
	private void loadRationales(HttpServletRequest request, int compareId) {
		ArrayList<domain.Rationale> rationales = RationaleDAO.getRationales(compareId);
		ArrayList<domain.Rationale> diagram1Rationales = new ArrayList<domain.Rationale>();
		ArrayList<domain.Rationale> diagram2Rationales = new ArrayList<domain.Rationale>();
		for(domain.Rationale rationale: rationales) {
			if(rationale.getPromotedDiagramId() == diagramID1) {
				diagram1Rationales.add(rationale);
			}
			else if(rationale.getPromotedDiagramId() == diagramID2) {
				diagram2Rationales.add(rationale);
			}
		}
		request.setAttribute("diagram1rationales", diagram1Rationales);
		request.setAttribute("diagram2rationales", diagram2Rationales);
	}
}