package com.scheduleproject.DBA;

import com.scheduleproject.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDBA {


    public static User currentUser;

    public static User checkUser(String username, String password) {
        User user = null;

        for (User u : getAllUsers()) {
            if (u.getUserName().equalsIgnoreCase(username) &&
                    u.getPassword().equals(password)) {
                user = u;
                break;

            }
        }

        return user;
    }

    public static List<User> getAllUsers() {

        Connection con = SQLConnection.con;
        PreparedStatement stmt = null;
        List<User> ls = new ArrayList<>();
        String query = "Select * from users";
        try {
            stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                User user = new User(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getTimestamp(4).toLocalDateTime(),
                        rs.getString(5),
                        rs.getTimestamp(6).toLocalDateTime(),
                        rs.getString(7)
                );

                ls.add(user);
            }

            return ls;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

}
