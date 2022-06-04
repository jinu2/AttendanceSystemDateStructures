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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.LinkedList;

public class HomeworkController extends Test{

    @FXML
    private TextField grade_filed;
    //text field to put the grade in

    @FXML
    private TextArea homework_filed;
    //text area that display the homework

    @FXML
    private TextField name_filed;
    //text field for name

    @FXML
    private TextField sn_filed;
    //text field for student number

    @FXML
    private TextField surname_filed;
    //text field for surname

    @FXML
    private Label warning_filed;
    //label that will open if warning comes

    @FXML
    private void initialize() {

        if(list.isEmpty()){
            //if the list from Test is not set, check it and set if it's empty
            setList();
        }

        name_filed.setEditable(false);
        surname_filed.setEditable(false);
        sn_filed.setEditable(false);
        //text fields for name, surname and num are just for displaying not for editing, so it's disabled

        homework_filed.setWrapText(true);
        //the text area will wrap text in the bounds, so it's not in one line and unnecessary scroll bar


        homework_filed.setText(queue.peek().getHomework());
        name_filed.setText(queue.peek().getName());
        surname_filed.setText(queue.peek().getSurname());
        sn_filed.setText(String.valueOf(queue.peek().getId()));
        //the data for text area, name, surname and number will be taken from QUEUE. Queue is
        //initialized in Test class by order from student
        //the first who enter the homework will be the first who is get graded


        //grade text field should be able to enter just integer characters, not alphabetic, so listener will block that
        grade_filed.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    grade_filed.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });


    }

    @FXML
    void Back(ActionEvent event) throws IOException {
        //Button back for back to Start Page
        Parent loader = FXMLLoader.load(getClass().getResource("StartPage.fxml"));
        Scene scene = new Scene(loader);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void Next(ActionEvent event) {

        if(queue.isEmpty() || queue.size()==1){
            //If there is no homeworks the labels and text fields will be empty and on
            //text area it will display message that there is no homeworks

            homework_filed.setText("THERE IS NO MORE HOMEWORKS!");
            warning_filed.setText("");
            name_filed.setText( "");
            surname_filed.setText( "");
            sn_filed.setText( "");

        }
        else {
            if (grade_filed.getText() == "" || Integer.valueOf(grade_filed.getText()) < 0 || Integer.valueOf(grade_filed.getText()) > 100) {
                warning_filed.setText("Warning! You didn't enter the right grade!");
                //if the grade is not entered user will not be able to continue action
            }
            else {
                //When the grade is given first we will have to update the Sudent grade
                queue.peek().setGrade(Integer.parseInt(grade_filed.getText()));
                //The student that has been graded will enter the stack structure
                stack.push(queue.peek());

                warning_filed.setText("");
                //If Warning message was on, turn it off

                queue.dequeue();
                homework_filed.setText(queue.peek().getHomework());
                name_filed.setText(queue.peek().getName());
                surname_filed.setText(queue.peek().getSurname());
                sn_filed.setText(String.valueOf(queue.peek().getId()));
                //remove element from queue and set the next one
            }
        }

    }


    public int linearSearch(LinkedList<Student> data, int item) {
        int ans = -1;
        // Traversing through the Linked List
        for (int i = 0; i < list.size(); i++) {
            // Extracting each element in the Linked List
            Student llElement = list.get(i);
            // Checking if the extracted element is equal to the element to be searched
            if (llElement.getId() == item) {
                // Assigning the index of the element to answer
                ans = i;
            }
        }
        return ans;
    }



}
