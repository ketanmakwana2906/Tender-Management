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
 * Servlet implementation class RejectBidSrv
 */
@WebServlet("/AccountantrejectBid")
public class AccountantRejectBidSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountantRejectBidSrv() {
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
		
		if(user==null || !user.equalsIgnoreCase("accountant")  || uname.equals("") || pword.equals("")){
			
			response.sendRedirect("loginFailed.jsp");
			
		}
		
		
		String bidderId = request.getParameter("bid");
				
		BidderDao dao = new BidderDaoImpl();
		
		String status = dao.AccountantrejectBid(bidderId);
		
		PrintWriter pw = response.getWriter();
		
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
