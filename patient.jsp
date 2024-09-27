<%-- 
    Document   : patient
    Created on : Dec 3, 2023, 6:47:17 PM
    Author     : sault
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@page import="dent.Patient"%>
<%@page import="dent.Appointments"%>
<% 
    Patient p1 = (Patient)request.getAttribute("p1");
    %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Patient Page</title>
    </head>
    <body>
        <h1>Patient Portal</h1>
        <br>
<h3>Patient Account Information</h3>
<br>
<p>Name: ${p1.patientFn} ${p1.patientLn}</p>
<p>Address: ${p1.patientAdd}</p>
<p>Email: ${p1.patientEm}</p>
<p>Insurance: ${p1.patientIns}</p>
<p>Patient ID: ${p1.patientId}</p>

<form action="apptServlet" method="post">
    Update Password: <input type="text" name="updatedPwd">
    <br>
    Update First Name: <input type="text" name="updatedFn">
    <br>
    Update Last Name: <input type="text" name="updatedLn">
    <br>
    Update E-mail: <input type="text" name="updatedEm">
    <br>
    Update Address: <input type="text" name="updatedAdd">
    <br>
    Update Insurance: <input type="text" name="updatedIns">
    <br>
     <input type="hidden" name="patientId" value="${p1.patientId}">
    <input type="submit" value="Update">
</form>

<a href="apptServlet?patientId=${p1.patientId}">Click here to View/Change Appointments</a>



    </body>
</html>
