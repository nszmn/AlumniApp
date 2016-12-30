package com.example.enes.alumniapp;

import android.content.DialogInterface;
import android.database.SQLException;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.widget.Spinner;

import java.util.List;

public class AddFaculty extends AppCompatActivity {

    EditText addFaculty;
    TextView list;
    AlumniDB controller;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_faculty);

        addFaculty=(EditText)findViewById(R.id.edit_faculty);

        list=(TextView)findViewById(R.id.text_list);

        controller=new AlumniDB(this,"",null,1);


    }
    public void btn_click(View view){
        switch (view.getId()){

            case R.id.btn_Add_Faculty:
                try{
                    controller.insert_faculty(addFaculty.getText().toString());
                   Toast.makeText(AddFaculty.this,"DONE",Toast.LENGTH_SHORT).show();

                }catch (SQLException e){
                    Toast.makeText(AddFaculty.this,"ALREADY EXISTS",Toast.LENGTH_SHORT).show();

                }
                break;
            case R.id.btn_list_faculty:
                controller.list_faculty(list);
                break;


            case R.id.imgbtn_delete_faculty:
                AlertDialog.Builder dialog=new AlertDialog.Builder(AddFaculty.this);
                dialog.setTitle("Delete Faculty");
                final EditText delete=new EditText(this);
                dialog.setView(delete);
                dialog.setPositiveButton("DELETE ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        controller.delete_faculty(delete.getText().toString());
                        Toast.makeText(AddFaculty.this,"DELETED.",Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.show();

                break;


        }

    }


}
