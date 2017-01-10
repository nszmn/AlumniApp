package com.example.enes.alumniapp;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.enes.alumniapp.Database.AlumniDB;

import java.util.ArrayList;

public class Department extends AppCompatActivity {

    TextView listF;
    Spinner spDepartment,spFaculty;
    EditText department;
    Button save,list,listele;
    ArrayList<String> departments=new ArrayList<String>();
    ArrayList<String> facultyList=new ArrayList<String>();
    ArrayAdapter<String> adapterF,adapterD;

    AlumniDB.DbAdapterDepartment controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department);


        listele=(Button)findViewById(R.id.btn_listele);


        spFaculty=(Spinner)findViewById(R.id.spinnerForFaculty);
        spDepartment=(Spinner)findViewById(R.id.spinnerDepartment);

        department=(EditText)findViewById(R.id.edit_departmentName);

        save=(Button)findViewById(R.id.btn_saveDepartment);
        list=(Button)findViewById(R.id.btn_listDepartment);
        //Adapter
        adapterD=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,departments);
        adapterF=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,facultyList);

        adapterD.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterF.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final AlumniDB.DbAdapter db;
        db = new AlumniDB.DbAdapter(this);

        final AlumniDB.DbAdapterDepartment dbD;
        dbD=new AlumniDB.DbAdapterDepartment(this);


        facultyList.clear();
        //open
        db.openDB();

        Cursor cursor=db.getAllFaculty();
        while(cursor.moveToNext()){
            String name=cursor.getString(1);
            facultyList.add(name);
        }
        //close
        db.closeDB();

        //Set it to spinner
        spFaculty.setAdapter(adapterF);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //open
                dbD.openDB();
                //String facultyID = spFaculty.getSelectedItem().toString();
                long result=dbD.add(department.getText().toString());//,facultyID.toString());
                if(result != 0){
                    department.setText("");
                    Toast.makeText(getApplicationContext(),"SAVED",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(),"FAILURE",Toast.LENGTH_SHORT).show();
                }

                //close
                dbD.closeDB();
            }
        });

        //List
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // clear
                departments.clear();
                //open
                dbD.openDB();

                Cursor cursor=dbD.getAllDepartment();
                while(cursor.moveToNext()){
                    String name=cursor.getString(1);
                    departments.add(name);
                }
                //close
                dbD.closeDB();

                //Set it to spinner
                spDepartment.setAdapter(adapterD);
            }
        });
    }
}