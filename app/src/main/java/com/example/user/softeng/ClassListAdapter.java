package com.example.user.softeng;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ClassListAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<Model> c;

    public ClassListAdapter(Context context, int layout, ArrayList<Model> c) {
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

    private class ViewHolder{
        TextView subject,ccode,ctime;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        ViewHolder holder = new ViewHolder();

        if (row==null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout,null);
            holder.subject = row.findViewById(R.id.txtsubject);
            holder.ccode = row.findViewById(R.id.txtcode);
            holder.ctime = row.findViewById(R.id.txttime);
            row.setTag(holder);
        }else{
            holder= (ViewHolder) row.getTag();
        }

        Model classlist = c.get(position);

        holder.subject.setText(classlist.getSubject()+  "");
        holder.ccode.setText(classlist.getCde()+"");
        holder.ctime.setText(classlist.getTme()+"");

        return row;
    }
}
