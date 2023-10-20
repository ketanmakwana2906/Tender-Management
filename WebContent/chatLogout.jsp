<%
    String userRole = (String) session.getAttribute("user");

    if (userRole != null) {
        if (userRole.equals("admin")) {
            response.sendRedirect("adminHome.jsp");
        } else if (userRole.equals("accountant")) {
            response.sendRedirect("AccountantHome.jsp");
        } else if (userRole.equals("user")) {
            response.sendRedirect("vendorHome.jsp");
        } else {
            // Handle other cases or provide a default redirection
            response.sendRedirect("index.jsp");
        }
    } else {
        // Handle the case where the "user" session attribute is not set
        response.sendRedirect("index.jsp");
    }
%>
