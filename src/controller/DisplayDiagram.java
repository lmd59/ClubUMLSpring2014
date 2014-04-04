/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.Decision;
import domain.Diagram;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.download.DownloadDirectory;
import controller.download.DownloadZipfile;
import repository.DecisionDAO;
import repository.DiagramDAO;
import repository.UserDAO;

/**
 *
 * @author wintor12
 */

/**
 * Information class that contains all the features of one DisplayDiagram
 * @ doc author	Rui Hou
 */


public class DisplayDiagram extends HttpServlet {

    private String[] checked;// selected checkbox list from display jsp page.
    private String option; // function button from display jsp page.
	
    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request
     * 			servlet request
     * @param response
     * 			servlet response
     * @throws ServletException
     * 			if a servlet-specific error occurs
     * @throws IOException
     * 			if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
	
		checked = (String[]) request.getParameterValues("check");
		option = request.getParameter("submit");

		if (option != null) {
			if (option.equals("Go to compare")) {
			    goToCompare(checked, request, response);
			}
			if (option.equals("Display")) {
			    displayDiagram(checked, request, response);
		
			}
			if (option.equals("Download")) {
			    downloadDiagram(checked, request, response);
			}
			if (option.equals("DownloadProject")) {
			    downloadProject(request, response);
			}
			if (option.equals("CreateDecision")) {
			    createProjectDecision(request, response);
			}
		}
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request
     * 			servlet request
     * @param response
     * 			servlet response
     * @throws ServletException
     * 			if a servlet-specific error occurs
     * @throws IOException
     * 			if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request
     * 			servlet request
     * @param response
     * 			servlet response
     * @throws ServletException
     * 			if a servlet-specific error occurs
     * @throws IOException
     * 			if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
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
    /*
     * function to get two diagrams'id and path in diagram list , stroe them in ruquest and go to the compare page.
     * @throws ServletException
     * 			if a servlet-specific error occurs
     * @throws IOException
     * 			if an I/O error occurs
     */
    
