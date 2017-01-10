package com.example.enes.alumniapp.Student;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.enes.alumniapp.R;
import com.example.enes.alumniapp.model.students;

import java.util.List;

/**
 * Created by Enes on 6.1.2017.
 */

public class ListStudents extends BaseAdapter {

    TextView ID,Name,Reg;
    private LayoutInflater layoutInflater;
    private List<students>studentsList;

    public ListStudents(Activity activity,List<students> studentses){
        layoutInflater=(LayoutInflater)activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        studentsList=studentses;
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

        ID=(TextView)view.findViewById(R.id.ListStudentID);
        Name=(TextView)view.findViewById(R.id.ListStudentName);
        Reg=(TextView)view.findViewById(R.id.ListRegTime);

        students st=studentsList.get(position);

        ID.setText(st.getID());
        Name.setText(st.getFirstname());//+" "+st.getLastname());
        Reg.setText(st.getLastname());


        return view;
    }
}
