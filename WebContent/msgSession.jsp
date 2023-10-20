

<%@page import="com.hit.beans.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    int reciever = Integer.parseInt(request.getParameter("reciever"));
    RecieverBean rec = new RecieverBean(reciever);
    session.setAttribute("current_reciever", rec);
%>