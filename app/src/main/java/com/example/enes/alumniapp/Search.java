package com.example.enes.alumniapp;

import android.app.Dialog;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.enes.alumniapp.Database.CustomAdapter;
import com.example.enes.alumniapp.Database.DatabaseAdapter;
import com.example.enes.alumniapp.Database.Planet;
import com.example.enes.alumniapp.Student.ListStudents;
import com.example.enes.alumniapp.Database.Helper;
import com.example.enes.alumniapp.model.students;

import java.util.ArrayList;
import java.util.List;

public class Search extends AppCompatActivity {

    ListView lv;
    SearchView sv;
    EditText nameEditText,bos;
    Button saveBtn,retrieveBtn,aa;
    CustomAdapter adapter;
    ArrayList<Planet> planets=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);





        lv= (ListView) findViewById(R.id.lv);
        sv= (SearchView) findViewById(R.id.sv);

        adapter=new CustomAdapter(this,planets);




        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                getPlanets(newText);
                return false;
            }
        });

    }



    private void displayDialog()
    {
        Dialog d=new Dialog(this);
        d.setTitle("SQLite Database");
        d.setContentView(R.layout.dialog_layout);

        nameEditText= (EditText) d.findViewById(R.id.nameEditTxt);
        bos=(EditText) d.findViewById(R.id.nameEditTxt);
        saveBtn= (Button) d.findViewById(R.id.saveBtn);
        retrieveBtn= (Button) d.findViewById(R.id.retrieveBtn);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save(nameEditText.getText().toString(),bos.getText().toString());
            }
        });
        retrieveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPlanets(null);
            }
        });

        d.show();
    }

    private void save(String name,String lastname)
    {
        DatabaseAdapter db=new DatabaseAdapter(this);
        db.openDB();
        if(db.add(name,lastname))
        {
            nameEditText.setText("");
        }else {
            Toast.makeText(this,"Unable To Save",Toast.LENGTH_SHORT).show();
        }

        db.closeDB();

        this.getPlanets(null);
    }

    private void getPlanets(String searchTerm)
    {
        planets.clear();

        DatabaseAdapter db=new DatabaseAdapter(this);
        db.openDB();
        Planet p=null;
        Cursor c=db.retrieve(searchTerm);
        while (c.moveToNext())
        {
            int id=c.getInt(0);
            String name=c.getString(1);

            p=new Planet();
            p.setId(id);
            p.setName(name);

            planets.add(p);
        }

        db.closeDB();

        lv.setAdapter(adapter);

    }


}
