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
import javax.mail.Address;


import com.hit.utility.DBUtil;

import mail.PasswordMail;



@WebServlet("/changepwdmail") 
public class pwdmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public pwdmail() {
        super(); 
    }  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.setAttribute("fvid", request.getParameter("vid"));
		session.setAttribute("foldPass", request.getParameter("oldpassword"));
		session.setAttribute("newPass", request.getParameter("newpassword"));
		session.setAttribute("verifyPass", request.getParameter("verifynewpassword"));
	
		 String emailid = "";
		try {
			Connection con = DBUtil.provideConnection();
			
			PreparedStatement pse = null;
			ResultSet rse = null;
			String vid = request.getParameter("vid");
			pse = con.prepareStatement("select * from vendor where vid=?");
			
			pse.setString(1, vid);
			
			rse = pse.executeQuery();
	       
	        
	        if(rse.next()) {
	        	emailid = rse.getString("vemail");
	        }
		} catch(Exception e)
		{
			e.printStackTrace();
		}
		

		
	
		System.out.print(emailid);
		
  
        
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
					String password= rs.getString(2);
					System.out.print(password);
				
					session.setAttribute("email", emailid);
					session.setAttribute("pass", password);
					String data = "12345678901234567890123456789012345678901234567890";
					String otp="";
					char ch[]=data.toCharArray();
					
					for (int i=0; i<4; i++)
					{
						int j = (int)((Math.random()) * 50);
						otp=otp+ch[j];
					}
					
					session.setAttribute("otp",otp);
					PasswordMail pm = new PasswordMail(); 
					boolean status = pm.sendMail(emailid, otp);
					if(status)
					{ 
						response.sendRedirect("otp2.jsp?msg=send");
						
					}	 
				}
				else {
					response.sendRedirect("error.jsp?msg=email");
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
		
	
}

