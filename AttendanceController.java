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
import javafx.stage.Stage;
import javafx.util.Callback;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.temporal.ChronoField;


public class AttendanceController extends Test{


    @FXML
    public DatePicker data_picker;
    //Data Picker to pick date that will be taken presents from

    @FXML
    public TableView<Student> tabel;
    //tabel that will contain name,surname,id and check boxes for taking attendance

    @FXML
    public TableColumn<Student, Boolean> col1;
    //column1 for Check Box

    @FXML
    public TableColumn<Student, Integer> col2;
    //column2 for student number

    @FXML
    public TableColumn<Student, String> col3;
    //column3 for student name

    @FXML
    public TableColumn<Student, String> col4;
    // column4 for taking student surname


    public static int count=0;
    //this will take count of how many days' attendance made


    @FXML
    void initialize() {
        count = Used_Dates.size();
        col1.setCellValueFactory(new PropertyValueFactory<Student, Boolean>("check"));
        col2.setCellValueFactory(new PropertyValueFactory<Student, Integer>("id"));
        col3.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
        col4.setCellValueFactory(new PropertyValueFactory<Student, String>("surname"));
        //initialize the tabel that will contain Check Boxes, name, surname, student number

        tabel.setItems(getInitialTableData());
        //set items to the tabel

        //track data picker which date is choosen
        data_picker.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
            }
        });

        //controling the date picker Cell Factory
        //set dates that are not school days in color pink
        //and set all the date that passed to color green
        data_picker.setDayCellFactory(new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(DatePicker param) {
                return new DateCell(){
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        if (isWeekend(item)) {
                            setDisable(true);
                            setStyle("-fx-background-color: #EDC9AF");
                            //if the date is not school day it is disabled and pink
                        }
                        if (!empty && item != null) {
                            if(Used_Dates.contains(item)) {
                                getStyleClass().add("-fx-background-color:  #3EB489");
                                setStyle("-fx-background-color:  #3EB489");
                                //if date is already passed and attendance has been made on that day it is colored green
                            }
                        }
                    }
                };
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
    void DataPick(ActionEvent event) {

        for (Student n : getInitialTableData()) {
            n.setCheck(false);
            n.disableCheck(false);
        }

    }

    @FXML
    void Select(ActionEvent event) {

        LocalDate isoDate = data_picker.getValue();
        //get the date value from date picker

        if(isoDate!=null) {
            //First check if the date is already been used and plased to Used_Dates list

            if (Used_Dates.contains(isoDate)) {
                //if dates is already passed than displayed previous attendance
                //but this time Check Boxes are disabled and can't be changed
                int a = Used_Dates.indexOf(isoDate);
                for (Student n : getInitialTableData()) {
                    Boolean b = n.control[a];
                    n.setCheck(b);
                    n.disableCheck(true);
                }
            }
            else {
                Used_Dates.add(isoDate);
                //Add the date to Used Dates list
                int index = 0;
                for (Student n : getInitialTableData()) {
                    //then we have to append all Student with boolean according to their
                    //presents on the date, the list is stored in control array
                    boolean select;
                    select = tabel.getItems().get(index).getCheck().isSelected();
                    n.control[count] =select;
                    if(!select){
                        n.setAbsence();
                    }
                    index++;
                    //the data for check boxes is taking directly from tabel for every student
                }

                count++;
                //increase the count for dates that been used

                for (Student n : getInitialTableData()) {
                    n.setCheck(false);
                    n.disableCheck(false);
                }
                //set all check boxes empty(false) for new date
            }

            tabel.setItems(getInitialTableData());
            //update the tabel
        }

        //track and update date picker everytime on button select,
        // so all dates that are used are green and weekend days are pink
        data_picker.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {}});

        data_picker.setDayCellFactory(new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(DatePicker param) {
                return new DateCell(){
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        if (isWeekend(item)) {
                            setDisable(true);
                            setStyle("-fx-background-color: #EDC9AF");
                        }
                        if (!empty && item != null) {
                            if(Used_Dates.contains(item)) {
                                getStyleClass().add("-fx-background-color:  #3EB489");
                                setStyle("-fx-background-color:  #3EB489");
                                //update date cell to green when new date is added
                            }
                        }
                    }
                };
            }
        });


    }

    public static boolean isWeekend(final LocalDate ld)
    {
        //Weekend days that there are no classes on
        //For this class Days off are Sunday,Monday,Saturday,Friday,and Wednesday
        DayOfWeek day = DayOfWeek.of(ld.get(ChronoField.DAY_OF_WEEK));
        return day == DayOfWeek.SUNDAY || day == DayOfWeek.SATURDAY || day == DayOfWeek.MONDAY || day == DayOfWeek.FRIDAY || day == DayOfWeek.WEDNESDAY;
    }

}
