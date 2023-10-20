
<%@page import="com.hit.beans.*"%>
<%@page import="com.hit.utility.*"%>
<%
    RecieverBean rec = (RecieverBean) session.getAttribute("current_reciever");
    UserBean user = (UserBean) session.getAttribute("current_user");
    if (user == null) {
        response.sendRedirect("index.jsp");
    }
%>
<%@page import="java.util.List, java.sql.*"%>

<%@page import="com.hit.dao.*"%>

<%

    userDaoImpl re = new userDaoImpl();
    UserBean r = re.readReciever(rec.getReciever());
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <%
            messageDaoImpl md = new messageDaoImpl();
            List<MessageBean> list = md.readMessage(rec.getReciever(), user.getUserId());

            for (MessageBean msg : list) {
                if (msg.getSender() == user.getUserId()) {
        %>

    <li class="replies">
        
        <p><%= msg.getMessage()%></p>
    </li>
    <%
    } else {
    %>
    <li class="sent">
        
        <p><%= msg.getMessage()%></p>
    </li>
    <%
            }
        }
    %>

</body>
</html>