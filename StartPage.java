package com.example.finalvy;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class StartPage extends Test{


    @FXML
    private Button About_button;

    @FXML
    private Button attendance_button;

    @FXML
    private Button general_button;

    @FXML
    private Button quit_button;

    @FXML
    private Button settings_button;

    @FXML
    private Button student_button;

    @FXML
    void AboutPage(ActionEvent event) throws IOException {
        //On button Action the scene will be changed so we will load another one from fxml folder
        Parent loader = FXMLLoader.load(getClass().getResource("AboutLecture.fxml"));
        Scene scene = new Scene(loader);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void AttendancePage(ActionEvent event) throws IOException {
        //On button Action the scene will be changed so we will load another one from fxml folder
        Parent loader = FXMLLoader.load(getClass().getResource("Attendance.fxml"));
        Scene scene = new Scene(loader);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void GeneralPage(ActionEvent event) throws IOException{
        //On button Action the scene will be changed so we will load another one from fxml folder
        Parent loader = FXMLLoader.load(getClass().getResource("Homework.fxml"));
        Scene scene = new Scene(loader);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void Quit(ActionEvent event) {
        Platform.exit();
        //On button exit the stage will be closed

    }

    @FXML
    void SettingsPage(ActionEvent event) throws IOException{
        //On button Action the scene will be changed so we will load another one from fxml folder
        Parent loader = FXMLLoader.load(getClass().getResource("Settings.fxml"));
        Scene scene = new Scene(loader);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void StudentPage(ActionEvent event) throws IOException{
        //On button Action the scene will be changed so we will load another one from fxml folder
        Parent loader = FXMLLoader.load(getClass().getResource("Student.fxml"));
        Scene scene = new Scene(loader);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void initialize() {
        if(getList().isEmpty()){
            setList();
        }
        //When Start Page is  entert the likedlist will generate in Test class that contains all data
    }

}
