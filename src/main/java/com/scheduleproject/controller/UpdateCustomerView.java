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

public class UpdateCustomerView implements Initializable {

    public static Customer currentCustomer;
    @FXML
    private Label title;
    @FXML
    private TextField idTf;
    @FXML
    private TextField nameTf;
    @FXML
    private Label nameErrL;
    @FXML
    private TextField addressTf;
    @FXML
    private Label addressErrL;
    @FXML
    private TextField postalTf;
    @FXML
    private Label postalErrL;
    @FXML
    private TextField phoneTf;
    @FXML
    private Label phoneErrorL;
    @FXML
    private ComboBox<Country> countryComboBox;
    @FXML
    private ComboBox<Division> divisionComboBox;
    @FXML
    private Label countryErrL;
    @FXML
    private Button modify;
    @FXML
    private Button cancelBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        this.idTf.setText(currentCustomer.getCustomerId() + "");
        this.nameTf.setText(currentCustomer.getCustomerName());
        this.addressTf.setText(currentCustomer.getAddress());
        this.postalTf.setText(currentCustomer.getPostalCode());
        this.phoneTf.setText(currentCustomer.getPhoneNumber());
        this.divisionComboBox.getSelectionModel().select(CountryDBA.getDivisionById(currentCustomer.getDivisionId()));

        countryComboBox.getItems().addAll(CountryDBA.getAllCountries());
        idTf.setEditable(false);

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
    private void modifyBtnAction(ActionEvent event) {

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
                    cid, name, address, postal, phone, currentCustomer.getCreatedDate(), currentCustomer.getCreatedBy(), createdDate, createdBy, division.getDivisionId()
            );

            boolean isAdded = CustomerDBA.updateCustomer(customer);
            if (isAdded) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Customer Modification");
                alert.setContentText("Customer " + name + " is Updated Successfully");
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
                alert.setTitle("Customer Updation");
                alert.setContentText("There is Some DataBase Error While Updation!!!!!");
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
