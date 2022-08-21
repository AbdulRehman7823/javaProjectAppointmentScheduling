package com.scheduleproject.controller;

import com.scheduleproject.App;
import com.scheduleproject.DBA.AppointmentDBA;
import com.scheduleproject.DBA.ContactDBA;
import com.scheduleproject.DBA.CustomerDBA;
import com.scheduleproject.DBA.UserDBA;
import com.scheduleproject.model.Appointment;
import com.scheduleproject.model.Contact;
import com.scheduleproject.model.Customer;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author abdul
 */
public class UpdateAppointmentViewController implements Initializable {

    public static Appointment currentAppointment;
    @FXML
    private Label typeErr;
    @FXML
    private Label titleErr;
    @FXML
    private Label descErr;
    @FXML
    private Label locationErr;
    @FXML
    private Label contactErr;
    @FXML
    private Label userErr;
    @FXML
    private Label CustomerErr;
    @FXML
    private Label stDateErr;
    @FXML
    private Label stTimeErr;
    @FXML
    private Label endDateErr;
    @FXML
    private Label endTimeErr;
    @FXML
    private TextField idTf;
    @FXML
    private TextField titleTf;
    @FXML
    private TextField descriptionTf;
    @FXML
    private TextField locationTf;
    @FXML
    private TextField typeTf;
    @FXML
    private ComboBox<Contact> contactComboBox;
    @FXML
    private ComboBox<Customer> customerIdComboBox;
    @FXML
    private ComboBox<User> userIdComboBox;
    @FXML
    private DatePicker startDateTf;
    @FXML
    private Spinner<Integer> startTimeTf1;
    @FXML
    private Spinner<Integer> startTimeTf2;
    @FXML
    private DatePicker endDateTf;
    @FXML
    private Spinner<Integer> endTimeTf1;
    @FXML
    private Spinner<Integer> endTimeTf2;
    @FXML
    private Button saveBtn;
    @FXML
    private Button cancelBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        contactComboBox.getItems().addAll(ContactDBA.getAllContacts());
        customerIdComboBox.getItems().addAll(CustomerDBA.getAllCustomers());
        userIdComboBox.getItems().addAll(UserDBA.getAllUsers());
        if (currentAppointment != null) {
            this.idTf.setText(String.valueOf(currentAppointment.getAppointmentId()));
            this.idTf.setDisable(true);
            this.titleTf.setText(currentAppointment.getTitle());
            this.descriptionTf.setText(currentAppointment.getDescription());
            this.locationTf.setText(currentAppointment.getLocation());
            this.typeTf.setText(currentAppointment.getType());
            this.startDateTf.setValue(currentAppointment.getStartDateWithoutTime());
            this.endDateTf.setValue(currentAppointment.getEndDateWithoutTime());
            this.startTimeTf1.increment(currentAppointment.getStartTime().getHour());
            this.startTimeTf2.increment(currentAppointment.getStartTime().getMinute());
            this.endTimeTf1.increment(currentAppointment.getEndTime().getHour());
            this.endTimeTf2.increment(currentAppointment.getEndTime().getMinute());

            for (int i = 0; i < customerIdComboBox.getItems().size(); i++) {
                if (customerIdComboBox.getItems().get(i).getCustomerId() == currentAppointment.getCustomerId()) {
                    this.customerIdComboBox.getSelectionModel().select(i);
                }
            }
            for (int i = 0; i < contactComboBox.getItems().size(); i++) {
                if (contactComboBox.getItems().get(i).getContactId() == currentAppointment.getContactId()) {
                    this.contactComboBox.getSelectionModel().select(i);
                }
            }
            for (int i = 0; i < userIdComboBox.getItems().size(); i++) {
                if (userIdComboBox.getItems().get(i).getUserId() == currentAppointment.getUserId()) {
                    this.userIdComboBox.getSelectionModel().select(i);
                }
            }
        }
    }

    @FXML
    private void saveBtnAction(ActionEvent event) {

        String title = this.titleTf.getText();
        String location = locationTf.getText();
        String type = this.typeTf.getText();
        String description = this.descriptionTf.getText();
        Customer customer = this.customerIdComboBox.getSelectionModel().getSelectedItem();
        User user = this.userIdComboBox.getSelectionModel().getSelectedItem();
        Contact contact = this.contactComboBox.getSelectionModel().getSelectedItem();
        LocalDate startDate = this.startDateTf.getValue();
        LocalDate endDate = this.endDateTf.getValue();

        int startHours = this.startTimeTf1.getValue();
        int startMin = this.startTimeTf2.getValue();

        int endHour = this.endTimeTf1.getValue();
        int endMin = this.endTimeTf2.getValue();

        int errCounter = 0;
        if (title.equals("")) {
            this.titleTf.setStyle("-fx-border-color:red;-fx-border-width:2px;");
            this.titleErr.setText("Please Give a Correct Title.");

        } else {
            this.titleTf.setStyle("-fx-border-color:Green;-fx-border-width:2px;");
            this.titleErr.setText("");
            errCounter++;
        }

        if (location.equals("")) {
            this.locationTf.setStyle("-fx-border-color:red;-fx-border-width:2px;");
            this.locationErr.setText("Please Give a Correct Location.");

        } else {
            this.locationTf.setStyle("-fx-border-color:Green;-fx-border-width:2px;");
            this.locationErr.setText("");
            errCounter++;
        }

        if (description.equals("")) {
            this.descriptionTf.setStyle("-fx-border-color:red;-fx-border-width:2px;");
            this.descErr.setText("Please Give a Correct Description!.");

        } else {
            this.descriptionTf.setStyle("-fx-border-color:Green;-fx-border-width:2px;");
            this.descErr.setText("");
            errCounter++;
        }

        if (type.equals("")) {
            this.typeTf.setStyle("-fx-border-color:red;-fx-border-width:2px;");
            this.typeErr.setText("Please Give a Correct Type.");

        } else {
            this.typeTf.setStyle("-fx-border-color:Green;-fx-border-width:2px;");
            this.typeErr.setText("");
            errCounter++;
        }

        if (customer == null) {
            this.customerIdComboBox.setStyle("-fx-border-color:red;-fx-border-width:2px;");
            this.CustomerErr.setText("Please Select a Customer!");

        } else {
            this.customerIdComboBox.setStyle("-fx-border-color:Green;-fx-border-width:2px;");
            this.CustomerErr.setText("");
            errCounter++;
        }

        if (user == null) {
            this.userIdComboBox.setStyle("-fx-border-color:red;-fx-border-width:2px;");
            this.userErr.setText("Please Select a User");

        } else {
            this.userIdComboBox.setStyle("-fx-border-color:Green;-fx-border-width:2px;");
            this.userErr.setText("");
            errCounter++;
        }

        if (contact == null) {
            this.contactComboBox.setStyle("-fx-border-color:red;-fx-border-width:2px;");
            this.contactErr.setText("Please Select a Contact.");

        } else {
            this.contactComboBox.setStyle("-fx-border-color:Green;-fx-border-width:2px;");
            this.contactErr.setText("");
            errCounter++;
        }

        //starting with dates
        if (startDate == null) {
            this.startDateTf.setStyle("-fx-border-color:red;-fx-border-width:2px;");
            this.stDateErr.setText("Please Select a Start Date.");

        } else {
            this.startDateTf.setStyle("-fx-border-color:Green;-fx-border-width:2px;");
            this.stDateErr.setText("");
            errCounter++;
        }

        if (endDate == null) {
            this.endDateTf.setStyle("-fx-border-color:red;-fx-border-width:2px;");
            this.endDateErr.setText("Please Select a End Date.");

        } else {
            this.endDateTf.setStyle("-fx-border-color:Green;-fx-border-width:2px;");
            this.endDateErr.setText("");
            errCounter++;
        }

        if (startHours == endHour && startMin == endMin) {
            this.endTimeTf1.setStyle("-fx-border-color:red;-fx-border-width:2px;");
            this.endTimeTf2.setStyle("-fx-border-color:red;-fx-border-width:2px;");
            this.endTimeErr.setText("Start and End Time Must be Different!");

        } else {
            this.endTimeTf1.setStyle("-fx-border-color:green;-fx-border-width:2px;");
            this.endTimeTf2.setStyle("-fx-border-color:green;-fx-border-width:2px;");
            this.endTimeErr.setText("");
            errCounter++;
        }


        if (errCounter == 10) {


            Appointment appointment = new Appointment(
                    Integer.parseInt(idTf.getText()),
                    title, description, location, type, LocalDateTime.of(startDate, LocalTime.of(startHours, startMin))
                    , LocalDateTime.of(endDate, LocalTime.of(endHour, endMin)), LocalDateTime.now(),
                    UserDBA.currentUser.getUserName(), LocalDateTime.now(),
                    UserDBA.currentUser.getUserName(), customer.getCustomerId(),
                    user.getUserId(), contact.getContactId()
            );

            boolean isAdded = AppointmentDBA.updateAppointment(appointment);
            if (isAdded) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Appointment Modification");
                alert.setContentText("Appointment " + title + " is Updated Successfully");
                Optional<ButtonType> op = alert.showAndWait();
                if (op.get() == ButtonType.OK) {
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("MainView.fxml"));
                        Scene scene = new Scene(fxmlLoader.load());
                        Stage st = (Stage) this.endTimeTf1.getScene().getWindow();
                        st.setScene(scene);
                        st.setTitle("Appointment Management System");
                        st.show();
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Appointment Modification");
                alert.setContentText("There is Some DataBase Error While Updatation!!!!!");
                alert.show();
            }


        }
    }

    @FXML
    private void cancelBtnAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("MainView.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage st = (Stage) this.userIdComboBox.getScene().getWindow();
            st.setScene(scene);
            st.setTitle("Appointment Management System");
            st.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
