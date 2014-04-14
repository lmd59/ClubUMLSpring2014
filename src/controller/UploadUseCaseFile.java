package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import java.io.*;

import org.dom4j.*;

import java.util.List;
import java.util.Iterator;

import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import domain.UseCaseDiagram;
import parser.XMIParser;
import repository.UseCaseDiagramDAO;

/**
 * Servlet implementation class UploadFile
 */
@WebServlet("/UploadUseCaseFile")
public class UploadUseCaseFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
      XMIParser xmi = new XMIParser();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadUseCaseFile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		String selectedDiagram = request.getParameter("selectedRecord");
		
		System.out.print("selected Diagram: " + selectedDiagram);
		
		UseCaseDiagram diagram = UseCaseDiagramDAO.getUseCaseDiagram(Integer.parseInt(selectedDiagram));
		System.out.println("diagram file path: " + diagram.getFilePath());
		
		
		XMIParser xmiData;
		try {
			xmiData = xmi.XMLReader(diagram.getFilePath() + diagram.getDiagramName());
			HttpSession session = request.getSession();
	        session.setAttribute("xmiData",xmiData);
	        this.getServletConfig().getServletContext().setAttribute("xmiData", xmiData);
	        
	        //request.setAttribute("source_button", "Compare_to_Rule");
	        request.getRequestDispatcher("/SetRules").forward(request,response);      
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
           
	}

}
