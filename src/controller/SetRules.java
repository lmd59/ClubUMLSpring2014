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
@WebServlet("/SetRules")
public class SetRules extends HttpServlet {
	private static final long serialVersionUID = 1L;
       JasonParser jp = new JasonParser();
       XMIParser xmiData = new XMIParser();
       EvaluateDiagram evaluate = new EvaluateDiagram();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetRules() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Getting to SetRules");
		
		request.setAttribute("json", UseCaseJSONDAO.getJSONString());
		
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("WEB-INF/JSP/displayUseCaseRules.jsp");
		dispatcher.forward(request, response);	
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Posting to SetRules");
		
		String temp = request.getParameter("rulesJSON");
		UserDefinedRule rules=null;
		if(jp.parser(temp)!=null)
		{
//			rules = jp.parser(temp);
//			HttpSession session = request.getSession();
//			session.setAttribute("rules",rules);
			
			//save json text
			UseCaseJSONDAO.insertJSONString(temp);
		}
	}
}
