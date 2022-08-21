package com.scheduleproject.Application;

import com.scheduleproject.DBA.AppointmentDBA;
import com.scheduleproject.DBA.CustomerDBA;
import com.scheduleproject.DBA.ReportDBA;
import com.scheduleproject.DBA.SQLConnection;

import java.time.Month;

public class Test {
    public static void main(String[] args) {
        SQLConnection.makeConnection();
        System.out.println("All Customers");
        CustomerDBA.getAllCustomersByMonth(Month.SEPTEMBER).forEach(customer -> {
            System.out.println(customer);
        });
        System.out.println("_________________________________________________");
        System.out.println();

        System.out.println("All Appointments");
        AppointmentDBA.getAllAppointments().forEach(appointment -> {
            System.out.println(appointment);
        });

        System.out.println("__________________________________________________");
        System.out.println();

        System.out.println("All Customers By Country UK");
        ReportDBA.getCustomersByCountry("UK").forEach(customer -> {
            System.out.println(customer);
        });
        System.out.println("____________________________________________________");
        System.out.println();

        System.out.println("All Customers By Country Canada");
        ReportDBA.getCustomersByCountry("Canada").forEach(customer -> {
            System.out.println(customer);
        });
        System.out.println("____________________________________________________");
        System.out.println();

        System.out.println("All Customers By Type:De-Briefing");
        ReportDBA.getCustomersByType("De-Briefing").forEach(customer -> {
            System.out.println(customer);
        });
        System.out.println("____________________________________________________");
        System.out.println();

        System.out.println("All Customers By Type:Planning Session");
        ReportDBA.getCustomersByType("Planning Session").forEach(customer -> {
            System.out.println(customer);
        });
        System.out.println("____________________________________________________");
        System.out.println();


        System.out.println("All Appointments By Contact ID");
        AppointmentDBA.getAllAppointmentsByContact(2).forEach(appointment -> {
            System.out.println(appointment);
        });


    }
}
