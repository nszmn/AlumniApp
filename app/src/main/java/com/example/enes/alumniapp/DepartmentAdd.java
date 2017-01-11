package com.example.enes.alumniapp;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.enes.alumniapp.Database.AlumniDB;

import java.util.ArrayList;

public class DepartmentAdd extends AppCompatActivity {


    private String[] faculty={"ENGINEERING","EDUCATION","ECONOMY"};
    Spinner spDepartment,spFaculty;
    EditText name;
    Button save,ret;
    ArrayList<String> departmentList=new ArrayList<String>();
    ArrayAdapter<String> adapterDepatment;

    //ArrayList<String> facultyList=new ArrayList<String>();
    ArrayAdapter<String> adapterFaculty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department_add);


        spDepartment=(Spinner)findViewById(R.id.spinnerDepartmentList);
        spFaculty=(Spinner)findViewById(R.id.spinnerFacultyForDepartment);

        name=(EditText)findViewById(R.id.edit_addDepartmentList);

        save=(Button)findViewById(R.id.btn_departmentAdd);

        ret=(Button)findViewById(R.id.btn_departmentList);


        //Adapter
        adapterDepatment=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,departmentList);
        adapterDepatment.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adapterFaculty=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,faculty);///facultyList
        adapterFaculty.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spFaculty.setAdapter(adapterFaculty);

        final AlumniDB.DbDepartmentAdapter db;
        db = new AlumniDB.DbDepartmentAdapter(this);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //open
                db.openDB();

                String getfaculty = spFaculty.getSelectedItem().toString();
                long result=db.addDepartment(name.getText().toString(),getfaculty);
                if(result != 0){
                    name.setText("");


                    Toast.makeText(getApplicationContext(),"SAVED",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(),"FAILURE",Toast.LENGTH_SHORT).show();
                }

                //close
                db.closeDB();
            }
        });
        //List
        ret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // clear
                departmentList.clear();
                //open
                db.openDB();

                Cursor cursor=db.getAllDepartment();
                while(cursor.moveToNext()){
                    String name=cursor.getString(1);
                    departmentList.add(name);
                }
                //close
                db.closeDB();

                //Set it to spinner
                spDepartment.setAdapter(adapterDepatment);

            }
        });
    }
}
