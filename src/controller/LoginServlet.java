/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.User;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import repository.UserDAO;

/**
 * @author Zhang Junyu
 */

/**
 * Information class that contains all the features of one LoginServlet
 * @ doc author	Rui Hou
 */

public class LoginServlet extends HttpServlet {

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request
     * 			servlet request
     * @param response
     * 			servlet response
     * @throws ServletException
     * 			If a servlet-specific error occurs
     * @throws IOException
     * 			If an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {

	//get Parameters from jsp page.
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	HttpSession session = request.getSession(true);
	//authorize user from database, if exist, store it in session.
	if(username!=null&&password!=null){
		User userObj = UserDAO.getUser(username, password);
		if (userObj != null) {
		//policy manager
			session.setAttribute("username", username);
			session.setAttribute("userId", userObj.getUserId());
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/JSP/home.jsp");
			dispatcher.forward(request, response);
		} else {
			response.sendRedirect("invalid.jsp");	
	    //redirect user to invalid.jsp page if the input does not match
		}
	} else {
		if(session.getAttribute("username")!=null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/JSP/home.jsp");
			dispatcher.forward(request, response);
		} else {
			response.sendRedirect("invalid.jsp");	
		}
	}
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    	    throws ServletException, IOException {
    
    	doPost(request,response);
    }
}
