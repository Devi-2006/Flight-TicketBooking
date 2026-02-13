<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <title>View Flight Booking</title>
</head>
<body>
<h2>View Flight Booking</h2>
<form action="MainServlet" method="post">
    <input type="hidden" name="operation" value="viewRecord">
    <label>Passenger Name:</label>
    <input type="text" name="passengerName">
    <br><br>
    <label>Travel Date:</label>
    <input type="date" name="travelDate">
    <br><br>
    <input type="submit" value="View Booking">
    
</form>
<br>
<a href="menu.html">Back to Menu</a>
</body>
</html>
