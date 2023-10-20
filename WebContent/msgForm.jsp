

<%@page import="com.hit.beans.*"%>
<%
    RecieverBean rec = (RecieverBean) session.getAttribute("current_reciever");
    UserBean user = (UserBean) session.getAttribute("current_user");
    if (user == null) {
        response.sendRedirect("index.jsp");
    }
%>

<%@page import="com.hit.dao.*"%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>

        <form id="msgSendForm" name="msgSendForm" method="post" onsubmit="return false;">
            <input type="text" name="msg" id="msg" placeholder="Type a message..." />
            <i class="fa fa-paperclip attachment" aria-hidden="true"></i>
            <button class="submit" id="msgSendButton" onclick="msgSend()"><i class="fa fa-paper-plane" aria-hidden="true"></i></button>
        </form>