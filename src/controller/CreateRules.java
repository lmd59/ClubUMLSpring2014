package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import parser.JasonParser;
import parser.XMIParser;
import policy.UserDefinedRule;
import repository.UseCaseJSONDAO;

/**
 * Servlet implementation class SetRules
 */
@WebServlet("/CreateRules")
public class CreateRules extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateRules() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Getting from Create Rules");
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Going to Create Rules");
		
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("WEB-INF/HTML/rules.html");
		dispatcher.forward(request, response);

	}
	


}