    public void goToCompare(String[] checked, HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {

	int diagramId1 = Integer.parseInt(checked[0]);
	int diagramId2 = Integer.parseInt(checked[1]);

	request.setAttribute("diagramId1", diagramId1);
	request.setAttribute("diagramId2", diagramId2);
	//request.setAttribute("path1", "uploads/" + DiagramDAO.getDiagram(diagramId1).getDiagramName());
	//request.setAttribute("path2", "uploads/" + DiagramDAO.getDiagram(diagramId2).getDiagramName());
	request.setAttribute("path1", DiagramDAO.getDiagram(diagramId1).getFilePath() + ".png");
	request.setAttribute("path2", DiagramDAO.getDiagram(diagramId2).getFilePath() + ".png");
	
	RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/JSP/compare.jsp");
	dispatcher.forward(request, response);

    }

    /**
     * function to display the selected diagram and its comments.
     * @throws ServletException
     * 			if a servlet-specific error occurs
     * @throws IOException
     * 			if an I/O error occurs
     */
    
    public void displayDiagram(String[] checked, HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {

	int diagramId1 = Integer.parseInt(checked[0]);

	// retrieve diagram list from database.
	/*
	ArrayList<domain.EditingHistory> editedDiagrams = EditingHistoryDAO.getPriorityList();
	if (!editedDiagrams.isEmpty()) {
	    ArrayList<domain.Diagram> diagrams = new ArrayList();
	    for (int i = 0; i < editedDiagrams.size(); i++) {
		Diagram diagObj = DiagramDAO.getDiagram(editedDiagrams.get(i).getDiagramId());
		diagObj.setCreatedTime(editedDiagrams.get(i).getEditingTime());
		diagrams.add(diagObj);
	    }
	 */
	//Modified by Xuesong Meng
	
		int ProjectID = -1;
		if ((request.getParameter("ProjectID") != null))
		{
			ProjectID = Integer.parseInt(request.getParameter("ProjectID"));
		}
		System.out.println("ProjectID: " + ProjectID);
	
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
	    
		ArrayList<Decision> decisions;
		try {
			decisions = DecisionDAO.getLatestDecisions(ProjectID);
    	    if (!decisions.isEmpty()) {
    	    	System.out.println("Have decisions");
    			request.setAttribute("decisions", decisions);
    	    }
		} catch (SQLException e) {
			e.printStackTrace();
		}

	    RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/JSP/display.jsp");
		dispatcher.forward(request, response);
	}
	
    public void createProjectDecision(HttpServletRequest request, HttpServletResponse response)
    	    throws ServletException, IOException {
    	
    	HttpSession session = request.getSession(true);
    	int userId = -1;
    	Object tempObj = session.getAttribute("userId");
    	if (tempObj != null)
    		userId = Integer.parseInt(tempObj.toString());

    	tempObj = session.getAttribute("username");
    	String userName = "";
    	if (tempObj != null)
    		userName = tempObj.toString();

		String decisionName = request.getParameter("decisionName");
		System.out.println("Decision Name: " + decisionName);

		int ProjectID = -1;
		if ((request.getParameter("ProjectID") != null))
		{
			ProjectID = Integer.parseInt(request.getParameter("ProjectID"));
		}
			
		Decision newDecision = new Decision();
		newDecision.setDecisionName(decisionName);
		newDecision.setProjectId(ProjectID);
		newDecision.setUserId(userId);
		newDecision.setUserName(userName);
		
		if (DecisionDAO.addDecision(newDecision)) {
			System.out.println("Decision Name: " + decisionName);
		}

		// changes for loading the JSP back again
    	ArrayList<domain.Diagram> diagrams = DiagramDAO.getDiagramList(ProjectID);
    	if (!diagrams.isEmpty()) {
    		request.setAttribute("diagrams", diagrams);
    		//set the first diagram in diagram list as the default display diagram..
    		request.setAttribute("firstPath", diagrams.get(0).getFilePath() + ".png");
    		request.setAttribute("diagramId1", diagrams.get(0).getDiagramId());
    	   	request.setAttribute("ProjectID", ProjectID);
    	}
    	    
    	ArrayList<Decision> decisions;
		try {
			decisions = DecisionDAO.getLatestDecisions(ProjectID);
	        if (!decisions.isEmpty()) {
	        	System.out.println("Have decisions");
	    		request.setAttribute("decisions", decisions);
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}

    	RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/JSP/display.jsp");
    	dispatcher.forward(request, response);
    }
    	
    /*
     * function to download the selected diagram.
     */

    public void downloadDiagram(String[] checked, HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, FileNotFoundException, IOException {

		int id = Integer.parseInt(checked[0]) -1;
	    ArrayList<domain.Diagram> diagrams = DiagramDAO.getDiagramList();
	
		// the absolute path of folder where all diagrams store.
		String filePath = diagrams.get(id).getFilePath();
		String[] splitPath = filePath.split("/");
		String targetPath = "/" + splitPath[0] + "/" + splitPath[1] + "/";
		ServletContext context = getServletContext();
		String targetRealPath = context.getRealPath(targetPath);
		String reportRealPath = context.getRealPath("/reports/");
		DownloadDirectory dirObj = new DownloadDirectory();
	    DownloadZipfile zipObj = new DownloadZipfile();
	    File tmpdir = dirObj.createDirectory(reportRealPath);
	    
	    String outputFile = splitPath[2] + ".zip";
	    String downloadPath = dirObj.getDownloadPath();
	    String output = "";
	    if(OSDetails.getServerOS().equals("windows"))
	    {
	    	output = downloadPath + "\\" + outputFile;
	    } else if(OSDetails.getServerOS().equals("mac") || 
	    		OSDetails.getServerOS().equals("unix"))
	    {
	    	output = downloadPath + "/" + outputFile;
	    }
	    OutputStream ops = null;
	    DataInputStream in = null;
		try {
		    zipObj.downloadZipfileProcessor(targetRealPath, output);
		    ops = response.getOutputStream();
		    byte bytes[] = new byte[1024];
		    int length = 0;
			
		    File fileLoad = new File(downloadPath, outputFile);
	
		    String mimetype = context.getMimeType(outputFile);
		    response.setHeader("Content-disposition", "attachment;filename=" + outputFile);
		    response.setContentType((mimetype != null) ? mimetype : "application/octet-stream");
		    response.setContentLength((int) fileLoad.length());
	
		    in = new DataInputStream(new FileInputStream(fileLoad));
	
		    while ((in != null) && ((length = in.read(bytes)) != -1)) {
		    	ops.write(bytes, 0, length);
		    }
		    ops.flush();
		    ops.close();
		    in.close();
		    dirObj.deleteDirectory(tmpdir);
		    
		} catch (IOException ex) {
			if(!ops.equals(null))
			{
				ops.close();
			}
			if(!in.equals(null))
			{
				in.close();
			}
			dirObj.deleteDirectory(tmpdir);
			System.out.println(ex);
		}
    }
    
    
    /*
     * function to download project.
     */
    public void downloadProject(HttpServletRequest request, HttpServletResponse response)
    	    throws FileNotFoundException, IOException {

    	// the absolute path of folder where all diagrams store.
    	ServletContext context = getServletContext();
    	String targetPath = context.getRealPath("/uploads/");
    	String reportPath = context.getRealPath("/reports/");
    	
    	DownloadDirectory dirObj = new DownloadDirectory();
	    DownloadZipfile zipObj = new DownloadZipfile();
	    File tmpdir = dirObj.createDirectory(reportPath);
	    
	    String outputFile = "download.zip";
	    String downloadPath = dirObj.getDownloadPath();
	    String output = "";
	    if(OSDetails.getServerOS().equals("windows"))
	    {	    
	    	output = downloadPath + "\\" + outputFile;	    
	    } else if(OSDetails.getServerOS().equals("mac") || 
    			OSDetails.getServerOS().equals("unix"))
	    {
	    	output = downloadPath + "/" + outputFile;
	    }
    	    	
    	try {
    		zipObj.downloadZipfileProcessor(targetPath, output);
    		
    	    OutputStream ops = response.getOutputStream();
    	    byte bytes[] = new byte[1024];
    	    int length = 0;
    		
    	    File fileLoad = new File(downloadPath, outputFile);

    	    String mimetype = context.getMimeType(outputFile);
    	    response.setHeader("Content-disposition", "attachment;filename=" + outputFile);
    	    response.setContentType((mimetype != null) ? mimetype : "application/octet-stream");
    	    response.setContentLength((int) fileLoad.length());

    	    DataInputStream in = new DataInputStream(new FileInputStream(fileLoad));

    	    while ((in != null) && ((length = in.read(bytes)) != -1)) {
    	    	ops.write(bytes, 0, length);
    	    }
    	    ops.flush();
    	    ops.close();
    	    in.close();
    	    
    	    dirObj.deleteDirectory(tmpdir);
    	} catch (IOException ex) {
			System.out.println(ex);
    	}
    }
}
