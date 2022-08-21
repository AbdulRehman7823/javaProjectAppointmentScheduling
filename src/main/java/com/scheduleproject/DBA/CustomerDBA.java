package com.scheduleproject.DBA;

import com.scheduleproject.model.Customer;

import java.sql.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerDBA {

    private static final String tableName = "customers";

    public static List<Customer> getAllCustomers() {

        Connection con = SQLConnection.con;
        PreparedStatement stmt = null;
        List<Customer> customers = new ArrayList<>();

        try {
            stmt = con.prepareStatement("select * from " + tableName);
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
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return customers;
    }

    public static List<Customer> getCustomerWithCountry(String country) {

        Connection con = SQLConnection.con;
        PreparedStatement stmt = null;
        List<Customer> customers = new ArrayList<>();

        try {
            stmt = con.prepareStatement("select * from " + tableName + " where ");
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
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return customers;

    }

    public static boolean updateCustomer(Customer customer) {
        Connection con = SQLConnection.con;
        PreparedStatement stmt = null;
        String query = "Update " + tableName + "  SET Customer_Name=?, Address=?, Postal_Code=?, Phone=?,Last_Update=?,Last_Updated_By=?,Division_ID=? " +
                "WHERE Customer_ID=?";
        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, customer.getCustomerName());
            stmt.setString(2, customer.getAddress());
            stmt.setString(3, customer.getPostalCode());
            stmt.setString(4, customer.getPhoneNumber());
            stmt.setTimestamp(5, Timestamp.valueOf(customer.getUpdatedDate()));
            stmt.setString(6, customer.getUpdatedBy());
            stmt.setInt(7, customer.getDivisionId());
            stmt.setInt(8, customer.getCustomerId());

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

    public static boolean deleteCustomer(int id) {
        Connection con = SQLConnection.con;
        PreparedStatement stmt = null;
        String query = "Delete from " + tableName + " where Customer_ID=" + id;
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

    public static List<Customer> getAllCustomersByDate(LocalDate date) {
        List<Customer> filteredList = getAllCustomers().stream().filter(customer ->
                (customer.getCreatedDate().toLocalDate().equals(date))).collect(Collectors.toList());
        return filteredList;
    }

    public static boolean addCustomer(Customer customer) {
        Connection con = SQLConnection.con;
        PreparedStatement stmt = null;
        String query = "insert into " + tableName + " (Customer_Name, Address, Postal_Code, Phone, Create_Date,Created_By,Last_Update,Last_Updated_By,Division_ID)"
                + " values ( ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, customer.getCustomerName());
            stmt.setString(2, customer.getAddress());
            stmt.setString(3, customer.getPostalCode());
            stmt.setString(4, customer.getPhoneNumber());
            stmt.setTimestamp(5, Timestamp.valueOf(customer.getCreatedDate()));
            stmt.setString(6, customer.getCreatedBy());
            stmt.setTimestamp(7, Timestamp.valueOf(customer.getUpdatedDate()));
            stmt.setString(8, customer.getUpdatedBy());
            stmt.setInt(9, customer.getDivisionId());

            int ans = stmt.executeUpdate();
            if (ans == 0) {
                return false;
            }
            return true;


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

            return false;
        }
    }

    public static List<Customer> getAllCustomersByMonth(Month month) {

        List<Customer> filteredList = getAllCustomers().stream().filter(customer -> (customer.getCreatedDate().getMonth().equals(month))).collect(Collectors.toList());
        return filteredList;
    }
}
