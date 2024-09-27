<%-- 
    Document   : dentLogin
    Created on : Dec 3, 2023, 5:49:00 PM
    Author     : sault
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dent.Dentist"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="script.js" defer></script>
        <title>Dentist Login</title>
    </head>
    <body>
        
         <form action="dentLoginServlet" method="POST">
        <h1>Dentist Login</h1>
        <h3 for="userId">ID: </h3><input type="Text" name ="userId"/>
        <h3 for="passWd">Password: </h3><input type="password" name ="passWd" id="pass" />
        <input type="submit" value="Login"/>
        <input type="reset" value="Clear" />
    </form>        
    </body>
</html>

