package com.example.enes.alumniapp;


import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.enes.alumniapp.model.admin;

public class AdminRegistration extends AppCompatActivity {


    EditText username,password;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_registration);

         username=(EditText)findViewById(R.id.edit_reg_username);
         password=(EditText)findViewById(R.id.edit_reg_password);

         register=(Button)findViewById(R.id.btn_registr);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    String uName= username.getText().toString();
                    String pass= password.getText().toString();

                    admin obj= new admin(getApplicationContext());

                    obj.setPassword(pass);
                    obj.setUsername(uName);
                    obj.Insert();

                    Toast.makeText(AdminRegistration.this,"OK",Toast.LENGTH_SHORT).show();
                    Intent reg = new Intent(AdminRegistration.this, MainActivity.class);
                    startActivity(reg);
                }catch (SQLException e){
                    e.printStackTrace();
                    Toast.makeText(AdminRegistration.this,"hata",Toast.LENGTH_SHORT).show();

                }


            }
        });
    }
}