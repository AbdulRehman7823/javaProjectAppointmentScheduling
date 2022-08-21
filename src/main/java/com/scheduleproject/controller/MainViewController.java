/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scheduleproject.controller;

import com.scheduleproject.App;
import com.scheduleproject.DBA.AppointmentDBA;
import com.scheduleproject.DBA.CustomerDBA;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {

    @FXML
    private RadioButton viewCustomerBtn;
    @FXML
    private ToggleGroup tg;
    @FXML
    private RadioButton viewByWeekBtn;
    @FXML
    private RadioButton viewByMonthBtn;
    @FXML
    private RadioButton viewAllBtn;
    @FXML
    private DatePicker mainDatePicker;
    @FXML
    private AnchorPane mainViewPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        AppointmentViewController.flag = 0;
        CustomerViewController.flag = 0;
        this.viewCustomerBtn.fire();
    }

    @FXML
    private void viewCustomerBtnAction(ActionEvent event) {

        try {
            CustomerViewController.customerList = CustomerDBA.getAllCustomers();
            mainViewPane.getChildren().clear();
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("CustomerView.fxml"));
            mainViewPane.getChildren().add(fxmlLoader.load());

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void viewByWeekBtnAction(ActionEvent event) {
        try {
            AppointmentViewController.appointments = AppointmentDBA.getAllAppointmentsByWeek();
            mainViewPane.getChildren().clear();
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("AppointmentView.fxml"));
            mainViewPane.getChildren().add(fxmlLoader.load());

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void viewByMonthBtnAction(ActionEvent event) {
        try {
            AppointmentViewController.appointments = AppointmentDBA.getAllAppointmentsByMonth(LocalDate.now().getMonth());
            mainViewPane.getChildren().clear();
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("AppointmentView.fxml"));
            mainViewPane.getChildren().add(fxmlLoader.load());

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void viewAllBtnAction(ActionEvent event) {

        try {
            AppointmentViewController.appointments = AppointmentDBA.getAllAppointments();
            mainViewPane.getChildren().clear();
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("AppointmentView.fxml"));
            mainViewPane.getChildren().add(fxmlLoader.load());

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void mainDatePickerAction(ActionEvent event) {

        if (this.viewCustomerBtn.isSelected()) {

            try {
                LocalDate date = this.mainDatePicker.getValue();
                CustomerViewController.customerList = CustomerDBA.getAllCustomersByDate(date);
                mainViewPane.getChildren().clear();
                FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("CustomerView.fxml"));
                mainViewPane.getChildren().add(fxmlLoader.load());

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            try {
                LocalDate date = this.mainDatePicker.getValue();
                AppointmentViewController.appointments = AppointmentDBA.getAllAppointmentsByDate(date);
                if (AppointmentViewController.appointments.size() == 0) {
                    AppointmentViewController.appointments = new ArrayList<>();
                }
                mainViewPane.getChildren().clear();
                FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("AppointmentView.fxml"));
                mainViewPane.getChildren().add(fxmlLoader.load());

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }


}
