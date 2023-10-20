

<%@page import="com.hit.beans.*"%>
<%
    RecieverBean rec = (RecieverBean) session.getAttribute("current_reciever");
    UserBean user = (UserBean) session.getAttribute("current_user");
    if (user == null) {
        response.sendRedirect("index.jsp");
    }
%>

<%

    userDaoImpl re = new userDaoImpl();
    UserBean r = re.readReciever(rec.getReciever());
%>


<%@page import="com.hit.dao.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

        <div class="contact-profile">
          <p>&nbsp; You chatting with : &nbsp;</p>
            <p> <%= r.getUserName()%></p>
            <div class="social-media">
                <i class="fa fa-facebook" aria-hidden="true"></i>
                <i class="fa fa-twitter" aria-hidden="true"></i>
                <i class="fa fa-instagram" aria-hidden="true"></i>
            </div>
        </div>
        