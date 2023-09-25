package com.hit.srv;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

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

            // Create an order
            JSONObject orderRequest = new JSONObject();
            orderRequest.put("amount", Integer.parseInt(request.getParameter("amount")) * 100); // Amount in paise
            orderRequest.put("currency", "INR");
            orderRequest.put("receipt", "order_rcptid_" + System.currentTimeMillis());

            Order order = razorpayClient.orders.create(orderRequest);

            // Get the order ID
            String orderId = order.get("id");

            // Store order ID in session for later use
            HttpSession session = request.getSession();
            session.setAttribute("orderId", orderId);
           System.out.print(orderId);

            // Redirect to the Razorpay payment page
           response.sendRedirect("MakePayment.jsp?bid=" + bidderId + "&tid=" + tenderId + "&vid=" + vendorId);
        } catch (RazorpayException e) {
            e.printStackTrace();
        }
    }
}
