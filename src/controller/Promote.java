/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.Comment;
import domain.Rationale;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import repository.CommentDAO;
import repository.CompareDAO;

/**
 *
 * @author Pratham
 * @author Ying
 */

/**
 * Information class that contains all the features of one Promote
 * @ doc author	Rui Hou
 */


@WebServlet(name = "Promote", urlPatterns = {"/Promote"})
public class Promote extends HttpServlet {

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

    	HttpSession session = request.getSession(true);
    	int userId = Integer.parseInt(session.getAttribute("userId").toString());
    	String userName = session.getAttribute("username").toString();
    	
    	updatePromoteCount(request);
    	// saveComment(request,userId,userName);
    	saveRationale(request,userId,userName);


    	/* removed by Xuesong Meng
		EditingHistory editObj = new EditingHistory();
		editObj.setDiagramId(imageId);
		editObj.setUserId(Integer.parseInt(userId));
		//update edit history
		EditingHistoryDAO.addHistory(editObj);
    	 */
	
    	RequestDispatcher dispatcher = request.getRequestDispatcher("Compare");
    	dispatcher.forward(request, response);

    }
    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	processRequest(request, response);
    }
    
    /**
     * Save comment
     * @param request
     * @param userId
     * @param userName
     */
    private void saveComment(HttpServletRequest request, int userId, String userName) {
    	int compareId = Integer.parseInt(request.getParameter("compareId"));
    	Comment comment = new Comment();
    	comment.setPromotedDiagramId(Integer.parseInt(request.getParameter("diagramId")));
    	comment.setCommentText(request.getParameter("comment"));
    	comment.setCompareId(compareId);
    	comment.setUserId(userId);
    	comment.setUserName(userName);
    	CommentDAO.addComment(comment);
    }
    
    private void saveRationale(HttpServletRequest request, int userId, String userName) {
    	int compareId = Integer.parseInt(request.getParameter("compareId"));
    	Rationale rationale = new Rationale();
    	rationale.setPromotedDiagramId(Integer.parseInt(request.getParameter("diagramId")));
    	rationale.setCompareId(compareId);
    	rationale.setUserId(userId);
    	rationale.setUserName(userName);

    	rationale.setSummary(request.getParameter("summary"));
    	rationale.setIssue(request.getParameter("issue"));
    	rationale.setIssueRelationship(request.getParameter("issueRelationship"));
    	rationale.setCriteria(request.getParameter("criteria"));
    	rationale.setCriteriaRelationship(request.getParameter("criteriaRelationship"));
    	
    	// @todo - the interim we are masking it as a comment
    	String tempComment = "summary: " + rationale.getSummary() +
    			", issue: " + rationale.getIssue() +
    			", issue relationship: " + rationale.getIssueRelationship() +
    			", criteria: " + rationale.getCriteria() +
    			", criteria relationship: " + rationale.getCriteriaRelationship();
    	
    	Comment comment = new Comment();
    	comment.setPromotedDiagramId(rationale.getPromotedDiagramId());
    	comment.setCompareId(compareId);
    	comment.setUserId(userId);
    	comment.setUserName(userName);    	
    	comment.setCommentText(tempComment);    	
    	CommentDAO.addComment(comment);
    }
    
    /**
     * Check which diagram is promoted and update the count in compare object.
     * @param request
     */
    private void updatePromoteCount(HttpServletRequest request) {
    	int AId = Integer.parseInt(request.getParameter("A"));
    	int BId = Integer.parseInt(request.getParameter("B"));
    	int diagramId = Integer.parseInt(request.getParameter("diagramId"));
    	int compareId = Integer.parseInt(request.getParameter("compareId"));
    	
    	if(diagramId == AId){
    		CompareDAO.updateCount(compareId, "A");
    	}
    	else if(diagramId == BId) {
    		CompareDAO.updateCount(compareId, "B");
    	} 	
    }
    
}