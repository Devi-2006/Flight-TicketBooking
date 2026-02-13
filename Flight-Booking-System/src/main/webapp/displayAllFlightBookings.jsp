<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.List" %>
	<%@ page import="com.wipro.flight.bean.FlightBookingBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All Flight Bookings</title>
</head>
<body>
<h2>All Flight Booking Records</h2>
<%
    List<FlightBookingBean> list =
        (List<FlightBookingBean>) request.getAttribute("flightList");
    String message = (String) request.getAttribute("message");
    if (list != null && !list.isEmpty()) {
%>
        <table border="1" cellpadding="5">
            <tr>
                <th>Record ID</th>
                <th>Passenger Name</th>
                <th>Travel Date</th>
            </tr>
<%
        for (FlightBookingBean bean : list) {
%>
            <tr>
                <td><%= bean.getRecordId() %></td>
                <td><%= bean.getPassengerName() %></td>
                <td><%= bean.getTravelDate() %></td>
            </tr>
<%
        }
%>
        </table>
<%
    } else {
%>
        <h3 style="color:red;">
            <%= message != null ? message : "No records available!" %>
        </h3>
<%
    }
%>
<br>
<a href="menu.html">Back to Menu</a>
</body>
</html>
