package nl.hu.v1wac.firstapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/CalculatorServlet.do")
public class CalculatorServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		double number1 = Double.parseDouble(req.getParameter("number1"));
		double number2 = Double.parseDouble(req.getParameter("number2"));
		String calc = req.getParameter("calc");
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println(" <title>Dynamic Example</title>");
		out.println(" <body>");
		out.println(" <h2>Dynamic webapplication example</h2>");
		if(calc.equals("-")) {
			out.println(" <h2>"+number1+" substracted by "+number2+" equals "+(number1-number2)+".</h2>");
		}else if(calc.equals("+")) {
			out.println(" <h2>"+number1+" plus "+number2+" equals "+(number1+number2)+".</h2>");
		}else if(calc.equals("/")) {
			out.println(" <h2>"+number1+" divided by "+number2+" equals "+(number1/number2)+".</h2>");
		}else {
			out.println(" <h2>"+number1+" multiplied by "+number2+" equals "+(number1*number2)+".</h2>");
		}
		out.println(" </body>");
		out.println("</html>");

	}
}
