/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.Comment;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import repository.CommentDAO;

/**
 *
 * @author Pratham
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
	int imageId = Integer.parseInt(request.getParameter("imageId"));
	String comment = request.getParameter("comment");
	int diagramId = Integer.parseInt(request.getParameter("diagramId"));

	HttpSession session = request.getSession(true);
	int userId = Integer.parseInt(session.getAttribute("userId").toString());
	String userName = session.getAttribute("username").toString();
	// TODO debug userId
	//String userId = "1";
	
	//Save the comment
	//saveComment(request, userId, userName);

	/* removed by Xuesong Meng
	EditingHistory editObj = new EditingHistory();
	editObj.setDiagramId(imageId);
	editObj.setUserId(Integer.parseInt(userId));
	//update edit history
	EditingHistoryDAO.addHistory(editObj);
	*/
	
	RequestDispatcher dispatcher = request.getRequestDispatcher("Display");
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
    
    private void saveComment(HttpServletRequest request, int userId, String userName) {
    	Comment comment = new Comment();
    	comment.setPromotedDiagramId(Integer.parseInt(request.getParameter("diagramId")));
    	comment.setCommentText(request.getParameter("comment"));
    	comment.setUserId(userId);
    	comment.setUserName(userName);
    }
}
