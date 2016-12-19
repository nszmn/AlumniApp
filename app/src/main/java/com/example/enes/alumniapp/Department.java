package com.example.enes.alumniapp;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Objects;
import java.sql.SQLException;
import java.


public class Department {
    private String Name;

    public  String getName(){
        return  Name;
    }
    public void setName(String name){
        Name=name;
    }

    private int FacultyId;
    public int getFacultyId(){
        return FacultyId;
    }

    public void setFacultyId(int facultyId){
        FacultyId=facultyId;
    }
    private int Id;
    public int getId(){
        return Id;
    }
    public void setId(int id){
        Id=id;
    }
    public Objects displayMember;
    public Objects valueMember;
    static Connection conn=new AlumniDB().connect();
    private Connection AlumniDB;
    static PreparedStatement pstmt=null;
    static ResultSet rs=null;
    public Department(){}
    public Department(Object _displayMember, Object _valueMember){
        displayMember=_displayMember;
        valueMember=_valueMember;
    }
    public String toString(){

        return displayMember.toString();
    }
    public ArrayList<Department>getData(int fId)
    {
        ArrayList<Department>list=new ArrayList<Department>();
        try{
            String sql="SELECT * FROM Departments WHERE facultyId = ?";
            pstmt= conn.prepareStatement(sql);
            System.out.println(fId);
            pstmt.setInt(1,fId);
            rs=pstmt.executeQuery();
            Department dp;
            while(rs.next()){
                dp=new Department();
                dp.setId(rs.getInt("Id"));
                dp.setName(rs.getString("Name"));
                dp.setFacultyId(rs.getInt("facultyId"));

                list.add(dp);
            }
        } catch (Exception e){

        }
        return list;
    }


}

