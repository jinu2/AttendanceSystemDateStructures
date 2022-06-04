package com.example.finalvy;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.LinkedList;

public class AboutLectureController extends Test{

    @FXML
    private Label avarage_grade_label;
    //Label to display student's average grade in class

    @FXML
    private Label enrolled_label;
    //Label to display how many student are they in class

    @FXML
    private Label maximum_grade_label;
    //Label that display maximum grade in class

    @FXML
    private Label minimum_grade_lable;
    //Label that display minimum grade in class

    @FXML
    private TableView<Student> table;
    //Tabel that will contain Student's name, surname, grade, student number

    @FXML
    private TableColumn<Student, Integer> colona1;
    //column1 for student number

    @FXML
    private TableColumn<Student, String> colona2;
    //column2 for name

    @FXML
    private TableColumn<Student, String> colona3;
    //column3 for surname

    @FXML
    private TableColumn<Student, Integer> colona4;
    //column4 for grades

    @FXML
    void Back(ActionEvent event) throws IOException {
        //Button Back for going back to start page
        Parent loader = FXMLLoader.load(getClass().getResource("StartPage.fxml"));
        Scene scene = new Scene(loader);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void initialize() {

        colona1.setCellValueFactory(new PropertyValueFactory<Student, Integer>("id"));
        colona2.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
        colona3.setCellValueFactory(new PropertyValueFactory<Student, String>("surname"));
        colona4.setCellValueFactory(new PropertyValueFactory<Student, Integer>("grade"));
        //initialize columns that can take data from Student class

        table.setItems(getInitialTableData1());
        //Set data in table


        String s;
        s=enrolled_label.getText();
        enrolled_label.setText(s+" "+list.size());
        //Display the label for all student enrolled
        s=minimum_grade_lable.getText();
        minimum_grade_lable.setText(s+" "+getMinimum(list));
        //Display the label for minimum grade
        s=maximum_grade_label.getText();
        maximum_grade_label.setText(s+" "+getMaximum(list));
        //display the label for the maximum grade
        s=avarage_grade_label.getText();
        avarage_grade_label.setText(s+" "+getAverage(list));
        //display the label for the average grade

    }

    public int getMinimum(LinkedList<Student> data) {
        //method that finds the minimum grade
        int min=data.get(0).getGrade();
        for(int i=0; i<data.size(); i++){
            if(min>data.get(i).getGrade()){
                min=data.get(i).getGrade();
            }
        }
        return min;
    }

    public int getMaximum(LinkedList<Student> data) {
        //method that finds the maximum grade
        int max=data.get(0).getGrade();
        for(int i=0; i<data.size(); i++){
            if(max<data.get(i).getGrade()){
                max=data.get(i).getGrade();
            }
        }
        return max;
    }
    public int getAverage(LinkedList<Student> data) {
        //method that finds sum of all grades and then calculate the average
        int sum=0;
        for(int i=0; i<data.size(); i++){
            sum+=data.get(i).getGrade();
        }

        return sum/data.size();
    }

}
