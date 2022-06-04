package com.example.finalvy;



import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.LinkedList;

public class SettingsController extends Test{
    @FXML
    private Button Add_button;

    @FXML
    private ToggleButton ToggleAdd;
    //Toggle button to display pane1

    @FXML
    private ToggleButton ToggleChange;
    //Toggle button to display pane2

    @FXML
    private ToggleButton ToggleDelete;
    //Toggle button to display pane3

    @FXML
    private TextField abscene_filed_pane3;
    //Text filed in pane3 for absence

    @FXML
    private TextField delete_number_field;
    //Text filed that student number will be entered on pane3

    @FXML
    private TextField email_filed_pane1;
    //Text filed to display email on pane1

    @FXML
    private TextField email_filed_pane3;
    //Text filed to display email on pane3

    @FXML
    private ToggleGroup group;
    //Toggle Button group

    @FXML
    private TextField name_field_pane1;
    //Text field to enter new name on pane1 for new student

    @FXML
    private TextField name_filed_pane3;
    //text filed to display name on pane3

    @FXML
    private StackPane pane1_AddStudent;
    //pane1 for adding new student

    @FXML
    private StackPane pane2_Change;
    //pane2 for displaying text area about lecture

    @FXML
    private StackPane pane3_delete;
    //pane3 for deleting student find by student number

    @FXML
    private TextField password_field_pane1;
    //text filed to enter new password for student

    @FXML
    private TextField sn_filed_pane1;
    //text filed to enter new student number

    @FXML
    private TextField surname_field_pane1;
    //text filed to enter surname in pane1

    @FXML
    private TextField surname_filed_pane3;
    //text filed for enter surname in pane3


    @FXML
    private Label warnning_pane1;
    //label for warning

    @FXML
    private TextArea textarea_pane2;
    //text area for the pane2

    @FXML
    void AddNewStudent(ActionEvent event) {

        String name = name_field_pane1.getText();
        String surname = surname_field_pane1.getText();
        Integer sn;
        if(sn_filed_pane1.getText() ==""){sn=0;}
        else {sn = Integer.valueOf(sn_filed_pane1.getText());}
        String email = email_filed_pane1.getText();
        String pas = password_field_pane1.getText();
        //Taking all the data from the filed and if its not null
        //adding new student to a list

        if(name=="" || surname == "" || sn==0 || email =="" || pas ==""){
            warnning_pane1.setText("Warning! Some fileds are empty!");
        }
        else {
            warnning_pane1.setText("");
            list.add(new Student(sn, name, surname, email, pas));
        }
        //After adding new student, set all field empty for new student
        name_field_pane1.setText("");
        surname_field_pane1.setText("");
        sn_filed_pane1.setText("");
        email_filed_pane1.setText("");
        password_field_pane1.setText("");
    }

    @FXML
    void Delete(ActionEvent event) {

        Integer sn;
        if(delete_number_field.getText()==""){sn=0;}
        else {sn= Integer.valueOf(delete_number_field.getText());}
        //getting the number that user enterd

        int index = interpolationSearch(list,sn);
        if(index!=-1) {
            list.remove(index);
        }

        //setting all the filed to empty for next enter
        name_filed_pane3.setText("");
        surname_filed_pane3.setText("");
        abscene_filed_pane3.setText("");
        email_filed_pane3.setText("");

    }

    @FXML
    void Display(ActionEvent event) {


        Integer sn;
        if(delete_number_field.getText()==""){}
        else {
            sn= Integer.valueOf(delete_number_field.getText());
            //taking the number that user enter and then display the information

            if(getList().isEmpty()){
                setList();
            }
            else {
                int index = interpolationSearch(list,sn);
                //finding index of list that student number is
                if(index==-1){
                    //if number is not found set fields empty
                    name_filed_pane3.setText("");
                    surname_filed_pane3.setText("");
                    abscene_filed_pane3.setText("");
                    email_filed_pane3.setText("");
                }
                else {
                    //if index is found then display data from that index in fileds
                    name_filed_pane3.setText(list.get(index).getName());
                    surname_filed_pane3.setText(list.get(index).getSurname());
                    abscene_filed_pane3.setText(String.valueOf(list.get(index).getAbsence()));
                    email_filed_pane3.setText(list.get(index).getEmail());
                }
            }
        }
    }


    @FXML
    private void initialize() {

        ToggleAdd.setToggleGroup(group);
        ToggleChange.setToggleGroup(group);
        ToggleDelete.setToggleGroup(group);
        //set the toggle group to buttons

        textarea_pane2.setWrapText(true);
        textarea_pane2.setText("Java is one of the most popular programming languages out there. Released in 1995 and still widely used today, Java has many applications, including software development, mobile applications, and large systems development. Knowing Java opens a lot of possibilities for you as a developer.\n\n***Coded by Nudzejma Zukic***\n\nStudent enroled: "+list.size()+"\nAverage grade: ");
        //set the text area for pane2 and Wrap text so it's not all in same line

        if(group.getSelectedToggle() ==null){
            //on initialization when no button is selected, select the first one
            pane1_AddStudent.setVisible(true);
            pane2_Change.setVisible(false);
            pane3_delete.setVisible(false);
        }

        ToggleAdd.selectedProperty().addListener(((observable, oldValue, newValue) -> {
            //if ToggleAdd button is selected then just pane 1 will be visible
            pane1_AddStudent.setVisible(true);
            pane2_Change.setVisible(false);
            pane3_delete.setVisible(false);

        }));

        ToggleChange.selectedProperty().addListener(((observable, oldValue, newValue) -> {
            //if ToggleAdd button is selected then just pane 2 will be visible
            pane1_AddStudent.setVisible(false);
            pane2_Change.setVisible(true);
            pane3_delete.setVisible(false);
        }));
        ToggleDelete.selectedProperty().addListener(((observable, oldValue, newValue) -> {
            //if ToggleAdd button is selected then just pane 3 will be visible
            pane1_AddStudent.setVisible(false);
            pane2_Change.setVisible(false);
            pane3_delete.setVisible(true);
        }));


        //on pane3 text filed for student number can just accept numbers, not alphabet
        delete_number_field.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    delete_number_field.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        //on pane1 text filed for student number can just accept numbers, not alphabet
        sn_filed_pane1.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    sn_filed_pane1.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    @FXML
    void Back(ActionEvent event) throws IOException {
        //Button for back to Start Page
        Parent loader = FXMLLoader.load(getClass().getResource("StartPage.fxml"));
        Scene scene = new Scene(loader);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    public int interpolationSearch(LinkedList<Student> data, int item) {
        //interpolation search method that returns index of item in data list
        int highEnd = (data.size() - 1);
        int lowEnd = 0;

        while (item >= data.get(lowEnd).getId() && item <= data.get(highEnd).getId() && lowEnd <= highEnd) {
            int probe = lowEnd + (highEnd - lowEnd) * (item - data.get(lowEnd).getId()) / (data.get(highEnd).getId() - data.get(lowEnd).getId());
            if (highEnd == lowEnd) {
                if (data.get(lowEnd).getId() == item) {return lowEnd;}
                else {return -1;}}
            if (data.get(probe).getId() == item) {return probe;}
            if (data.get(probe).getId() < item) {lowEnd = probe + 1;}
            else {highEnd = probe - 1;}
        }
        return -1;
    }

}
