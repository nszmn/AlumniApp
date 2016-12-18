package com.example.enes.alumniapp;

/**
 * Created by Enes on 4.12.2016.
 */

public class Student {
    private String Name;
    private String Surname;
    private String Birthday;
    private String PlaceOfBirth;
    private int Department;
    private int Faculty;
    private String Id;

    public String getName(){
        return Name;
    }
    public void setName(String name){
        Name=name;
    }

    public String getSurname(){
        return Surname;
    }
    public void setSurname(String surname){
        Surname=surname;
    }

    public String getBirthday(){
        return Birthday;
    }
    public void setBirthday(String birthday){
        Birthday=birthday;
    }

    public String getPlaceOfBirth(){
        return PlaceOfBirth;
    }
    public void setPlaceOfBirth(String placeOfBirth){
        PlaceOfBirth=placeOfBirth;
    }

    public int getDepartment(){
        return Department;
    }
    public void setDepartment(int department){
        Department=department;
    }

    public int getFaculty(){
        return Faculty;
    }
    public void setFaculty(int faculty){
        Faculty=faculty;
    }
    public String getId(){
        return Id;
    }
    public void setId(String id){
        Id=id;
    }
}
