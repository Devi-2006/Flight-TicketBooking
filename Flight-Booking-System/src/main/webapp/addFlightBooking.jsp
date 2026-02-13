<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Flight Booking</title>
</head>
<body>

<h2>Add Flight Booking</h2>

<form action="MainServlet" method="post">
    <input type="hidden" name="operation" value="newRecord">

    Passenger Name:
    <input type="text" name="passengerName" required>
    <br><br>

    Flight Number:
    <input type="text" name="flightNumber" required>
    <br><br>

    Travel Date:
    <input type="date" name="travelDate" required>
    <br><br>

    Seat No:
    <input type="text" name="seatNo" required>
    <br><br>

    Ticket No:
    <input type="text" name="ticketNo">
    <br><br>

    Remarks:
    <input type="text" name="remarks">
    <br><br>

    <input type="submit" value="Add Flight Booking">

</form>

</body>
</html>
