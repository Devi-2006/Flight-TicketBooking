package com.wipro.flight.service;

import java.util.Date;
import java.util.List;

import com.wipro.flight.bean.FlightBookingBean;
import com.wipro.flight.dao.FlightBookingDAO;
import com.wipro.flight.util.InvalidInputException;

public class Administrator {

    public String addRecord(FlightBookingBean bookingBean) {

        FlightBookingDAO dao = new FlightBookingDAO();

        try {

            if (bookingBean == null || bookingBean.getPassengerName() == null || bookingBean.getTravelDate() == null) {
                throw new InvalidInputException();
            }

            if (bookingBean.getPassengerName().length() < 2) {
                return "INVALID PASSENGER NAME";
            }

            if (bookingBean.getTravelDate().before(new Date())) {
                return "INVALID DATE";
            }

            java.sql.Date sqlDate = new java.sql.Date(bookingBean.getTravelDate().getTime());

            if (dao.recordExists(bookingBean.getPassengerName(), sqlDate)) {
                return "ALREADY EXISTS";
            }

            String recordId = dao.generateRecordID(bookingBean.getPassengerName(), sqlDate);
            bookingBean.setRecordId(recordId);

            return dao.createRecord(bookingBean);

        } catch (InvalidInputException e) {
            return "INVALID INPUT";
        }
    }

    public FlightBookingBean viewRecord(String passengerName, Date travelDate) {

        FlightBookingDAO dao = new FlightBookingDAO();

        if (passengerName == null || travelDate == null) {
            return null;
        }

        java.sql.Date sqlDate = new java.sql.Date(travelDate.getTime());

        return dao.fetchRecord(passengerName, sqlDate);
    }

    public List<FlightBookingBean> viewAllRecords() {

        FlightBookingDAO dao = new FlightBookingDAO();
        return dao.fetchAllRecords();
    }
}
