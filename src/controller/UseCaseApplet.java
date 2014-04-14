/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.Decision;
import domain.UseCaseDiagram;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.dom4j.DocumentException;

import parser.JasonParser;
import parser.XMIParser;
import policy.UserDefinedRule;
import repository.DecisionDAO;
import repository.DiagramDAO;
import repository.UseCaseDiagramDAO;
import repository.UseCaseJSONDAO;



@WebServlet(name = "UseCaseApplet", urlPatterns = { "/UseCaseApplet" })
public class UseCaseApplet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    XMIParser xmi = new XMIParser();
    JasonParser jp = new JasonParser();
    EvaluateDiagram evaluate = new EvaluateDiagram();
    
	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String selectedDiagram = request.getParameter("selectedRecord");
		
		if(selectedDiagram!=null){
			
			//handle rules evaluation
			
			System.out.print("selected Diagram: " + selectedDiagram);
			
			//get use case diagram info using id
			UseCaseDiagram diagram = UseCaseDiagramDAO.getUseCaseDiagram(Integer.parseInt(selectedDiagram));
			System.out.println("diagram file path: " + diagram.getFilePath());
			
			
			XMIParser xmiData;
			try {
				//parse data from selected file
				xmiData = xmi.XMLReader(diagram.getFilePath() + diagram.getDiagramName());
				HttpSession session = request.getSession();
				
				//get rules json from database
				UserDefinedRule rules=jp.parser(UseCaseJSONDAO.getJSONString());
				System.out.println(rules+" "+xmiData);
				
				//evaluate
				evaluate.evaluate(rules, xmiData);
				session.setAttribute("compareResult", evaluate.getCompareResult());
				
				//forward to displayResult page
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("WEB-INF/JSP/displayUseCaseResult.jsp");
				dispatcher.forward(request, response);	
		            
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			
			//handle display of use case applet
			
			response.setContentType("text/html;charset=UTF-8");

			//TODO: pull use case diagrams from database and set as attribute
			ArrayList<UseCaseDiagram> diagrams = null;
			try {
				diagrams = UseCaseDiagramDAO.getAllUseCaseDiagrams();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("diagrams", diagrams);
			
			//handle upload
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("WEB-INF/JSP/useCaseApplet.jsp");
			dispatcher.forward(request, response);
		}
			
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
	
}