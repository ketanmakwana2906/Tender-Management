package com.hit.srv;

import java.io.IOException;
import java.io.PrintWriter;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hit.beans.VendorBean;
import com.hit.dao.BidderDao;
import com.hit.dao.*;

import mail.PasswordMail;

/**
 * Servlet implementation class AcceptBidSrv
 */
@WebServlet("/accAcceptBidSrv")
public class AccAcceptBidSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccAcceptBidSrv() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String user = (String)session.getAttribute("user");
		String uname = (String)session.getAttribute("username");
		String pword = (String)session.getAttribute("password");
		
		if(!user.equalsIgnoreCase("accountant") || uname.equals("") || pword.equals("")){
			
			response.sendRedirect("loginFailed.jsp");
			
		}
		
		
		
		
		String bidderId = request.getParameter("bid");
		String tenderId = request.getParameter("tid");
		String vendorId = request.getParameter("vid");
		String amount = request.getParameter("amount");

		BidderDao dao = new BidderDaoImpl();
		
		String status = dao.AccacceptBid(bidderId, tenderId, vendorId);
		
		VendorDao vendorDao = new VendorDaoImpl(); // You'll need to create an instance of VendorDao
	    VendorBean vendor = vendorDao.getVendorDataById(vendorId); // Get the vendor data

	    
		
		PrintWriter pw = response.getWriter();
		
		PasswordMail pm = new PasswordMail(); 
		try {
	        String emailid = vendor.getEmail(); // Get the emailid from the VendorBean
			boolean status1 = pm.sendMail2(emailid,bidderId,tenderId,vendorId,amount);
			System.out.print(status1)	;
			} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("AccviewTenderBidsForm.jsp");
		
		rd.include(request, response);
		
		pw.print("<script>document.getElementById('show').innerHTML='"+status+"'</script>");
		
		pw.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
