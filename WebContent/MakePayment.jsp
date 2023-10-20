<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@page import="java.sql.*, com.hit.utility.DBUtil, javax.servlet.annotation.WebServlet" errorPage="errorpage.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
  <head>
    <link rel="shortcut icon" type="image/png" href="images/Banner_Hit.png">
   <link rel="shortcut icon" type="image/ico" href="images/hit_fevicon.ico">
	
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tender Management System</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/annimate.css">
    <link href="css/font-awesome.min.css" type="text/css" rel="stylesheet">
    <link href="css/SpryTabbedPanels.css" type="text/css" rel="stylesheet">
    <!--link rel="stylesheet" href="css/styles.css"-->
    <link href="https://fonts.googleapis.com/css?family=Black+Ops+One" rel="stylesheet">
    <link href="css/bootstrap-dropdownhover.min.css">
    <link rel="stylesheet" href="css/style2.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://checkout.razorpay.com/v1/checkout.js"></script>
  </head>
<body>

	<%
		String user = (String)session.getAttribute("user");
		String uname = (String)session.getAttribute("username");
		String pword = (String)session.getAttribute("password");
		
		if(!user.equalsIgnoreCase("user") || uname.equals("") || pword.equals("")){
			
			response.sendRedirect("loginFailed.jsp");
			
		}
	
	%>
	
	<!-- Including the header of the page  -->
	
	<jsp:include page="header.jsp"></jsp:include>
	
	<jsp:include page="vendorMenu.jsp"></jsp:include>
	
	<div class="clearfix hidden-sm hidden-xs" style="color:white;background-color: green; margin-top:-15px; margin-bottom: 12px"><marquee>Welcome to Tender Management Site</marquee>
 </div> <!--A green color line between header and body part-->
 
     <div class="container-fluid">
     
     	<div class="notice">
        <div class="col-md-3"style="margin-left:2%">
     		<% Connection con = DBUtil.provideConnection(); %>
     		
     		<jsp:include page="notice.jsp"></jsp:include><br>
     		
          <!-- Next marquee starting-->
          <jsp:include page="approved.jsp"></jsp:include><br>
          
        </div>  <!-- End of col-md-3-->
      </div> <!-- End of notice class-->
      
      
      <!-- Next part of same container-fluid in which galary or other information will be shown-->

      
      
          
   <div class="col-md-8">
    <div class="marquee" style="border:2px black hidden; background-color:white">
        <h4 style="background-color:black; margin-top:-1.8px; margin-bottom:1px;padding: 5px; text-align: center;color:red;font-weight:bold">
        &nbsp; <span id="pagetitle">PAYMENT SECTION</span></h4><!-- pagetitle id is given here -->
        <div class="marquee-content" style="align:center; padding-top:100px; padding-left:200px;min-height:750px;background-color:cyan">
     	
        <center> <strong><h1>Make Payment</h1></strong></center>
        <h3>Payment details</h3>
        <p>Bid ID : <strong><%= request.getParameter("bid") %></strong></p>
        <p>Tender ID : <strong><%= request.getParameter("tid") %></strong></p>
        <p>Vendor ID : <strong><%= request.getParameter("vid") %></strong></p>
        <p>Amount : <strong> &#8377; <%= request.getParameter("amount") %></strong></p>
        <p>Order ID : <strong> <%= session.getAttribute("orderId") %></strong></p>
        
        <button id="rzp-button">Pay Now</button>
  
     
      </div>
     </div>
     </div>
      
      
      
      
     <a><h1></h1></a>
      
    </div> <!-- End of container-fluid-->
	
	




<!-- Now from here the footer section starts-->
                      
<!-- Including the footer of the page -->
    
<jsp:include page="footer.jsp"></jsp:include>
 <script>
        var orderId = "<%= session.getAttribute("orderId") %>";
        var amount = "<%= request.getParameter("amount") %>";
        var bidderId = "<%= request.getParameter("bid") %>";
        var tenderId = "<%= request.getParameter("tid") %>";
        var vendorId = "<%= request.getParameter("vid") %>";

        var options = {
            key: "rzp_test_MQbTBQlaI7ViKC",
            amount: amount * 100, // Amount in paise
            currency: "INR",
            name: "tender management",
            description: "Payment for Order #" + orderId,
            order_id: orderId,
            handler: function (response) {
                alert("Payment Successful! Payment ID: " + response.razorpay_payment_id);
                
                var servletUrl = "AssignBidSrv?bid=" + bidderId + "&vid=" + vendorId + "&tid=" + tenderId + "&pid=" + response.razorpay_payment_id + "&amount=" + amount;

                
                window.location.href = servletUrl; 

            },
            prefill: {
            	name: "vendor",
                contact: "9999999998",
                email: "vendor@example.com"
            },
            theme: {
                color: "#3399cc"
            }
        };

        var rzp = new Razorpay(options);
        rzp.on('payment.failed', function (response) {
            alert("Payment failed: " + response.error.description);
        });

        document.getElementById('rzp-button').onclick = function () {
            rzp.open();
        };
    </script>
</body>
</html>
