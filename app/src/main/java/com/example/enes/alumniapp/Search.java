package com.example.enes.alumniapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import com.example.enes.alumniapp.Student.ListStudents;
import com.example.enes.alumniapp.model.Helper;
import com.example.enes.alumniapp.model.students;

import java.util.List;

public class Search extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        int studentID= Helper.getUserID();
        List<students> studentsList=Helper.getStudentList();

        final GridView gridView=(GridView)findViewById(R.id.listGrid);
        ListStudents listStudents=new ListStudents(Search.this,studentsList);
        gridView.setAdapter(listStudents);
    }
}
