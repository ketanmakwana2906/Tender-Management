package com.hit.srv;
import java.io.IOException; 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mail.PasswordMail;

@WebServlet("/Otp1Servlet")
public class Otp1Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Otp1Servlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String otp = request.getParameter("otp");
		HttpSession session = request.getSession();
		String otp1 = (String)session.getAttribute("otp1");
		String emailid = (String)session.getAttribute("email");
		
		String page="";
		String link ="http://localhost:8080/evoting_system/resetpassword.jsp";
		
		if(otp.equals(otp1))
		{
			page="register.jsp";
			session.setAttribute("vemail", emailid);
		}
		else
		{
			page="error.jsp?msg=otp";
		}
		response.sendRedirect(page);
	}
}

