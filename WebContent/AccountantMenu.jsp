<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Accountant Home</title>
</head>
<body>
	  <div class="menubar secondnav " style="background-color:white;">
    <div id="menucontent" class="container secondnav">
      <div id="collapsable-nav" class="collapse navbar-collapse hidden-lg hidden-md">
        <ul id="nav-list" class="nav navbar-nav navbar-left pull-left">
            <li id="navHomeButton">
              <a href="AccountantHome.jsp">
                <span class="glyphicon glyphicon-home"></span> Home</a> <!--Home button is here -->
            </li>
            <!-- <li class="dropdown">
                <a class="dropdown-toggle " data-toggle="dropdown" data-animations="fadeIn fadeInLeft fadeInUp fadeInRight" data-hover="dropdown">Vendors
                    <span class="caret"></span></a>    Dropdown under about us category
                <ul class="dropdown-menu">
                  <li><a href="viewVendor.jsp">View All Vendors</a></li>
                  <li><a href="ApproveVendor">Approve Vendors</a></li>
                  <li><a href="vendorDetail.jsp">Vendor Detail</a></li>
                </ul>
              </li> -->
             <!-- <li><a href="accountantViewVendor.jsp">Vendors</a></li> --> 
              
             <li>
            	<a href="AccviewTenderBidsForm.jsp">View admin approved Tender Bids</a>
        
            </li>
            <li>
            	<a href="paymentHistory.jsp">View payment orders history</a>
        
            </li>
            <li>
            	<a href="viewAccAssignedTenders.jsp">View assigned tenders</a>
        
            </li>
          
              
             <li>
            	<a href="LogoutSrv">Logout</a>
        
            </li>
            
            <li>
              <!-- Form for searching any tenders or items-->
             <!-- <form class="navbar-form hidden-xs" action="searchTender.jsp"  >
                <div class="form-group" >
                  <input type="text" name="tid" class="form-control" placeholder="Find Tenders by name or tenderId" style="margin-left:10px;" required>
                </div>
               
                <button type="submit" class="btn btn-primary" >Search</button>
                
              </form>
              <!--End of form section-->
            </li>
        </ul>
      </div>
    </div>
  </div>
</body>
</html>