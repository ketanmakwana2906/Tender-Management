package com.hit.srv;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.client.fluent.Request;
import org.json.JSONObject;

import javax.mail.Address;


import com.hit.utility.DBUtil;

import mail.PasswordMail;



@WebServlet("/verificationServlet") 
public class verificationEmailsrv extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public String otp2="";
       
    public verificationEmailsrv() {
        super(); 
    }  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String emailid = request.getParameter("email");
		//String phone = request.getParameter("phone");
	
		System.out.print(emailid);
		
		 boolean isEmailValid = isEmailDeliverable(emailid);

	        if (!isEmailValid) {
	            response.sendRedirect("error.jsp?msg=email");
	            return;
	        }
        
		if(!emailid.equals(""))
		{ 
			
			Connection conn =DBUtil.provideConnection();
			PreparedStatement ps = null;
			PreparedStatement pst = null;
			ResultSet rs = null;
			ResultSet rs1 = null;
			try
			{
				pst = conn.prepareStatement("select * from vendor where vemail=?");
				pst.setString(1, emailid);
				rs=pst.executeQuery();
				if (rs.next())
				{
					response.sendRedirect("error.jsp?msg=exists");
				}
				else 
				{
					
					HttpSession session = request.getSession();
					session.setAttribute("email", emailid);
					//session.setAttribute("email", phone);
					String data = "12345678901234567890123456789012345678901234567890";
					String otp1="";
					
					char ch[]=data.toCharArray();
					
					for (int i=0; i<4; i++)
					{
						int j = (int)((Math.random()) * 50);
						otp1=otp1+ch[j];
					}
					for (int i=0; i<4; i++)
					{
						int j = (int)((Math.random()) * 50);
						otp2=otp2+ch[j];
					}
					
					session.setAttribute("otp1",otp1);
					session.setAttribute("otp2",otp1);
					PasswordMail pm = new PasswordMail(); 
					boolean status = pm.sendMail(emailid, otp1);
					if(status)
					{ 
						response.sendRedirect("otp1.jsp?msg=send");
						
					}	 
				}
			}		
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
		}
		else
		{
			PrintWriter pw = response.getWriter();
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.include(request, response);
			pw.print("<script>document.getElementById('show').innerHTML = 'Invalid Username or Password<br>Please Try Again!'</script>");
			}
		}
		
	 private boolean isEmailDeliverable(String email) {
	        String apiKey = "38b90c9d3dd441838f664bf87f9bda48";
	        String apiUrl = "https://emailvalidation.abstractapi.com/v1/?api_key=" + apiKey + "&email=" + email;
	        System.out.print(apiUrl);

	        try {
	            String responseJson = Request.Get(apiUrl).execute().returnContent().asString();
	            JSONObject jsonObject = new JSONObject(responseJson);
	            String deliverability = jsonObject.getString("deliverability");
	            System.out.print(deliverability);
	            return "DELIVERABLE".equalsIgnoreCase(deliverability);
	        } catch (IOException e) {
	            e.printStackTrace();
	            return false; // In case of an error, consider the email as invalid
	        }
	    }
}

