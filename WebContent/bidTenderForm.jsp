<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@page import="java.sql.*,com.hit.dao.VendorDao,com.hit.dao.VendorDaoImpl, java.util.Date,com.hit.beans.TenderBean,com.hit.dao.TenderDao,com.hit.dao.TenderDaoImpl, com.hit.utility.DBUtil, javax.servlet.annotation.WebServlet, com.hit.beans.VendorBean" errorPage="errorpage.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
  <head>
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  
    <link rel="shortcut icon" type="image/png" href="images/Banner_Hit.png">
    <!--link rel="shortcut icon" type="image/ico" href="images/hit_fevicon.ico"-->
	
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
    
  </head>
<body>


	<%
		String user = (String)session.getAttribute("user");
		String uname = (String)session.getAttribute("username");
		String pword = (String)session.getAttribute("password");
		
		if(user== null || !user.equalsIgnoreCase("user") || uname.equals("") || pword.equals("")){
			
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
      
      <%-- <jsp:include page="login.jsp"></jsp:include> --%>
      
   <div class="col-md-8">
    <!-- <div class="marquee" style="border:2px black hidden; background-color:white">
        <h4 style="background-color:black; margin-top:-1.8px; margin-bottom:1px;padding: 5px; text-align: center;color:red;font-weight:bold">
        &nbsp; <span id="pagetitle">Tender Creation</span></h4>pagetitle id is given here
        <div  class="marquee-content" style="align:center; padding-top:5px;min-height:750px;background-color:cyan">
      --> 

     	<table style="border-radius:10px" >
			<tr >
				<td id="show" style="min-width:683px;min-height:0px;color:blue">Enter Bid Amount And Deadline To Bid</td>
			</tr>
		</table>
            
	<%
	
			
		String vendorId = request.getParameter("vid");
		String tenderId = request.getParameter("tid");
		
		TenderDao dao = new TenderDaoImpl();
		VendorDao daov = new VendorDaoImpl();
		
		TenderBean tender = dao.getTenderDataById(tenderId);
		
		VendorBean vendor = daov.getVendorDataById(vendorId);
	
	%>

	<form action="BidTenderSrv" method="post" enctype="multipart/form-data">
		<table style="background-color:white">
			<th colspan="2">Bid For The Tendor<input type="hidden" name="vid" value="<%= vendorId%>"></th>
			<tr><td style="color:red">	Tendor Id : </td><td><input type="hidden" name="tid" value="<%= tender.getId()%>"><%= tender.getId()%></td></tr>
		
		   <tr><td style="color:red">Tendor Name: </td><td> <input readonly type="text" name="tendername" required="required" value="<%=tender.getName()%>"></td></tr>
		   <tr><td style="color:red"> Tender Type: </td><td> <input readonly type="text" name="tendertype" required="required" value="<%=tender.getType()%>"></td></tr>
		   <tr><td style="color:red"> Deadline Reqd: </td><td> <input readonly type="datetime" name="" required="required" value="<%=tender.getDeadline()%>"></td></tr>
		   
<tr><td style="color:red"> Base Price : </td><td> <input readonly type="number" name="tenderamount" required="required" value="<%=tender.getPrice()%>"></td></tr>
		   <tr><td style="color:red"> Location : </td><td> <input readonly type="text" name="tenderlocation" required="required" value="<%=tender.getLocation()%>"></td></tr>
		   
		   <tr><td style="color:red"> Description: </td><td><textarea rows="2" cols="40" readonly><%=tender.getDesc() %> </textarea></td></tr>
		   <tr><td style="color:red">	Bid Amount : </td><td> <input type="number" name="bidamount" required="required" " value="<%=tender.getPrice()%>"></td></tr>
		   		   <tr><td style="color:red">experince (in years): </td><td> <input type="number" placeholder="how many years experince you have?" name="bidexperince" required="required" min="<%= tender.getBexp()%>" value="<%= tender.getBexp()%>" ></td></tr>
		 <tr>
    <td style="color:red">License Document:</td>
    <td><input type="file" name="licenseDocument" accept=".jpg, .jpeg, .png, .pdf, .doc, .docx" required="required">
            <p style="color:red; margin-top: 5px; font-size:15px; ">Only jpg,jpeg,png,pdf,doc,docx file types allowed</p>
    
</tr>

		   <input type="hidden" name="biddeadline" required="required" value="<%= tender.getDeadline()%>">				
			<tr><td colspan="2" align="center" value="Launch"><input type="submit" value="Bid Now" style="background-color:green;color:white;"></td></tr>
			
		</table>
	</form>
	
      <!-- </div>
     </div> -->
     </div>
      
      
    </div> <!-- End of container-fluid-->
	
	
	<!-- <div class="container" style="height:300px">
	ucomment this if you want to add some space in the lower part of page
	</div> -->



<!-- Now from here the footer section starts-->
                      
<!-- Including the footer of the page -->
    
<jsp:include page="footer.jsp"></jsp:include>
<script>
$(document).ready(function() {
    // Listen for changes in the file input field
    $('input[name="licenseDocument"]').on('change', function() {
        var allowedTypes = ['.jpg', '.jpeg', '.png', '.pdf', '.doc', '.docx'];
        var selectedFile = $(this).val();

        if (selectedFile) {
            var fileExtension = selectedFile.split('.').pop().toLowerCase();

            // Check if the file extension is not in the allowed types
            if ($.inArray('.' + fileExtension, allowedTypes) === -1) {
                // Show the error message
                alert("Only jpg, jpeg, png, pdf, doc, docx file types allowed")  ;              // Clear the file input (optional)
                $(this).val('');
            } else {
                // Clear the error message if a valid file is selected
                $('.file-error').html('');
            }
        } else {
            // Clear the error message if no file is selected
            $('.file-error').html('');
        }
    });
});
</script>
</body>
<style>
    input,textarea,select{
    	min-width:450px;
    	font-size:16px;
    	background-color:#f8f8f8;
    }
    input:hover,textarea:hover,select:hover{
    	min-width:450px;
    	background-color:white;
    }
	table, th, td { 
				margin-bottom:10px;
				margin-left:20%;
				font-size:20px;
                border: 2px solid green; 
                text-align:center; 
                background-color:white;
                color:#003399;
            } 
             td { 
                padding: 12px; 
                background-color:none; 
            } 
            th{
            	padding:12px;
            	background-color:#660033;
            	color:white;
            	font-weight:bold;
            }

</style>
</html>
