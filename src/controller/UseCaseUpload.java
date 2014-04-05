/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.Decision;
import domain.Diagram;
import domain.DiagramPolicyScore;
import domain.Report;
import domain.UseCaseDiagram;
import compareAlgorithm.CompareDiagrams;
import compareAlgorithm.DiagramCompare;
import compareAlgorithm.smartPolicy.PolicyScoreGenerator;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
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
import repository.DecisionDAO;
import repository.DiagramDAO;
import repository.PolicyDAO;
import repository.RationaleDAO;
import repository.ReportDAO;
import repository.UseCaseDiagramDAO;


/**
 * 
 * @author Pratham
 */

/**
 * Information class that contains all the features of one Compare
 * @ doc author	Rui Hou
 */

@WebServlet(name = "Compare", urlPatterns = { "/Compare" })
public class UseCaseUpload extends HttpServlet {

	private int diagramID1;
	private int diagramID2;
	private String diagram1RealPath;
	private String diagram2RealPath;
	
	private String[] checked;// selected checkbox list from display jsp page.
    private String option; // function button from display jsp page.
	
	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = this.getServletContext();
		
		response.setContentType("text/html;charset=UTF-8");
		
		checked = (String[]) request.getParameterValues("check");
		option = request.getParameter("submit");

		//TODO: pull use case diagrams from database and set as attribute
		ArrayList<UseCaseDiagram> diagrams = null;
		try {
			diagrams = UseCaseDiagramDAO.getAllUseCaseDiagrams();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("diagrams", diagrams);
		
		
		//handle check box redirect to rule compare
		if (option != null) {
			if (option.equals("Compare to Rule")) {
				compareToRule(checked, request, response);
		
			}
		}else{
			//handle upload
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("WEB-INF/JSP/useCaseUpload.jsp");
			dispatcher.forward(request, response);
			
		}
		
		
			
	}

	
	public void compareToRule(String[] checked, HttpServletRequest request, HttpServletResponse response)
		    throws ServletException, IOException {

		int diagramId1 = Integer.parseInt(checked[0]);
			int ProjectID = -1;
			if ((request.getParameter("ProjectID") != null))
			{
				ProjectID = Integer.parseInt(request.getParameter("ProjectID"));
			}
		
			ArrayList<domain.Diagram> diagrams = DiagramDAO.getDiagramList(ProjectID);
		    if (!diagrams.isEmpty()) {

				for (int i = 0; i < diagrams.size(); i++) {
				    if (diagrams.get(i).getDiagramId() == diagramId1) {
						// Set the first diagram in diagram list as the default display diagram..
						request.setAttribute("firstPath", diagrams.get(i).getFilePath() + ".png");
						request.setAttribute("diagramId1", diagrams.get(i).getDiagramId());
						break;
				    }			    
				}
				request.setAttribute("diagrams", diagrams);
		    	request.setAttribute("ProjectID", ProjectID);
		    }
		    
			HashMap<String, Decision> decisions;
			try {
				decisions = DecisionDAO.getLatestDecisions(ProjectID);
	    	    if (!decisions.isEmpty()) {
	    			request.setAttribute("decisions", decisions);
	    	    }
			} catch (SQLException e) {
				e.printStackTrace();
			}

		    RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/JSP/display.jsp");
			dispatcher.forward(request, response);
		}
	
	
	
	
	
	//other stuff
	
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