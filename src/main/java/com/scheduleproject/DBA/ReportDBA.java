package com.scheduleproject.DBA;

import com.scheduleproject.model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReportDBA {

    private static final String cutomersTable = "customers";
    private static final String appointmentTable = "appointments";

    public static List<Customer> getCustomersByType(String type) {

        Connection con = SQLConnection.con;
        PreparedStatement stmt = null;
        String query = "SELECT * FROM " + cutomersTable + " where Customer_ID IN (select Customer_ID From " + appointmentTable + " where Type=?)";

        List<Customer> customers = new ArrayList<>();
        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, type);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Customer cs = new Customer(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getTimestamp(6).toLocalDateTime(),
                        rs.getString(7),
                        rs.getTimestamp(8).toLocalDateTime(),
                        rs.getString(9),
                        rs.getInt(10));
                customers.add(cs);
            }
            return customers;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }


    public static List<Customer> getCustomersByCountry(String country) {


        Connection con = SQLConnection.con;
        PreparedStatement stmt = null;
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT * FROM " + cutomersTable + " where Division_ID IN (select Division_ID from first_level_divisions where Country_ID IN (select Country_ID from countries where Country=?))";

        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, country);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Customer cs = new Customer(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getTimestamp(6).toLocalDateTime(),
                        rs.getString(7),
                        rs.getTimestamp(8).toLocalDateTime(),
                        rs.getString(9),
                        rs.getInt(10));
                customers.add(cs);
            }
            return customers;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }


}
