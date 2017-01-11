package com.example.enes.alumniapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.enes.alumniapp.Database.AlumniDB;
import com.example.enes.alumniapp.Database.CustomAdapter;
import com.example.enes.alumniapp.Database.DatabaseAdapter;
import com.example.enes.alumniapp.Database.DatabaseHelper;
import com.example.enes.alumniapp.Database.Helper;
import com.example.enes.alumniapp.Database.Planet;
import com.example.enes.alumniapp.Student.StudentOperations;
import com.example.enes.alumniapp.Student.students;

import java.util.ArrayList;
import java.util.List;

public class AddStudents extends AppCompatActivity {

    private String[] faculty={"ENGINEERING","EDUCATION","ECONOMY"};
    private String[] department={"IT","IEEE","GENETIC","ACRHITECTURE","Banking","Management","English Lang.","Turkish Language "};
    Spinner spFaculty,spDepartment;
    Button studentOperations,save;
    EditText number,name,surname,birthday,place,mail,time,phone,address;
    ListView lv;
    CustomAdapter adapter;
    ArrayList<Planet> planets=new ArrayList<>();

    //ArrayList<String> departmentList=new ArrayList<String>();
    ArrayAdapter<String> adapterDepatment;

    //ArrayList<String> facultyList=new ArrayList<String>();
    ArrayAdapter<String> adapterFaculty;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addstudents);

        studentOperations=(Button)findViewById(R.id.btnOperations);

        spDepartment=(Spinner)findViewById(R.id.spinner_ADDSTUDENT_department);
        spFaculty=(Spinner)findViewById(R.id.spinner_ADDSTUDENT_faculty);

        adapterDepatment=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,department);
        adapterDepatment.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adapterFaculty=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,faculty);///facultyList
        adapterFaculty.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spFaculty.setAdapter(adapterFaculty);
        spDepartment.setAdapter(adapterDepatment);

        final AlumniDB.DbDepartmentAdapter db;
        db = new AlumniDB.DbDepartmentAdapter(this);

        number=(EditText)findViewById(R.id.edit_StudentNumber);
        name=(EditText)findViewById(R.id.edit_Name);
        surname=(EditText)findViewById(R.id.edit_Surname);
        birthday=(EditText)findViewById(R.id.edit_Birthday);
        place=(EditText)findViewById(R.id.edit_place);
        mail=(EditText)findViewById(R.id.edit_Email);
        phone=(EditText)findViewById(R.id.edit_phone);
        //time=(EditText)findViewById(R.id.edit_addTime);
        address=(EditText)findViewById(R.id.edit_address);
        final Button Add=(Button)findViewById(R.id.btnSave);

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    //save(name.getText().toString(),surname.getText().toString());

                    String Name=name.getText().toString();
                    Integer SId= number.getInputType();
                    String last=surname.getText().toString();
                    Integer birth=birthday.getInputType();
                    String places=place.getText().toString();
                    String maill=mail.getText().toString();
                    Integer phoneN=phone.getInputType();
                    String adres=address.getText().toString();
                    String fac=spFaculty.getSelectedItem().toString();
                    String dep=spDepartment.getSelectedItem().toString();

                    students stu=new students(getApplicationContext());

                    stu.setStudentID(SId);
                    stu.setFirstname(Name);
                    stu.setLastname(last);
                    stu.setBirthday(birth);
                    stu.setPlace(places);
                    stu.setMail(maill);
                    stu.setPhone(phoneN);
                    stu.setAddres(adres);
                    stu.setSfaculty(fac);
                    //stu.setSdepartment(dep);
                    stu.Insert();

                    Toast.makeText(AddStudents.this,"OK..",Toast.LENGTH_LONG).show();
                }catch (Exception e){

                    Toast.makeText(AddStudents.this,"hata",Toast.LENGTH_SHORT).show();
                }
            }
        });




        //studentOperations=(Button)findViewById(R.id.btn_studentOperations);

        studentOperations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                students tt = new students(getApplicationContext());
                List<students> data=tt.ListAll();
                if (data==null || data.size()==0)
                {

                    Toast.makeText(getApplicationContext(),"Added",Toast.LENGTH_LONG).show();
                }else
                {
                    Helper.setStudentList(data);
                    Toast.makeText(getApplicationContext(),"Empty",Toast.LENGTH_LONG).show();
                }

                Intent inn=new Intent(AddStudents.this,StudentOperations.class);
                startActivity(inn);
            }
        });

    }


    private void save(String namef,String lastname)
    {
        DatabaseAdapter db=new DatabaseAdapter(this);
        db.openDB();
        if(db.add(namef,lastname))
        {
            name.setText("");
            surname.setText("");
        }else {
            Toast.makeText(this,"Unable To Save",Toast.LENGTH_SHORT).show();
        }

        db.closeDB();

        this.getPlanets(null);
    }

    private void getPlanets(String searchTerm)
    {
        planets.clear();

        DatabaseAdapter db=new DatabaseAdapter(this);
        db.openDB();
        Planet p=null;
        Cursor c=db.retrieve(searchTerm);
        while (c.moveToNext())
        {
            int id=c.getInt(0);
            String name=c.getString(1);

            p=new Planet();
            p.setId(id);
            p.setName(name);

            planets.add(p);
        }

        db.closeDB();

        lv.setAdapter(adapter);

    }



}
