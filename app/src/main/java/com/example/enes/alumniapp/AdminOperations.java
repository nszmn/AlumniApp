package com.example.enes.alumniapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AdminOperations extends AppCompatActivity {

    EditText username;
    TextView list;
    AlumniDB controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_operations);

        username=(EditText)findViewById(R.id.edit_admin_delete);

        list=(TextView)findViewById(R.id.text_admin_list);

        controller=new AlumniDB(this,"",null,1);
    }
    public void btn_onclick(View view){
        switch (view.getId()){
            case R.id.btn_admin_list:
                controller.list_admin(list);
                break;
            case R.id.btn_admin_delete:
                controller.delete_admin(username.getText().toString());
                Toast.makeText(AdminOperations.this,"Deleted.",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
