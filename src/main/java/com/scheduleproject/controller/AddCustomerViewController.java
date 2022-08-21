/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scheduleproject.controller;

import com.scheduleproject.App;
import com.scheduleproject.DBA.CountryDBA;
import com.scheduleproject.DBA.CustomerDBA;
import com.scheduleproject.DBA.UserDBA;
import com.scheduleproject.model.Country;
import com.scheduleproject.model.Customer;
import com.scheduleproject.model.Division;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author abdul
 */
public class AddCustomerViewController implements Initializable {
    @FXML
    public Label title;
    @FXML
    public Label nameErrL;
    @FXML
    public Label addressErrL;
    @FXML
    public Label postalErrL;
    @FXML
    public Label phoneErrorL;

    @FXML
    public Label countryErrL;
    @FXML
    private TextField idTf;
    @FXML
    private TextField nameTf;
    @FXML
    private TextField addressTf;
    @FXML
    private TextField postalTf;
    @FXML
    private TextField phoneTf;
    @FXML
    private ComboBox<Country> countryComboBox;
    @FXML
    private ComboBox<Division> divisionComboBox;
    @FXML
    private Button saveBtn;
    @FXML
    private Button cancelBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        countryComboBox.getItems().addAll(CountryDBA.getAllCountries());
        this.idTf.setText(String.valueOf(CustomerDBA.getAllCustomers().size() + 1));
        idTf.setDisable(false);

    }

    @FXML
    private void countryComboBoxAction(ActionEvent event) {
        divisionComboBox.getItems().clear();
        divisionComboBox.getItems().addAll(
                CountryDBA.getAllDivisionByCountry(
                        countryComboBox.getSelectionModel().getSelectedItem().getCountryId()));


    }

    @FXML
    private void divisionComboBoxAction(ActionEvent event) {
    }

    @FXML
    private void saveBtnAction(ActionEvent event) {
        String id = idTf.getText();
        String name = nameTf.getText();
        String address = this.addressTf.getText();
        String postal = this.postalTf.getText();
        String phone = this.phoneTf.getText();
        Division division = this.divisionComboBox.getSelectionModel().getSelectedItem();
        LocalDateTime createdDate = LocalDateTime.now();
        String createdBy = UserDBA.currentUser.getUserName();

        int counter = 0;
        if (name.equals("")) {
            nameTf.setStyle("-fx-border-color:red;-fx-border-width:2px");
            nameErrL.setText("Name Should not be Empty!");
        } else {
            nameTf.setStyle("-fx-border-color:green;-fx-border-width:2px");
            nameErrL.setText("");
            counter++;
        }

        if (address.equals("")) {
            addressTf.setStyle("-fx-border-color:red;-fx-border-width:2px");
            addressErrL.setText("Address Should not be Empty!");
        } else {
            addressTf.setStyle("-fx-border-color:green;-fx-border-width:2px");
            addressErrL.setText("");
            counter++;
        }

        if (phone.equals("")) {
            phoneTf.setStyle("-fx-border-color:red;-fx-border-width:2px");
            phoneErrorL.setText("Phone Number Should not be Empty!");
        } else {
            phoneTf.setStyle("-fx-border-color:green;-fx-border-width:2px");
            phoneErrorL.setText("");
            counter++;
        }

        if (postal.equals("")) {
            postalTf.setStyle("-fx-border-color:red;-fx-border-width:2px");
            postalErrL.setText("Postal Code Should not be Empty!");
        } else {
            postalTf.setStyle("-fx-border-color:green;-fx-border-width:2px");
            postalErrL.setText("");
            counter++;
        }

        if (division == null) {
            divisionComboBox.setStyle("-fx-border-color:red;-fx-border-width:2px");
            countryErrL.setText("Division Selection is Mandatory!");
        } else {
            divisionComboBox.setStyle("-fx-border-color:green;-fx-border-width:2px");
            countryErrL.setText("");
            counter++;
        }

        int cid = Integer.parseInt(id);
        if (counter == 5) {
            Customer customer = new Customer(
                    cid, name, address, postal, phone, createdDate, createdBy, createdDate, createdBy, division.getDivisionId()
            );

            boolean isAdded = CustomerDBA.addCustomer(customer);
            if (isAdded) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Customer Registration");
                alert.setContentText("Customer " + name + " is Added Successfully");
                Optional<ButtonType> op = alert.showAndWait();
                if (op.get() == ButtonType.OK) {
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("MainView.fxml"));
                        Scene scene = new Scene(fxmlLoader.load());
                        Stage st = (Stage) this.divisionComboBox.getScene().getWindow();
                        st.setScene(scene);
                        st.setTitle("Appointment Management System");
                        st.show();
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Customer Registration");
                alert.setContentText("There is Some DataBase Error While Insertion!!!!!");
                alert.show();
            }

        }

    }

    @FXML
    private void cancelBtnAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("MainView.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage st = (Stage) this.divisionComboBox.getScene().getWindow();
            st.setScene(scene);
            st.setTitle("Appointment Management System");
            st.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
