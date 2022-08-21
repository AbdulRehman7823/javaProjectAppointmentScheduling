package com.scheduleproject.DBA;

import com.scheduleproject.model.Appointment;

import java.sql.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class AppointmentDBA {

    private static final String tableName = "appointments";

    public static List<Appointment> getAllAppointments() {

        Connection con = SQLConnection.con;
        PreparedStatement stmt = null;
        List<Appointment> appointments = new ArrayList<>();

        try {
            stmt = con.prepareStatement("select * from " + tableName);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                Appointment ap = new Appointment(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getTimestamp(6).toLocalDateTime(),
                        rs.getTimestamp(7).toLocalDateTime(),
                        rs.getTimestamp(8).toLocalDateTime(),
                        rs.getString(9),
                        rs.getTimestamp(10).toLocalDateTime(),
                        rs.getString(11),
                        rs.getInt(12),
                        rs.getInt(13),
                        rs.getInt(14)
                );
                appointments.add(ap);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return appointments;
    }


    public static boolean updateAppointment(Appointment appointment) {
        Connection con = SQLConnection.con;
        PreparedStatement stmt = null;
        String query = "Update " + tableName + "  SET Title=?, Description=?, Location=?, type=?, Start=?, End=?, Created_Date=?," +
                "Created_By=?, Last_Updated=?, Last_Updated_By=?, Customer_ID=?, User_ID=?, Contact_ID=? " +
                "WHERE Appointment_ID=?";
        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, appointment.getTitle());
            stmt.setString(2, appointment.getDescription());
            stmt.setString(3, appointment.getLocation());
            stmt.setString(4, appointment.getType());
            stmt.setTimestamp(5, Timestamp.valueOf(appointment.getStartDate()));
            stmt.setTimestamp(6, Timestamp.valueOf(appointment.getEndDate()));
            stmt.setTimestamp(7, Timestamp.valueOf(appointment.getCreatedDate()));
            stmt.setString(8, appointment.getCreatedBy());
            stmt.setTimestamp(9, Timestamp.valueOf(appointment.getUpdatedDate()));
            stmt.setString(10, appointment.getUpdatedBy());
            stmt.setInt(11, appointment.getCustomerId());
            stmt.setInt(12, appointment.getUserId());
            stmt.setInt(13, appointment.getContactId());
            stmt.setInt(14, appointment.getAppointmentId());
            int result = stmt.executeUpdate();
            if (result == 0)
                return false;
            else
                return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public static boolean deleteAppointment(int id) {
        Connection con = SQLConnection.con;
        PreparedStatement stmt = null;
        String query = "Delete from " + tableName + " where Appointment_ID=" + id;
        try {
            stmt = con.prepareStatement(query);
            int ans = stmt.executeUpdate();
            if (ans == 0)
                return false;
            else
                return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }


    }

    public static boolean addAppointment(Appointment appointment) {
        Connection con = SQLConnection.con;
        PreparedStatement stmt = null;
        String query = "insert into " + tableName + " " +
                "(Title, Description, Location, type, Start, End, Create_Date,Created_By, Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID)"
                + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            stmt = con.prepareStatement(query);

            stmt.setString(1, appointment.getTitle());
            stmt.setString(2, appointment.getDescription());
            stmt.setString(3, appointment.getLocation());
            stmt.setString(4, appointment.getType());
            stmt.setTimestamp(5, Timestamp.valueOf(appointment.getStartDate()));
            stmt.setTimestamp(6, Timestamp.valueOf(appointment.getEndDate()));
            stmt.setTimestamp(7, Timestamp.valueOf(appointment.getCreatedDate()));
            stmt.setString(8, appointment.getCreatedBy());
            stmt.setTimestamp(9, Timestamp.valueOf(appointment.getUpdatedDate()));
            stmt.setString(10, appointment.getUpdatedBy());
            stmt.setInt(11, appointment.getCustomerId());
            stmt.setInt(12, appointment.getUserId());
            stmt.setInt(13, appointment.getContactId());
            int ans = stmt.executeUpdate();
            if (ans == 0)
                return false;
            return true;


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public static List<Appointment> getAllAppointmentsByContact(int contactID) {
        Connection con = SQLConnection.con;
        PreparedStatement stmt = null;
        List<Appointment> appointments = new ArrayList<>();

        try {
            stmt = con.prepareStatement("select * from " + tableName + " where Contact_ID=?");
            stmt.setInt(1, contactID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                Appointment ap = new Appointment(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getTimestamp(6).toLocalDateTime(),
                        rs.getTimestamp(7).toLocalDateTime(),
                        rs.getTimestamp(8).toLocalDateTime(),
                        rs.getString(9),
                        rs.getTimestamp(10).toLocalDateTime(),
                        rs.getString(11),
                        rs.getInt(12),
                        rs.getInt(13),
                        rs.getInt(14)
                );
                appointments.add(ap);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return appointments;
    }

    public static List<Appointment> getAllAppointmentsByMonth(Month month) {

        List<Appointment> filteredList = getAllAppointments().stream().filter(appointment ->
                (appointment.getCreatedDate().getMonth().equals(month))).collect(Collectors.toList());
        return filteredList;
    }

    public static List<Appointment> getAllAppointmentsByDate(LocalDate date) {
        List<Appointment> filteredList = getAllAppointments().stream().filter(appointment ->
                (appointment.getCreatedDate().toLocalDate().equals(date))).collect(Collectors.toList());
        return filteredList;
    }

    public static List<Appointment> getAllAppointmentsByWeek() {
        LocalDate date = LocalDate.now();
        date.minusDays(7);
        List<Appointment> filteredList = getAllAppointments().stream().filter(appointment ->
                (appointment.getCreatedDate().toLocalDate().isAfter(date))).collect(Collectors.toList());
        return filteredList;
    }
}
