package com.example.enes.alumniapp;

import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.enes.alumniapp.Database.AlumniDB;

public class AddAdmin extends AppCompatActivity {
    Button operation;
    EditText username,password;
    AlumniDB controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_admin);

        operation=(Button)findViewById(R.id.btn_admin_operation);

        username=(EditText)findViewById(R.id.edit_addAdmin_username);
        password=(EditText)findViewById(R.id.edit_addAdmin_password);

        //controller=new AlumniDB(this,"",null,1);


    }
    public void btn_click(View view){

        switch (view.getId()){
            case R.id.btn_admin_save:
                try{
                    controller.insert_admin(username.getText().toString(),password.getText().toString());
                    Toast.makeText(AddAdmin.this,"Saved.",Toast.LENGTH_SHORT).show();

                }catch (SQLException e){
                    Toast.makeText(AddAdmin.this,"Couldnt Save",Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }
}
