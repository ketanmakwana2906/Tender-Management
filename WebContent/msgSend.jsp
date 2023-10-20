<%@page import="com.hit.beans.*"%>
<%@page import="com.hit.dao.*"%>


<%
    RecieverBean rec = (RecieverBean) session.getAttribute("current_reciever");
    UserBean user = (UserBean) session.getAttribute("current_user");
    if (user == null) {
        response.sendRedirect("index.jsp");
    }
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String message = request.getParameter("msg");

    messageDaoImpl md = new messageDaoImpl();
    md.saveMessage(user.getUserId(), rec.getReciever(), message);
%>