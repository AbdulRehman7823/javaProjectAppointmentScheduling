package com.scheduleproject.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.scheduleproject.App;
import com.scheduleproject.Application.ResourceBundleBindingExample;
import com.scheduleproject.DBA.UserDBA;
import com.scheduleproject.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TimeZone;

/**
 * FXML Controller class
 *
 * @author abdul
 */
public class StartUpController implements Initializable {

    @FXML
    public Label errorLabel;
    @FXML
    private TextField userNameTf;
    @FXML
    private PasswordField passwordTf;
    @FXML
    private Button loginBtn;
    @FXML
    private Button resetBtn;
    @FXML
    private ComboBox<Locale> languageComboBox;
    @FXML
    private Label timeZoneL;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Calendar now = Calendar.getInstance();
        TimeZone timeZone = now.getTimeZone();
        timeZoneL.setText(timeZone.getDisplayName());


        languageComboBox.getItems().addAll(Locale.ENGLISH, Locale.FRENCH);
        languageComboBox.setValue(Locale.ENGLISH);
        languageComboBox.setCellFactory(lv -> new ResourceBundleBindingExample.LocaleCell());
        languageComboBox.setButtonCell(new ResourceBundleBindingExample.LocaleCell());
        
    }

    @FXML
    private void loginBtnAction(ActionEvent event) {

        String userName = this.userNameTf.getText();
        String password = this.passwordTf.getText();

        int counter = 0;

        if (userName.equals("")) {
            this.userNameTf.setStyle("-fx-border-color:red;-fx-border-width:2px;");
        } else {
            this.userNameTf.setStyle("-fx-border-color:green;-fx-border-width:2px;");
            counter++;
        }
        if (password.equals("")) {
            this.passwordTf.setStyle("-fx-border-color:red;-fx-border-width:2px;");
        } else {
            this.passwordTf.setStyle("-fx-border-color:green;-fx-border-width:2px;");
            counter++;
        }

        if (counter == 2) {

            User user = UserDBA.checkUser(userName, password);
            if (user != null) {
                UserDBA.currentUser = user;
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("MainView.fxml"));
                    Scene scene = new Scene(fxmlLoader.load());
                    Stage st = (Stage) this.languageComboBox.getScene().getWindow();
                    st.setScene(scene);
                    st.setTitle("Appointment Management System");
                    st.show();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            } else {
                this.errorLabel.setText("User Name or Password is Wrong!!!");
            }
        } else {
            this.errorLabel.setText("Non of the Field can be Empty!!");
        }


    }

    @FXML
    private void resetBtnAction(ActionEvent event) {
        this.userNameTf.setText("");
        this.passwordTf.setText("");
        this.errorLabel.setText("");
    }

    @FXML
    private void languageComboBoxAction(ActionEvent event) {
    }

}
