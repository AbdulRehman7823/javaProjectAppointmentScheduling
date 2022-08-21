package com.scheduleproject.DBA;

import com.scheduleproject.model.Contact;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContactDBA {

    public static List<Contact> getAllContacts() {
        Connection con = SQLConnection.con;
        PreparedStatement stmt = null;
        List<Contact> ls = new ArrayList<>();
        String query = "Select * from contacts";
        try {
            stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Contact contact = new Contact(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3)
                );
                ls.add(contact);
            }

            return ls;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }
}
