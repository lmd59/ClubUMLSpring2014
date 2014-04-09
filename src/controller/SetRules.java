package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import parser.JasonParser;
import parser.XMIParser;
import policy.UserDefinedRule;
import repository.PolicyDataEdit;

/**
 * Servlet implementation class SetRules
 */
@WebServlet("/SetRules")
public class SetRules extends HttpServlet {
	private static final long serialVersionUID = 1L;
       PolicyDataEdit pde = new PolicyDataEdit();
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
		// TODO Auto-generated method stub
		String temp = request.getParameter("rulesJSON");
		UserDefinedRule rules=null;
		if(jp.parser(temp)!=null)
		{
			rules = jp.parser(temp);
			HttpSession session = request.getSession();
			session.setAttribute("rules",rules);
		}
		//System.out.println("actors:"+rules.getMustHaveActors());
		//pde.insertPolicy(rules);
		System.out.println(xmiData);
			xmiData = (XMIParser) request.getSession().getAttribute("xmiData");		
			//System.out.println(xmi.getActors());
			System.out.println(rules+" "+xmiData);
			evaluate.evaluate(rules, xmiData);
			HttpSession session = request.getSession();
			session.setAttribute("compareResult", evaluate.getCompareResult());
		//System.out.println(request.getSession().getAttribute("xmiData"));
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//String  testFile= request.getParameter("file");

		//ruleJSON
		//TODO Auto-generated method stub

	}
	


}
