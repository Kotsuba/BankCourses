package com.kotsuba.bank.application;

/**
 * Created by Kotsuba on 03.11.2015.
 */
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.time.LocalDate;

public class MyApplication extends Application {

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResourceAsStream("/fxml/main.fxml"));
        stage.setTitle("BankCourses");
        stage.setScene(new Scene(root));
        stage.show();
    }
}