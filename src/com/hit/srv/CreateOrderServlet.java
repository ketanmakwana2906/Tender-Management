package com.hit.srv;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.hit.beans.*;
import com.hit.dao.TenderDao;
import com.hit.dao.*;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@WebServlet("/CreateOrderServlet")
public class CreateOrderServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Initialize Razorpay client
            String razorpayKey = "rzp_test_MQbTBQlaI7ViKC";
            String razorpaySecret = "8Azj46EMnWKyZHuFPBpSoOpH";
            RazorpayClient razorpayClient = new RazorpayClient(razorpayKey, razorpaySecret);
            String bidderId = request.getParameter("bid");
    		String tenderId = request.getParameter("tid");
    		String vendorId = request.getParameter("vid");
    		String amount = request.getParameter("amount");

    		BidderDao dao = new BidderDaoImpl();
            List<PaymentBean> existingOrders = dao.getBidOrderId(bidderId);

            
            if (existingOrders.isEmpty()) {
            // Create an order
            JSONObject orderRequest = new JSONObject();
            orderRequest.put("amount", Integer.parseInt(request.getParameter("amount")) * 100); // Amount in paise
            orderRequest.put("currency", "INR");
            orderRequest.put("receipt", "order_rcptid_" + System.currentTimeMillis());

            Order order = razorpayClient.orders.create(orderRequest);

            // Get the order ID
            String orderId = order.get("id");
            String createdAt = order.get("created_at").toString(); // Parse as a string

            String status = order.get("status");

            // Store order ID in session for later use
            HttpSession session = request.getSession();
            session.setAttribute("orderId", orderId);
            String paymentId ="pending";

           System.out.print(orderId+createdAt+paymentId+status);
           
           PaymentBean payment = new PaymentBean(orderId,createdAt,vendorId,bidderId,tenderId,amount,paymentId,status);
  		
  		
  		String status1 = dao.bidPaymentOrder(payment);
            

            }
            
            else {
            	String orderId = existingOrders.get(0).getOrderId();
                HttpSession session = request.getSession();
                session.setAttribute("orderId", orderId);
            }
            // Redirect to the Razorpay payment page
          response.sendRedirect("MakePayment.jsp?bid=" + bidderId + "&tid=" + tenderId + "&vid=" + vendorId + "&amount=" + amount);
        } catch (RazorpayException e) {
            e.printStackTrace();
        }
    }

}
