package com.example.enes.alumniapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.enes.alumniapp.Database.Helper;
import com.example.enes.alumniapp.model.admin;

public class Login extends AppCompatActivity {


    public static String uName = "";
    public static String pass = "";
    private Button login;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText username = (EditText) findViewById(R.id.edit_username);
        final EditText password = (EditText) findViewById(R.id.edit_password);


        login = (Button) findViewById(R.id.btn_login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uName = username.getText().toString();
                pass = password.getText().toString();
                admin obj = new admin(getApplicationContext());
                admin result=obj.Login(uName,pass);
                if(result==null){
                    Toast.makeText(Login.this,"Username or Password is not Correct",Toast.LENGTH_LONG).show();
                    Intent intentMain=new Intent(Login.this,MainActivity.class);
                    startActivity(intentMain);

                }else{
                    int id=result.getId();
                    Helper.setUserID(id);
                    Toast.makeText(Login.this,"Successful",Toast.LENGTH_SHORT).show();
                    Intent intentMain=new Intent(Login.this,MainActivity.class);
                    startActivity(intentMain);

                }


            }
        });

    }




}
