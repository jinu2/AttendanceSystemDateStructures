package com.example.finalvy;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.converter.LocalDateStringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.Random;
import java.util.Stack;

public class Test {

    //Number off all student
    public static int StudentNumber = 20;
    //Declare the LinkedList that will store all the Student
    public static LinkedList<Student> list = new LinkedList<>();
    //Stack list will store just graded student and display them in About Lecture Page
    public static Stack<Student> stack = new Stack<>();
    //Queue list will store Students with the homework, and it will be displayed in Homework Page
    public static Queue queue = new Queue(StudentNumber);
    //Used_Dates list is storing all the dates that the attendance is made on
    public static LinkedList<LocalDate> Used_Dates = new LinkedList<>();



    static Random random = new Random(1);
    //make random class with seed 1 so it's stable

    //Random text that will be use as example for homeworks
    static String [] texts = new String[]{
            "Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, ","there live the blind texts. Separated they live in Bookmarksgrove right at the coast of the Semantics, ","a large language ocean. A small river named Duden flows by their place and supplies it with the necessary regelialia. It is a paradisematic country,"," in which roasted parts of sentences fly into your mouth. Even the all-powerful Pointing has no control about the blind texts it is an almost unorthographic"," life One day however a small line of blind text by the name of Lorem Ipsum decided to leave for the far World of Grammar."," The Big Oxmox advised her not to do so, because there were thousands of bad Commas, wild Question Marks and devious Semikoli, but the Little Blind Text didn’t listen."," She packed her seven versalia, put her initial into the belt and made herself on the way."," When she reached the first hills of the Italic Mountains, she had a last view back on the skyline of her hometown"," Bookmarksgrove, the headline of Alphabet Village and the subline of her own road, the Line Lane. Pityful a rethoric question ran over her cheek, then"
    };


    public static LinkedList<Student> getList() {return list;}

    public static void setList() {

        //set the list with random 10 students
        for(int i =0; i<StudentNumber; i++){

            int StudentNumber;
            int max = 999999;
            int min = 100000;
            StudentNumber=random.nextInt(max + 1 - min) + min;
            //Generate random Student Number
            Student n = new Student(StudentNumber, "Name"+(i+1), "Surname"+(i+1), "Email"+(i+1),"Password"+(i+1));
            list.add(n);
            //append list with student

            if(i%3==0){
                n.setAbsence();
                n.setAbsence();
                n.control[0]=true;
                n.control[1]=true;
            }
            else if(i%5==0){
                n.setAbsence();
                n.control[0]=true;
            }

            //random generating absence for students
        }
        for(Student n: list){
            //generate random number and then update the student with homeworks
            //Student with homeworks are added to QUEUE
            int rand = random.nextInt(texts.length-1);
            n.setHomework(texts[rand]);
            queue.enqueue(n);
        }
        list=insertionsort(list);
        setUsed_Dates();

        //after setting list, list is sorting by student number with insertionsort method
    }


    public ObservableList<Student> getInitialTableData() {
        //check if list is empty, if not just convert list to ObservableList so it can be placed in table
        ObservableList<Student> data;
        if(getList().isEmpty()){
            setList();
            data = FXCollections.observableList(list);}
        else {data = FXCollections.observableList(list);}
        return data;
    }



    public ObservableList<Student> getInitialTableData1() {
        //Converting stack list to Observable List, so it can be displayed in the tabel
        ObservableList<Student> data;
        data = FXCollections.observableList(stack);
        return data;
    }

    public static void setUsed_Dates() {
        //set two dates in list
        DateTimeFormatter JEFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        // parsing the string to convert it into date
        LocalDate local_date = LocalDate.parse("30/06/2022", JEFormatter);
        Used_Dates.add(local_date);
         local_date = LocalDate.parse("14/06/2022", JEFormatter);
        Used_Dates.add(local_date);

    }

    public static LinkedList<Student> insertionsort(LinkedList<Student> oldlist) {

        LinkedList<Student> sortedlist = new LinkedList<>();
        //new sorted Linked list
        while (!oldlist.isEmpty()){
            Student smallest = list.get(0);
            //Find the Student with the lowest student number
            for (int i = 0; i < oldlist.size() - 2; i++) {
                if (smallest.getId() > list.get(i + 1).getId()) {
                    smallest = list.get(i + 1);
                }
            }
            sortedlist.add(smallest);
            oldlist.remove(smallest);
            //add object to the new list and delete it from the old one
        }
        return sortedlist;
        //return the sorted list
    }

}