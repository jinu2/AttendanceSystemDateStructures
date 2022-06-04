package com.example.finalvy;

import javafx.scene.control.CheckBox;

public class Student {

    private    int id;
    private  String name;
    private  String surname;
    private  int age;
    private  CheckBox check ;
    private  int absence=0;
    private  String email;
    private  String password;
    private String homework;
    private int grade;

    boolean control [] = new boolean[30];
    //The student information that class contains are id,name, surname,absence,email,password,homework,grade

    public void setAbsence() {
        this.absence +=1;
    }

    public Student(int id, String name, String surname, String email,String password) {
        //Initialize Student
        check = new CheckBox();
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.absence =0;
        for(int i = 0; i < 30 ; i++)
        {
            control[i] = false;
        }
    }


    public int getGrade() {return grade;}

    public void setGrade(int grade) {this.grade = grade;}

    public String getHomework() {return homework;}

    public void setHomework(String homework) {this.homework = homework;}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAbsence() {return absence;}

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    public int getAge() {
        return this.age;
    }

    public CheckBox getCheck() {
        return check;
    }

    public void setCheck(Boolean x) {
        //set the Check box with true/false
        if(x) {
            check.setSelected(true);
        } else {
            check.setSelected(false);
        }
    }

    public void disableCheck(Boolean x) {
        //Disable Check Box
        if(x) {
            check.setDisable(x);
        } else {
            check.setDisable(x);
        }
    }



}
