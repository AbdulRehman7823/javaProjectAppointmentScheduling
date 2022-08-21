package com.scheduleproject.DBA;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.TimeZone;


public class SQLConnection {


    private final static String url = "jdbc:mysql://localhost:3306/";
    private final static String database = "client_schedule";
    private final static String userName = "root";
    private final static String password = "root";

    private final static String zoneString = "?useLegacyDatetimeCode=false&serverTimezone=";

    public static Connection con = null;


    public static void makeConnection() {
        Calendar now = Calendar.getInstance();
        TimeZone timeZone = now.getTimeZone();
        String timezone = timeZone.getID();
        System.out.println(timezone);

        try {
            con = DriverManager.getConnection(url + database + "?user=root&password=root&userUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetime=false&serverTimezone=" + timezone);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }


}
