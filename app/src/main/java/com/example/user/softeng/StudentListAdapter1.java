package com.example.user.softeng;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;

import java.util.ArrayList;

public class StudentListAdapter1 extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<Model2> c;

    public StudentListAdapter1(Context context, int layout, ArrayList<Model2> c) {
        this.context = context;
        this.layout = layout;
        this.c = c;
    }

    @Override
    public int getCount() {
        return c.size();
    }

    @Override
    public Object getItem(int position) {
        return c.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder1{
        TextView name;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
       ViewHolder1 holder = new ViewHolder1();


        if (row==null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout,null);
            holder.name= row.findViewById(R.id.txtname);

            row.setTag(holder);
        }else{
            holder= (ViewHolder1) row.getTag();
        }

        Model2 studentlist = c.get(position);
        holder.name.setText(studentlist.getName()+  "");


        return row;


    }

}
