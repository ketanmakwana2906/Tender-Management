package com.hit.srv;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.client.fluent.Request;
import org.json.JSONObject;

import com.hit.beans.VendorBean;
import com.hit.dao.VendorDao;
import com.hit.dao.VendorDaoImpl;
import com.hit.utility.DBUtil;
import com.hit.utility.IDUtil;

/**
 * Servlet implementation class RegisterSrv
 */
@WebServlet("/RegisterSrv")
public class RegisterSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterSrv() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String vname = request.getParameter("vname").trim();
		String vemail = ((String) session.getAttribute("vemail")).trim().toLowerCase();
		String vmob = request.getParameter("vmob").trim();
		//int vmob = Integer.parseInt(mob);
		String vaddr = request.getParameter("vaddr").trim();
		String cname = request.getParameter("cname").trim();
		String vpass = request.getParameter("vpass").trim();
		
		String vid = IDUtil.generateVendorId(vemail, vmob);
      
		System.out.print(vname+vemail+vmob+vaddr+cname+vpass+vid);
		
		 boolean isEmailValid = isEmailDeliverable(vemail);

	        if (!isEmailValid) {
	            response.sendRedirect("error.jsp?msg=email");
	            return;
	        }
	      
	        
	        if (!isPasswordValid(vpass)) {
	            response.sendRedirect("error.jsp?msg=password");
	            return;
	        }
		VendorBean vendor = new VendorBean(vid, vname, vmob, vemail, vaddr, cname, vpass);
		
		VendorDao dao = new VendorDaoImpl();
		
		String status = dao.registerVendor(vendor);
	
		PrintWriter pw = response.getWriter();
		
		RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
		
		rd.include(request, response);
		
		pw.print("<script>document.getElementById('show').innerHTML = '"+status+"'</script>");
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
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
	 private boolean isPhoneValid(String phone) {
		    String papiKey = "53e82dc254354752aba889f0e219a493";
		    String formattedPhone = "+91" + phone;
		    String papiUrl = "https://phonevalidation.abstractapi.com/v1/?api_key=" + papiKey + "&phone=" + formattedPhone;
		    System.out.print(papiUrl);

		    try {
		        String responseJson = Request.Get(papiUrl).execute().returnContent().asString();
		        JSONObject jsonObject = new JSONObject(responseJson);
		        boolean valid = jsonObject.getBoolean("valid");
		        System.out.print(valid);
		        return valid;
		    } catch (IOException e) {
		        e.printStackTrace();
		        return false; // In case of an error, consider the phone number as invalid
		    }
		}

	 private boolean isPasswordValid(String password) {
	        // Define your password validation rules here.
	        // For example, you can require a minimum length, at least one uppercase letter,
	        // at least one lowercase letter, at least one digit, and at least one special character.

	        String passwordPattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";
	        Pattern pattern = Pattern.compile(passwordPattern);
	        Matcher matcher = pattern.matcher(password);

	        return matcher.matches();
	    }
	 
}