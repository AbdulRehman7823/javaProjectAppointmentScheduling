package com.scheduleproject.DBA;

import com.scheduleproject.model.Country;
import com.scheduleproject.model.Division;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CountryDBA {

    public static List<Country> getAllCountries() {
        Connection con = SQLConnection.con;
        PreparedStatement stmt = null;
        List<Country> ls = new ArrayList<>();
        String query = "Select * from countries";
        try {
            stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Country country = new Country(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getTimestamp(3).toLocalDateTime(),
                        rs.getString(4),
                        rs.getTimestamp(5).toLocalDateTime(),
                        rs.getString(6)
                );

                ls.add(country);
            }

            return ls;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }


    public static List<Division> getAllDivisionByCountry(int id) {
        Connection con = SQLConnection.con;
        PreparedStatement stmt = null;
        List<Division> ls = new ArrayList<>();
        String query = "Select * from first_level_divisions where Country_ID=?";
        try {
            stmt = con.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Division division = new Division(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getTimestamp(3).toLocalDateTime(),
                        rs.getString(4),
                        rs.getTimestamp(5).toLocalDateTime(),
                        rs.getString(6),
                        rs.getInt(7)
                );

                ls.add(division);
            }

            return ls;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }


    public static Division getDivisionById(int id) {
        Connection con = SQLConnection.con;
        PreparedStatement stmt = null;
        Division division = null;
        String query = "Select * from first_level_divisions where Division_ID=?";
        try {
            stmt = con.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                division = new Division(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getTimestamp(3).toLocalDateTime(),
                        rs.getString(4),
                        rs.getTimestamp(5).toLocalDateTime(),
                        rs.getString(6),
                        rs.getInt(7)
                );
            }

            return division;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

}
