<%@page import="com.hit.beans.*"%>
<%
    UserBean user = (UserBean) session.getAttribute("current_user");
    if (user != null) {
        response.sendRedirect("chatHome.jsp");
    }
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Live Chat</title>
        <!-- Font Awesome -->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
        <!-- Bootstrap core CSS -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
        <!-- Material Design Bootstrap -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.8.5/css/mdb.min.css" rel="stylesheet">
    </head>
    <body style="background-image: url('images/bgForm.png'); background-size: cover; background-repeat: no-repeat;">
        <div class="container-fluid">

            <div class="row  justify-content-center" style="background-image: url('images/bgForm.png'); background-size: cover; background-repeat: no-repeat;">
                <div class="col-12 text-center text-light"><h2 class="mt-3"><strong>LIVE CHAT</strong></h2></div>
                <div class="col-12 text-center text-light"><p class="mt-1"><strong>Chat with Admin and other vendors</strong></p></div>
            </div>

            <!--Navbar-->

            
            
              <div class="row bg-info">
            
                <div class="col-md-12 p-2 text-center" >
                 
                        
                            <strong>Authenticate yourself to enter the chat</strong>
                        
                    
                </div>
            </div>

            <div class="row justify-content-center">
                <div class="col-md-6 col-lg-4 mt-5">

                    <form class="text-center border border-light p-5 bg-dark text-light" action="login.jsp" method="POST" id="loginForm">

                        <p class="h4 mb-4">Sign in to chat</p>

                        <input type="email" class="form-control mb-4" name="userEmail" placeholder="E-mail">
                        <input type="password" class="form-control mb-4" name="userPass" placeholder="Password">

                        <button class="btn btn-info btn-block my-4" type="submit">Sign in</button>

                    </form>
                    
                    
                </div>
            </div>

            <div class="row justify-content-center">
                <div class="col-md-6 col-lg-4 mt-5">
                    
                </div>
            </div>

        </div>

        <!-- JQuery -->
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <!-- Bootstrap tooltips -->
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.4/umd/popper.min.js"></script>
        <!-- Bootstrap core JavaScript -->
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <!-- MDB core JavaScript -->
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.8.5/js/mdb.min.js"></script>
        
        

    </body>
</html>
