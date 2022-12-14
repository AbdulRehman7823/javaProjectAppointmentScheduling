package com.scheduleproject;

import com.scheduleproject.DBA.SQLConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        SQLConnection.makeConnection();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("StartUpView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Employee Management System");
        stage.setScene(scene);
        stage.show();

    }
}
