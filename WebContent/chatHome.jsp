<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<%@page import="com.hit.beans.*"%>
<%@page import="java.util.List"%>
<%@page import="com.hit.dao.*"%>

<%
    UserBean user = (UserBean) session.getAttribute("current_user");
    if (user == null) {
        response.sendRedirect("index.jsp");
    }
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html class=''>
<style>
    .label {
        background-color: #4285f4;
        color: #fff;
        border-radius: 4px;
        font-size: 12px;
        padding: 2px 6px;
        width:40px;
    }
</style>

    <head>
     <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
    
        <link rel="canonical" href="https://codepen.io/emilcarlsson/pen/ZOQZaV?limit=all&page=74&q=contact+" />
        <link href='https://fonts.googleapis.com/css?family=Source+Sans+Pro:400,600,700,300' rel='stylesheet' type='text/css'>
        <script src="https://use.typekit.net/hoy3lrg.js"></script>
        <script>try {
                Typekit.load({async: true});
            } catch (e) {
            }</script>
        <link rel='stylesheet prefetch' href='https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css'>
        <link rel='stylesheet prefetch' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.2/css/font-awesome.min.css'>
        <link rel="stylesheet" href="css/style.css"/>

    </head>
    <body>

        <div id="frame">
            <!--SidePannel Starts-->
            <div id="sidepanel">
                <div id="profile">
                    <div class="wrap">
                      
                        <p> welcome <u> <%= user.getUserName()%></u> to chat </p>


                    </div>
                </div>
              

<c:if test='<%= "admin".equals(session.getAttribute("user")) %>'>
 <div id="search">
    <label for="contact-search"><i class="fa fa-search" aria-hidden="true"></i></label>
    <input type="text" id="contact-search" placeholder="Search..." />
</div>
    <div id="contacts">
        <ul>
            <%
            userDaoImpl u = new userDaoImpl();
            List<UserBean> list = u.readAllUser();

            for (UserBean us : list) {
                if (user.getUserId() != us.getUserId()) {
                    String email = us.getUserEmail();
            %>
            <li class="contact" onclick="msgSession(<%= us.getUserId()%>); msgHeader(); msgForm()" style="border-bottom: 1px solid whitesmoke">
                <div class="wrap">
                    <!-- <span class="contact-status online"></span> -->
                    <div class="meta">
                        &nbsp;
                        <p class="name"><%= us.getUserName()%></p>
                        <p class="preview"><%= us.getUserEmail()%></p>
                        <%
                            if ("admin-krd@gmail.com".equals(email) || "accountant-tender@gmail.com".equals(email)) {
                        %>
                        <p class="label">Official <i class="fa fa-check-circle" aria-hidden="true"></i></p>
                        <%
                            }
                        %>
                    </div>
                </div>
            </li>
            <%
                }
            }
            %>
        </ul>
    </div>
</c:if>

<c:if test='<%= "accountant".equals(session.getAttribute("user")) || "user".equals(session.getAttribute("user")) %>'>
 <div id="search">
    <input readonly type="text" placeholder="need help? , Chat with admin" />
</div>
    <div id="contacts">
        <ul>
            <%
            userDaoImpl u = new userDaoImpl();
            List<UserBean> list = u.readForAccountant();

            for (UserBean us : list) {
                if (user.getUserId() != us.getUserId()) {
                    String email = us.getUserEmail();
            %>
            <li class="contact" onclick="msgSession(<%= us.getUserId()%>); msgHeader(); msgForm()" style="border-bottom: 1px solid whitesmoke">
                <div class="wrap">
                    <!-- <span class="contact-status online"></span> -->
                    <div class="meta">
                        &nbsp;
                        <p class="name"><%= us.getUserName()%></p>
                        <p class="preview"><%= us.getUserEmail()%></p>
                        <%
                            if ("admin-krd@gmail.com".equals(email) || "accountant-tender@gmail.com".equals(email)) {
                        %>
                        <p class="label">Official <i class="fa fa-check-circle" aria-hidden="true"></i></p>
                        <%
                            }
                        %>
                    </div>
                </div>
            </li>
            <%
                }
            }
            %>
        </ul>
    </div>
