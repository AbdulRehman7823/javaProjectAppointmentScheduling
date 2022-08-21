/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scheduleproject.controller;

import com.scheduleproject.App;
import com.scheduleproject.DBA.*;
import com.scheduleproject.model.Contact;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * FXML Controller class
 *
 * @author abdul
 */
public class ReportsViewController implements Initializable {


    @FXML
    public AnchorPane tablePane;
    @FXML
    private RadioButton contactScheduleBtn;
    @FXML
    private ToggleGroup tg1;
    @FXML
    private RadioButton byTypeBtn;
    @FXML
    private RadioButton byMonths;
    @FXML
    private RadioButton byCountryBtn;
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private Button cancelBtn;
    @FXML
    private Button logoutBtn;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        AppointmentViewController.flag = 1;
        CustomerViewController.flag = 1;
        this.contactScheduleBtn.fire();
    }

    @FXML
    private void contactScheduleBtnAction(ActionEvent event) {
        try {
            AppointmentViewController.appointments = AppointmentDBA.getAllAppointments();
            tablePane.getChildren().clear();
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("AppointmentView.fxml"));
            tablePane.getChildren().add(fxmlLoader.load());
            if (this.comboBox.getItems().size() > 0)
                this.comboBox.getItems().clear();
            this.comboBox.getItems().addAll(
                    ContactDBA.getAllContacts().stream().map((Contact c) -> c.toString()).collect(Collectors.toList())
            );
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void byTypeBtnAction(ActionEvent event) {
        try {
            CustomerViewController.customerList = CustomerDBA.getAllCustomers();
            tablePane.getChildren().clear();
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("CustomerView.fxml"));
            tablePane.getChildren().add(fxmlLoader.load());
            if (this.comboBox.getItems().size() > 0)
                this.comboBox.getItems().clear();
            this.comboBox.getItems().addAll(
                    AppointmentDBA.getAllAppointments().stream().map(appointment -> appointment.getType()).collect(Collectors.toList())
            );
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void byMonthsAction(ActionEvent event) {
        try {
            CustomerViewController.customerList = CustomerDBA.getAllCustomers();
            tablePane.getChildren().clear();
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("CustomerView.fxml"));
            tablePane.getChildren().add(fxmlLoader.load());
            if (this.comboBox.getItems().size() > 0)
                this.comboBox.getItems().clear();
            this.comboBox.getItems().addAll(
                    Arrays.stream(Month.values()).map(month -> month.name()).collect(Collectors.toList())
            );

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void byCountryBtnAction(ActionEvent event) {
        try {
            CustomerViewController.customerList = CustomerDBA.getAllCustomers();
            tablePane.getChildren().clear();
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("CustomerView.fxml"));
            tablePane.getChildren().add(fxmlLoader.load());
            if (this.comboBox.getItems().size() > 0)
                this.comboBox.getItems().clear();
            this.comboBox.getItems().addAll(
                    CountryDBA.getAllCountries().stream().map(c -> c.getCountryName()).collect(Collectors.toList())
            );

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void comboBoxAction(ActionEvent event) {

        if (comboBox.getSelectionModel().getSelectedItem() != null) {
            if (this.contactScheduleBtn.isSelected()) {

                int id = Integer.parseInt(comboBox.getSelectionModel().getSelectedItem());
                try {
                    AppointmentViewController.appointments = AppointmentDBA.getAllAppointmentsByContact(id);
                    if (AppointmentViewController.appointments.size() == 0) {
                        AppointmentViewController.appointments = new ArrayList<>();
                    }
                    tablePane.getChildren().clear();
                    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("AppointmentView.fxml"));
                    tablePane.getChildren().add(fxmlLoader.load());
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }

            } else if (this.byTypeBtn.isSelected()) {

                String type = comboBox.getSelectionModel().getSelectedItem();

                try {
                    CustomerViewController.customerList = ReportDBA.getCustomersByType(type);
                    if (CustomerViewController.customerList.size() == 0)
                        CustomerViewController.customerList = new ArrayList<>();
                    tablePane.getChildren().clear();
                    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("CustomerView.fxml"));
                    tablePane.getChildren().add(fxmlLoader.load());
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }

            } else if (this.byMonths.isSelected()) {


                Month month = Month.valueOf(comboBox.getSelectionModel().getSelectedItem());

                try {
                    CustomerViewController.customerList = CustomerDBA.getAllCustomersByMonth(month);
                    if (CustomerViewController.customerList.size() == 0)
                        CustomerViewController.customerList = new ArrayList<>();
                    tablePane.getChildren().clear();
                    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("CustomerView.fxml"));
                    tablePane.getChildren().add(fxmlLoader.load());
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }


            } else if (this.byCountryBtn.isSelected()) {
                String country = comboBox.getSelectionModel().getSelectedItem();
                try {
                    CustomerViewController.customerList = ReportDBA.getCustomersByCountry(country);
                    if (CustomerViewController.customerList.size() == 0)
                        CustomerViewController.customerList = new ArrayList<>();
                    tablePane.getChildren().clear();
                    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("CustomerView.fxml"));
                    tablePane.getChildren().add(fxmlLoader.load());
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }

    @FXML
    private void cancelBtnAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("MainView.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage st = (Stage) this.contactScheduleBtn.getScene().getWindow();
            st.setScene(scene);
            st.setTitle("Appointment Management System");
            st.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void logoutBtnAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("StartUpView.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage st = (Stage) this.contactScheduleBtn.getScene().getWindow();
            st.setScene(scene);
            st.setTitle("Appoinments Management System");
            st.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
