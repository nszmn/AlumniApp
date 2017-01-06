package com.example.enes.alumniapp;


import android.content.Intent;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.enes.alumniapp.model.login;

public class AdminRegistration extends AppCompatActivity {


     EditText username,password;
    AlumniDB controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_registration);

         username=(EditText)findViewById(R.id.edit_reg_username);
         password=(EditText)findViewById(R.id.edit_reg_password);

        controller=new AlumniDB(this,"",null,1);



    }


    public void btn_click(View view){

        switch (view.getId()){
            case R.id.btn_registr:
                try{
                   controller.insert_admin(username.getText().toString(),password.getText().toString());
                    Toast.makeText(AdminRegistration.this,"Saved.",Toast.LENGTH_SHORT).show();

                }catch (SQLException e){
                   Toast.makeText(AdminRegistration.this,"Couldnt Save",Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }
}
