package com.wipro.flight.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import com.wipro.flight.bean.FlightBookingBean;
import com.wipro.flight.util.DBUtil;

public class FlightBookingDAO {

    public String createRecord(FlightBookingBean bookingBean) {

        Connection connection = DBUtil.getDBConnection();
        String query = "INSERT INTO FLIGHTBOOK_TBL VALUES (?,?,?,?,?,?,?)";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, bookingBean.getRecordId());
            ps.setString(2, bookingBean.getPassengerName());
            ps.setString(3, bookingBean.getFlightNumber());
            ps.setDate(4, new Date(bookingBean.getTravelDate().getTime()));
            ps.setString(5, bookingBean.getSeatNo());
            ps.setString(6, bookingBean.getTicketNo());
            ps.setString(7, bookingBean.getRemarks());

            int res = ps.executeUpdate();

            if (res > 0) {
                return bookingBean.getRecordId();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "FAIL";
    }

    public FlightBookingBean fetchRecord(String passengerName, Date travelDate) {

        Connection connection = DBUtil.getDBConnection();
        String query = "SELECT * FROM FLIGHTBOOK_TBL WHERE PASSENGERNAME=? AND TRAVEL_DATE=?";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, passengerName);
            ps.setDate(2, travelDate);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                FlightBookingBean bean = new FlightBookingBean();

                bean.setRecordId(rs.getString("RECORDID"));
                bean.setPassengerName(rs.getString("PASSENGERNAME"));
                bean.setFlightNumber(rs.getString("FLIGHTNUMBER"));
                bean.setTravelDate(rs.getDate("TRAVEL_DATE"));
                bean.setSeatNo(rs.getString("SEATNO"));
                bean.setTicketNo(rs.getString("TICKETNO"));
                bean.setRemarks(rs.getString("REMARKS"));

                return bean;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String generateRecordID(String passengerName, Date travelDate) {

        String recordId = "";
        Connection connection = DBUtil.getDBConnection();
        String query = "SELECT FLIGHTBOOK_SEQ.NEXTVAL FROM dual";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            DateFormat format = new SimpleDateFormat("yyyyMMdd");
            String datePart = format.format(travelDate);
            String namePart = passengerName.substring(0, 2).toUpperCase();

            int seq = 0;
            if (rs.next()) {
                seq = rs.getInt(1);
            }

            String seqPart = String.format("%02d", seq);
            recordId = datePart + namePart + seqPart;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return recordId;
    }

    public boolean recordExists(String passengerName, Date travelDate) {

        Connection connection = DBUtil.getDBConnection();
        String query = "SELECT RECORDID FROM FLIGHTBOOK_TBL WHERE PASSENGERNAME=? AND TRAVEL_DATE=?";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, passengerName);
            ps.setDate(2, travelDate);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<FlightBookingBean> fetchAllRecords() {

        List<FlightBookingBean> list = new ArrayList<>();
        Connection connection = DBUtil.getDBConnection();
        String query = "SELECT * FROM FLIGHTBOOK_TBL";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                FlightBookingBean bean = new FlightBookingBean();

                bean.setRecordId(rs.getString("RECORDID"));
                bean.setPassengerName(rs.getString("PASSENGERNAME"));
                bean.setFlightNumber(rs.getString("FLIGHTNUMBER"));
                bean.setTravelDate(rs.getDate("TRAVEL_DATE"));
                bean.setSeatNo(rs.getString("SEATNO"));
                bean.setTicketNo(rs.getString("TICKETNO"));
                bean.setRemarks(rs.getString("REMARKS"));

                list.add(bean);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
