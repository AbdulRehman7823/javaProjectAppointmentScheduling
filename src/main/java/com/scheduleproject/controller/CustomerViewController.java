/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scheduleproject.controller;

import com.scheduleproject.App;
import com.scheduleproject.DBA.CustomerDBA;
import com.scheduleproject.model.Customer;
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
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author abdul
 */
public class CustomerViewController implements Initializable {


    public static List<Customer> customerList;
    public static int flag;
    @FXML
    public HBox bottomHBox;
    @FXML
    private AnchorPane customerAnchorPane;
    @FXML
    private TableView<Customer> customerTableView;
    @FXML
    private TableColumn<Customer, Integer> customerId;
    @FXML
    private TableColumn<Customer, String> customerName;
    @FXML
    private TableColumn<Customer, String> customerAddress;
    @FXML
    private TableColumn<Customer, String> postalCode;
    @FXML
    private TableColumn<Customer, String> phoneNumber;
    @FXML
    private TableColumn<Customer, LocalDateTime> createdDate;
    @FXML
    private TableColumn<Customer, String> createdBy;
    @FXML
    private TableColumn<Customer, LocalDateTime> lastUpdate;
    @FXML
    private TableColumn<Customer, String> lastUpdatedBy;
    @FXML
    private TableColumn<Customer, Integer> state;
    @FXML
    private Button reportsBtn;
    @FXML
    private Button modify;
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
        if (flag == 0) {
            this.bottomHBox.setVisible(true);
        } else {
            this.bottomHBox.setVisible(false);
        }
        this.customerId.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("customerId"));
        this.customerName.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerName"));
        this.customerAddress.setCellValueFactory(new PropertyValueFactory<Customer, String>("address"));
        this.postalCode.setCellValueFactory(new PropertyValueFactory<Customer, String>("postalCode"));
        this.phoneNumber.setCellValueFactory(new PropertyValueFactory<Customer, String>("phoneNumber"));
        this.createdDate.setCellValueFactory(new PropertyValueFactory<Customer, LocalDateTime>("createdDate"));
        this.createdBy.setCellValueFactory(new PropertyValueFactory<Customer, String>("createdBy"));
        this.lastUpdate.setCellValueFactory(new PropertyValueFactory<Customer, LocalDateTime>("updatedDate"));
        this.lastUpdatedBy.setCellValueFactory(new PropertyValueFactory<Customer, String>("updatedBy"));
        this.state.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("divisionId"));

        for (Customer customer : customerList) {
            customerTableView.getItems().add(customer);
        }
    }

    @FXML
    private void reportsBtnAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("ReportsView.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage st = (Stage) this.customerTableView.getScene().getWindow();
            st.setScene(scene);
            st.setTitle("Resports Management");
            st.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void modifyAction(ActionEvent event) {
        if (customerTableView.getSelectionModel().getSelectedItem() != null) {
            try {
                UpdateCustomerView.currentCustomer = customerTableView.getSelectionModel().getSelectedItem();
                FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("UpdateCustomerView.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage st = (Stage) this.customerTableView.getScene().getWindow();
                st.setScene(scene);
                st.setTitle("Customer Mdification");
                st.show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @FXML
    private void addBtnAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("AddCustomerView.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage st = (Stage) this.customerTableView.getScene().getWindow();
            st.setScene(scene);
            st.setTitle("Employee's Registration Form");
            st.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void deleteBtnAction(ActionEvent event) {

        Customer customer = customerTableView.getSelectionModel().getSelectedItem();
        if (customer != null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Customer Deletion");
            alert.setContentText("Are you Sure you want to Delete " + customer.getCustomerName());
            Optional<ButtonType> op = alert.showAndWait();
            if (op.get() == ButtonType.OK) {
                boolean isDeleted = CustomerDBA.deleteCustomer(customer.getCustomerId());
                if (isDeleted) {
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setTitle("Customer Deletion");
                    a.setContentText("Customer " + customer.getCustomerName() + " is Deleted Successfully");
                    a.show();

                    customerTableView.getItems().clear();
                    for (Customer cs : CustomerDBA.getAllCustomers()) {
                        customerTableView.getItems().add(cs);
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
            Stage st = (Stage) this.customerTableView.getScene().getWindow();
            st.setScene(scene);
            st.setTitle("Appoinments Management System");
            st.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
