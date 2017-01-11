package com.example.enes.alumniapp.Operations;

import android.content.Intent;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.enes.alumniapp.AdminRegistration;
import com.example.enes.alumniapp.Database.AlumniDB;
import com.example.enes.alumniapp.MainActivity;
import com.example.enes.alumniapp.R;
import com.example.enes.alumniapp.model.admin;

public class AdminOperations extends AppCompatActivity {

    EditText username;
    TextView list;
    admin controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_operations);

        username=(EditText)findViewById(R.id.edit_admin_delete);

        list=(TextView)findViewById(R.id.text_admin_list);

        final Button deleteB=(Button)findViewById(R.id.btn_admin_delete);
        final Button listB=(Button)findViewById(R.id.btn_admin_list);

        deleteB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    String uName= username.getText().toString();

                    admin obj= new admin(getApplicationContext());


                    //obj.setUsername(uName);
                    obj.Delete(username.getText().toString());

                    Toast.makeText(AdminOperations.this,"OK",Toast.LENGTH_SHORT).show();
                    //Intent reg = new Intent(AdminRegistration.this, MainActivity.class);
                    //startActivity(reg);
                }catch (SQLException e){
                    e.printStackTrace();
                    Toast.makeText(AdminOperations.this,"hata",Toast.LENGTH_SHORT).show();

                }
            }
        });
        listB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    String uName= username.getText().toString();

                    admin obj= new admin(getApplicationContext());


                    //obj.setUsername(uName);
                    obj.list_allAdmin(list);

                    Toast.makeText(AdminOperations.this,"OK",Toast.LENGTH_SHORT).show();
                    //Intent reg = new Intent(AdminRegistration.this, MainActivity.class);
                    //startActivity(reg);
                }catch (SQLException e){
                    e.printStackTrace();
                    Toast.makeText(AdminOperations.this,"hata",Toast.LENGTH_SHORT).show();

                }
            }
        });



        //controller=new AlumniDB(this,"",null,1);
    }

}
