package com.wipro.flight.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.wipro.flight.bean.FlightBookingBean;
import com.wipro.flight.service.Administrator;

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public String addRecord(HttpServletRequest request) {
        try {

            String passengerName = request.getParameter("passengerName");
            String flightNumber = request.getParameter("flightNumber");
            String seatNo = request.getParameter("seatNo");
            String ticketNo = request.getParameter("ticketNo");
            String remarks = request.getParameter("remarks");

            String travelDateStr = request.getParameter("travelDate");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date travelDate = sdf.parse(travelDateStr);

            FlightBookingBean bean = new FlightBookingBean();
            bean.setPassengerName(passengerName);
            bean.setFlightNumber(flightNumber);
            bean.setSeatNo(seatNo);
            bean.setTicketNo(ticketNo);
            bean.setRemarks(remarks);
            bean.setTravelDate(travelDate);

            Administrator admin = new Administrator();
            return admin.addRecord(bean);

        } catch (Exception e) {
            return "INVALID INPUT";
        }
    }

    public FlightBookingBean viewRecord(HttpServletRequest request) {
        try {
            String passengerName = request.getParameter("passengerName");
            String travelDateStr = request.getParameter("travelDate");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date travelDate = sdf.parse(travelDateStr);

            Administrator admin = new Administrator();
            return admin.viewRecord(passengerName, travelDate);

        } catch (Exception e) {
            return null;
        }
    }

    public List<FlightBookingBean> viewAllRecords(HttpServletRequest request) {
        Administrator admin = new Administrator();
        return admin.viewAllRecords();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String operation = request.getParameter("operation");

        try {

            if (operation.equals("newRecord")) {

                String result = addRecord(request);

                if (result == null || result.equals("FAIL") || result.equals("INVALID INPUT")) {
                    response.sendRedirect("error.html");
                } else {
                    response.sendRedirect("success.html");
                }
            }

            else if (operation.equals("viewRecord")) {

                FlightBookingBean bean = viewRecord(request);

                if (bean == null) {
                    request.setAttribute("message",
                            "No matching records exists! Please try again!");
                    request.getRequestDispatcher("displayFlightBooking.jsp")
                            .forward(request, response);
                } else {
                    request.setAttribute("flight", bean);
                    request.getRequestDispatcher("displayFlightBooking.jsp")
                            .forward(request, response);
                }
            }

            else if (operation.equals("viewAllRecords")) {

                List<FlightBookingBean> list = viewAllRecords(request);

                if (list == null || list.isEmpty()) {
                    request.setAttribute("message", "No records available!");
                    request.getRequestDispatcher("displayAllFlightBookings.jsp")
                            .forward(request, response);
                } else {
                    request.setAttribute("flightList", list);
                    request.getRequestDispatcher("displayAllFlightBookings.jsp")
                            .forward(request, response);
                }
            }

        } catch (Exception e) {
            response.sendRedirect("error.html");
        }
    }
}
