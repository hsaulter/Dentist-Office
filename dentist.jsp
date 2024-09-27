<%-- 
    Document   : dentist
    Created on : Dec 3, 2023, 6:47:33 PM
    Author     : sault
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@page import="dent.Dentist"%>
<%@page import="dent.Appointments"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dentist Page</title>
    </head>
    <body>
        <h1>Dentist Portal</h1>
        <br>
<h3>Dentist Account Information</h3>
<br>
<p>Name: ${d1.dentistFn} ${d1.dentistLn}</p>
<p>Email: ${d1.dentistEm}</p>
<p>Dentist ID: ${d1.dentistId}</p>
<p>Office: ${d1.dentistOff}</p>
<form action="dentapptServlet" method="post">
    Update Password: <input type="text" name="updatedPwd">
    <br>
    Update First Name: <input type="text" name="updatedFn">
    <br>
    Update Last Name: <input type="text" name="updatedLn">
    <br>
    Update E-mail: <input type="text" name="updatedEm">
    <br>
       Update Office: <input type="text" name="updatedOff">
    <br>
     <input type="hidden" name="dentistId" value="${d1.dentistId}">
    <input type="submit" value="Update">
</form>

<a href="dentapptServlet?dentistId=${d1.dentistId}">Click here to View Appointments</a>



    </body>
</html>

