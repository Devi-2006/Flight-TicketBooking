<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.wipro.flight.bean.FlightBookingBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Display Flight Booking</title>
</head>
<body>
<h2>Flight Booking Details</h2>
<%
    FlightBookingBean bean =(FlightBookingBean) request.getAttribute("flight");
    String message = (String) request.getAttribute("message");
    if (bean != null) {
%>
        Record ID: <%= bean.getRecordId() %> <br><br>
        Passenger Name: <%= bean.getPassengerName() %> <br><br>
        Travel Date: <%= bean.getTravelDate() %> <br><br>

<%
    } else {
%>
        <h3 style="color:red;">
            <%= message %>
        </h3>
<%
    }
%>
<br>
<a href="menu.html">Back to Menu</a>
</body>
</html>
