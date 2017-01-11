package com.example.enes.alumniapp.Database;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.enes.alumniapp.R;
import com.example.enes.alumniapp.Student.students;

import java.util.List;

/**
 * Created by Enes on 11.1.2017.
 */

public class Adapter extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private List<students> studentsList;

    public Adapter(Activity activity, List<students> courses) {

        layoutInflater = (LayoutInflater) activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        studentsList = courses;
    }
    @Override
    public int getCount() {
        return studentsList.size();
    }

    @Override
    public Object getItem(int position) {
        return studentsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       View view;
        view=layoutInflater.inflate(R.layout.all_students_list,null);
        TextView SID=(TextView)view.findViewById(R.id.ListStudentID);
        TextView name=(TextView)view.findViewById(R.id.ListStudentName);
        TextView date=(TextView)view.findViewById(R.id.ListRegTime);

        students st=studentsList.get(position);
        SID.setText("Student Number: "+st.getStudentID());
        name.setText(st.getFirstname()+" "+st.getLastname());
        date.setText(st.getRegTime());


        return view;
    }
}
