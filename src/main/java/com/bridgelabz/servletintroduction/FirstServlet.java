package com.bridgelabz.servletintroduction;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
		description = "Login Servlet Interface",
		urlPatterns = {"/LoginServlet"},
		initParams = {
				@WebInitParam(name = "pwd", value ="karan123")
		})
public class FirstServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.getWriter().println("Hello World, this is my first servlet");
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String user = req.getParameter("user");
		String pwd = req.getParameter("pwd");
		String correctPwd = getServletConfig().getInitParameter("pwd");
		if(user.matches("^[A-Z]{1}[a-z]{3,}$") && correctPwd.equals(pwd)) {
			req.setAttribute("user", user);
			req.getRequestDispatcher("LoginSuccess.jsp").forward(req, res);
		}
		else {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("login.html");
			res.getWriter().println("<font> color=red>Either user name or password is wrong.</font>");
			rd.include(req, res);
		}
	}
}