</c:if>

                
                
                
                <div id="bottom-bar" style="width: 700px;">
                    <!-- <button id="addcontact"><i class="fa fa-user-plus fa-fw" aria-hidden="true"></i> <span>Add contact</span></button> -->
                    <a href="chatLogout.jsp"><button id="settings"><i class="fa fa-user" aria-hidden="true"></i> Logout from chat</button></a>
                </div>
            </div>
            <!--Side Panel ends-->

            <div class="content">
                <span id="msgHeader">
                    <!--Msg Header-->
                </span>
                <div class="messages">
                    <ul id="msgOutput">
                        <!--Chat Box here-->
                    </ul>
                </div>
                <div class="message-input">
                    <div class="wrap" id="msgForm">
                        <!--Msg Form-->
                    </div>
                </div>
            </div>
        </div>
        <div id="msgSend"></div>

        <script src='https://code.jquery.com/jquery-2.2.4.min.js'></script>

        <!-- JQuery -->
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <!-- Bootstrap tooltips -->
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.4/umd/popper.min.js"></script>
        <!-- Bootstrap core JavaScript -->
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <!-- MDB core JavaScript -->
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.8.5/js/mdb.min.js"></script>


        <!--Ajax request script starts-->
        <script>

                            var request;
                            function msgSession(reciever) {
                                
                                $.ajax({
                                    url: "msgSession.jsp?reciever=" + reciever,
                                    success: function (data, textStatus, jqXHR) {

                                    },
                                    error: function (jqXHR, textStatus, errorThrown) {
                                        console.log(data);
                                    }
                                });
                            }
        </script>
        <!--Ajax request script ends-->   

        <!--Ajax request script starts-->
        <script>

            var request;
            function msgHeader() {
                $.ajax({
                    url: "msgBoxHeader.jsp",
                    success: function (data, textStatus, jqXHR) {
                        $("#msgHeader").html(data);
                        setInterval(function () {
                            $.ajax({
                                url: "msgBox.jsp",
                                success: function (data, textStatus, jqXHR) {
                                    $("#msgOutput").html(data);
                                },
                                error: function (jqXHR, textStatus, errorThrown) {
                                    console.log(data);
                                }
                            });
                        }, 2000);
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        console.log(data);
                    }
                });
            }


        </script>
        <!--Ajax request script ends-->   


        <!--Ajax request script starts-->
        <script>
            var request;
            function msgForm() {
                $.ajax({
                    url: "msgForm.jsp",
                    success: function (data, textStatus, jqXHR) {
                        $("#msgForm").html(data);
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        console.log(data);
                    }
                });
            }
        </script>
        <!--Ajax request script ends-->   

        <!--Ajax request script starts-->
        <script>
    
            var request;
            function msgSend() {
                $.ajax({
                    type: 'POST',
                    url: 'msgSend.jsp',
                    data: {
                        msg: $('#msg').val()
                    },
                    success: function (data, textStatus, jqXHR) {
                        $.ajax({
                            url: "msgForm.jsp",
                            success: function (data, textStatus, jqXHR) {
                                $("#msgForm").html(data);
                            },
                            error: function (jqXHR, textStatus, errorThrown) {
                                console.log(data);
                            }
                        });
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        console.log(data);
                    }
                });
            }
            $(document).ready(function () {
                // Function to filter and display contacts based on search input
                function filterContacts() {
                    var searchInput = $("#contact-search").val().toLowerCase();
                    $(".contact").each(function () {
                        var contactName = $(this).find(".name").text().toLowerCase();
                        if (contactName.includes(searchInput)) {
                            $(this).show();
                        } else {
                            $(this).hide();
                        }
                    });
                }

                // Initial filtering based on the search input
                filterContacts();

                // Add an event listener for the search input field
                $("#contact-search").on("input", function () {
                    filterContacts();
                });
            });

        </script>
        <!--Ajax request script ends-->   


    </body>
</html>              