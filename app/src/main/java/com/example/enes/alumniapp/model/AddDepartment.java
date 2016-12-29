package com.example.enes.alumniapp.model;

import android.content.DialogInterface;
import android.database.SQLException;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.enes.alumniapp.AlumniDB;
import com.example.enes.alumniapp.R;

public class AddDepartment extends AppCompatActivity {

    EditText departmentName,deleteDepartment;
    TextView listDepartment;

    AlumniDB controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_department);

        departmentName=(EditText)findViewById(R.id.edit_add_department);
        //deleteDepartment=(EditText)findViewById(R.id.)

        listDepartment=(TextView)findViewById(R.id.text_department_list);

        controller=new AlumniDB(this,"",null,1);

    }
    public void btn_click(View view){
        switch (view.getId()){
            case R.id.btn_add_department:
                try{
                    controller.insert_department(departmentName.getText().toString());
                    controller.list_department(listDepartment);

                }catch (SQLException e){
                    Toast.makeText(AddDepartment.this,"ALREADY EXISTS",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_editDepartments:
                try{
                    AlertDialog.Builder dialog=new AlertDialog.Builder(AddDepartment.this);
                    dialog.setTitle("departmen name");
                    final EditText newDepartmentName=new EditText(this);
                    //final EditText old_editText=new EditText(this);
                    dialog.setView(newDepartmentName);
                   // dialog.setView(old_editText);
                    dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            controller.update_deparment(departmentName.getText().toString(),newDepartmentName.getText().toString());

                        }
                    });
                    Toast.makeText(this,"DOBAR..",Toast.LENGTH_SHORT).show();

                }catch (SQLException e){
                    Toast.makeText(this,"FAILED.",Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

}
