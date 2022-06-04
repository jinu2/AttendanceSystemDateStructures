package com.example.finalvy;


import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.LinkedList;

public class StudentController extends Test{


    @FXML
    public Button back_button;
    //Button Back for going back to Start Page

    @FXML
    public  TableView<Student> tabel;
    //Table that will contain Student's name, surname, student number and total absence
    //This table will also highlight the row of Student if student number is found

    @FXML
    public TableColumn<Student, Integer> col1;
    //in column1 the student number will be displayed

    @FXML
    public TableColumn<Student, String> col2;
    //in column2 the name of the student will be displayed

    @FXML
    public TableColumn<Student, String> col3;
    //in column3 the surname of the student will be displayed

    @FXML
    public TableColumn<Student, Integer> col4;
    //in column4 the number of total absence will be displayed

    @FXML
    public TextField text_field_search;
    //text field for entering student number that user want to found

    @FXML
    public Button Search_button;
    //Button for searching student number


    @FXML
    void initialize() {

        col1.setCellValueFactory(new PropertyValueFactory<Student, Integer>("id"));
        col2.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
        col3.setCellValueFactory(new PropertyValueFactory<Student, String>("surname"));
        col4.setCellValueFactory(new PropertyValueFactory<Student, Integer>("absence"));
        //set all columns and their types that will take data from linked list

        tabel.setItems(getInitialTableData());
        //set data to tabel

        text_field_search.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    text_field_search.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        //the text field has to be initialized to take just numbers. NO alphabet characters aloud
    }




    @FXML
    void Back(ActionEvent event) throws IOException {

        //Button Back for back to Start Page
        Parent loader = FXMLLoader.load(getClass().getResource("StartPage.fxml"));
        Scene scene = new Scene(loader);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void Search(ActionEvent event) {

        int SearchNumber = Integer.parseInt(text_field_search.getText());
        //take the number from the text field
        int index = interpolationSearch(list,SearchNumber);
        //searching for entered student number in list with interpolation search method
        //if student number is found then the table will highlight the row of that student

        int index1= linearSearch(list,SearchNumber);


        Platform.runLater(new Runnable()
        {
            @Override
            public void run()
            {
                tabel.requestFocus();
                tabel.getSelectionModel().select(index1);
                tabel.getFocusModel().focus(index1);
            }
        });


    }

    public int interpolationSearch(LinkedList<Student> data, int item) {

        int highEnd = (data.size() - 1);//taking upper bound of searching area
        int lowEnd = 0;//taking lower bound of searching area

        while (item >= data.get(lowEnd).getId() && item <= data.get(highEnd).getId() && lowEnd <= highEnd) {
            int probe
                    = lowEnd + (highEnd - lowEnd) * (item - data.get(lowEnd).getId()) / (data.get(highEnd).getId() - data.get(lowEnd).getId());
            if (highEnd == lowEnd) {
                if (data.get(lowEnd).getId() == item) {
                    return lowEnd;
                } else {
                    return -1;
                    //Not Found
                }
            }
            if (data.get(probe).getId() == item) {
                return probe;
                //it will return the index in the list where the item is found
            }

            if (data.get(probe).getId() < item) {
                lowEnd = probe + 1;
            } else {
                highEnd = probe - 1;
            }
        }
        return -1;
        //Not Found
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


