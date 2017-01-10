package com.example.enes.alumniapp.Database;

import com.example.enes.alumniapp.model.students;

import java.util.List;

/**
 * Created by Enes on 17.12.2016.
 */

public final class Helper {
    private static int StudentID;
    private static students Student;
    private static List<students> StudentList;



    public static int getUserID (){return StudentID;}
    public static void setUserID(int StudentID){StudentID=StudentID;}

    public static students getStudent(){return Student;}
    public static void setStudent(students student){Student=student;}

    public static List<students> getStudentList(){return StudentList;}
    public static void setStudentList(List<students> studentList){StudentList=studentList;}

}
