package com.hit.srv;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hit.beans.VendorBean;
import com.hit.dao.BidderDao;
import com.hit.dao.BidderDaoImpl;

/**
 * Servlet implementation class AcceptBidSrv
 */
@WebServlet("/AssignBidSrv")
public class AssignBidSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AssignBidSrv() {
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
		
		if(!user.equalsIgnoreCase("user") || uname.equals("") || pword.equals("")){
			
			response.sendRedirect("loginFailed.jsp");
			
		}
		
		
		
		
		String bidderId = request.getParameter("bid");
		String tenderId = request.getParameter("tid");
		String vendorId = request.getParameter("vid");
		String paymentId = request.getParameter("pid");
		String amount = request.getParameter("amount");


		
		BidderDao dao = new BidderDaoImpl();
		
		String status = dao.acceptBidassign(bidderId, tenderId, vendorId,paymentId);
		
        response.sendRedirect("paymentSuccess.jsp?pid=" + paymentId +"&bid=" + bidderId + "&tid=" + tenderId + "&vid=" + vendorId + "&amount=" + amount);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
