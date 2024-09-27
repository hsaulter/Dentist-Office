<%-- 
    Document   : LoginError
    Created on : Dec 4, 2023, 11:02:31 PM
    Author     : sault
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            
            String id = request.getParameter("id");
            
         HttpSession ses1 = request.getSession();
        ses1.setAttribute("id", id);
        String errMsg = "Error! Unable to find ID ";
        System.out.println("Error! Unable to find ID: " + id);
        
        
        %>
        <%= errMsg %>
    </body>
</html>
