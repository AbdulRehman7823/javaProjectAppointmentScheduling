/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scheduleproject.controller;

import com.scheduleproject.App;
import com.scheduleproject.DBA.AppointmentDBA;
import com.scheduleproject.model.Appointment;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author abdul
 */
public class AppointmentViewController implements Initializable {

    public static int flag;
    public static List<Appointment> appointments;
    public Button reportsBtn;
    public Button modify;
    @FXML
    public HBox bottomHBox;
    @FXML
    private AnchorPane appointmentsPane;
    @FXML
    private TableView<Appointment> appointmentsTableView;
    @FXML
    private TableColumn<Appointment, Integer> id;
    @FXML
    private TableColumn<Appointment, String> title;
    @FXML
    private TableColumn<Appointment, String> description;
    @FXML
    private TableColumn<Appointment, String> location;
    @FXML
    private TableColumn<Appointment, Integer> contact;
    @FXML
    private TableColumn<Appointment, String> type;
    @FXML
    private TableColumn<Appointment, LocalTime> startTime;
    @FXML
    private TableColumn<Appointment, LocalTime> endTime;
    @FXML
    private TableColumn<Appointment, LocalDateTime> startDate;
    @FXML
    private TableColumn<Appointment, LocalDateTime> endDate;
    @FXML
    private TableColumn<Appointment, Integer> customerId;
    @FXML
    private TableColumn<Appointment, Integer> userId;
    @FXML
    private Button reportBtn;
    @FXML
    private Button modifyBtn;
    @FXML
    private Button addBtn;
    @FXML
    private Button deleteBtn;
    @FXML
    private Button logoutBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if (flag == 0) {
            this.bottomHBox.setVisible(true);
        } else {
            this.bottomHBox.setVisible(false);
        }
        this.id.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("appointmentId"));
        this.title.setCellValueFactory(new PropertyValueFactory<Appointment, String>("title"));
        this.description.setCellValueFactory(new PropertyValueFactory<Appointment, String>("description"));
        this.location.setCellValueFactory(new PropertyValueFactory<Appointment, String>("location"));
        this.type.setCellValueFactory(new PropertyValueFactory<Appointment, String>("type"));
        this.startTime.setCellValueFactory(new PropertyValueFactory<Appointment, LocalTime>("startTime"));
        this.endTime.setCellValueFactory(new PropertyValueFactory<Appointment, LocalTime>("endTime"));
        this.startDate.setCellValueFactory(new PropertyValueFactory<Appointment, LocalDateTime>("startDate"));
        this.endDate.setCellValueFactory(new PropertyValueFactory<Appointment, LocalDateTime>("endDate"));
        this.contact.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("contactId"));
        this.customerId.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("customerId"));
        this.userId.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("userId"));

        for (Appointment appointment : appointments) {
            appointmentsTableView.getItems().add(appointment);
        }
    }

    @FXML
    private void reportBtnAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("ReportsView.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage st = (Stage) this.appointmentsTableView.getScene().getWindow();
            st.setScene(scene);
            st.setTitle("Resports Management");
            st.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void modifyBtnAction(ActionEvent event) {
        if (appointmentsTableView.getSelectionModel().getSelectedItem() != null) {
            try {
                UpdateAppointmentViewController.currentAppointment = appointmentsTableView.getSelectionModel().getSelectedItem();
                FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("UpdateAppointmentView.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage st = (Stage) this.appointmentsTableView.getScene().getWindow();
                st.setScene(scene);
                st.setTitle("Appointment Modification");
                st.show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @FXML
    private void addBtnAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("AddAppointmentView.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage st = (Stage) this.appointmentsTableView.getScene().getWindow();
            st.setScene(scene);
            st.setTitle("Add Appoinment Form");
            st.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    private void deleteBtnAction(ActionEvent event) {

        Appointment appointment = appointmentsTableView.getSelectionModel().getSelectedItem();
        if (appointment != null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Appointment Deletion");
            alert.setContentText("Are you Sure you want to Delete this Appointment");
            Optional<ButtonType> op = alert.showAndWait();
            if (op.get() == ButtonType.OK) {
                boolean isDeleted = AppointmentDBA.deleteAppointment(appointment.getAppointmentId());
                if (isDeleted) {
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setTitle("Appointment Deletion");
                    a.setContentText("Appoinment  is Deleted Successfully");
                    a.show();

                    appointments.remove(appointment);
                    appointmentsTableView.getItems().clear();
                    for (Appointment cs : appointments) {
                        appointmentsTableView.getItems().add(cs);
                    }
                } else {
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setTitle("Customer Deletion");
                    a.setContentText("There is Some SQL Error While Deleting this Customer");
                    a.show();

                }
            }
        }

    }

    @FXML
    private void logoutBtnAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("StartUpView.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage st = (Stage) this.appointmentsTableView.getScene().getWindow();
            st.setScene(scene);
            st.setTitle("Appoinments Management System");
            st.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
