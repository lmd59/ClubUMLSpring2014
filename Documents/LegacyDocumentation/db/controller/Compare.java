/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.Diagram;
import domain.Report;
import compareAlgorithm.CompareDiagrams;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
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
import repository.DiagramDAO;
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

		CompareDiagrams compareObj = new CompareDiagrams(
				context.getRealPath(diagram1.getFilePath()),
				context.getRealPath(diagram2.getFilePath()),
				context.getRealPath("/reports/"));
		try {
			String path = compareObj.process();
			this.saveReport(path);
			// this.showPdf(path, request, response);
			request.setAttribute("reportPath", path);
			request.setAttribute("path1",
					"uploads/" + diagram1.getDiagramName());
			request.setAttribute("path2",
					"uploads/" + diagram2.getDiagramName());
			request.setAttribute("val1", diagram1.getDiagramId());
			request.setAttribute("val2", diagram2.getDiagramId());
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("WEB-INF/JSP/promote.jsp");
			dispatcher.forward(request, response);
		} catch (Exception ex) {
			Logger.getLogger(Compare.class.getName()).log(Level.SEVERE, null,
					ex);
		}
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
	private void saveReport(String path) {
		Report reportObj = new Report();
		reportObj.setDiagramA(this.diagramID1);
		reportObj.setDiagramB(this.diagramID2);
		reportObj.setReportFilePath(path);
		ReportDAO.addReport(reportObj);
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
}
