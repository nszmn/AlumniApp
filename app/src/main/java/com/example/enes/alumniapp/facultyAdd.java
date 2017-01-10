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

import com.example.enes.alumniapp.Database.AlumniDB.DbAdapter;

import java.util.ArrayList;

public class facultyAdd extends AppCompatActivity {

    Spinner sp;
    EditText name;
    Button save,ret;
    ArrayList<String> names=new ArrayList<String>();

    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_add);

        sp=(Spinner)findViewById(R.id.spinnerFaculty);

        name=(EditText)findViewById(R.id.edit_addFaculy);

        save=(Button)findViewById(R.id.btn_facultyAdd);

        ret=(Button)findViewById(R.id.btn_FacultyLis);

        //Adapter
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,names);// simple_spinner_item_1

        final DbAdapter db;
        db = new DbAdapter(this);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //open
                db.openDB();

                long result=db.add(name.getText().toString());
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
                names.clear();
                //open
                db.openDB();

                Cursor cursor=db.getAllFaculty();
                while(cursor.moveToNext()){
                    String name=cursor.getString(1);
                    names.add(name);
                }

                //close
                db.closeDB();

                //Set it to spinner
                sp.setAdapter(adapter);

            }
        });
    }
}
