package com.example.user.softeng;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;

public class AddClassDialog  extends AppCompatDialogFragment {
    private EditText editTextName;
    private EditText editTextRoom;
    private EditText editTextTime,editTextDays,userId;
    int drop=2;
    int days;

    DatabaseAttendance db;
    ArrayList<Class> c;

    private Switch dropswitch;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

     db = new DatabaseAttendance(getActivity());


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_classdialog,null);


        builder.setView(view)
                .setTitle("Add Class")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismiss();
                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                      addclass();

                    }
                });

        editTextName = view.findViewById(R.id.editTextRoom);
        editTextRoom = view.findViewById(R.id.editTextName);
        editTextTime = view.findViewById(R.id.editTextTime);
        editTextDays = view.findViewById(R.id.editTextDays);
        userId = view.findViewById(R.id.userId);
        dropswitch = view.findViewById(R.id.dropswitch);
        editTextDays.setEnabled(false);

        dropswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    editTextDays.setEnabled(true);
                    drop =1;
                } else {
                    editTextDays.setEnabled(false);
                    editTextDays.setText("");
                    drop = 2;
                }

            }
        });

        return builder.create();
    }


    public void addclass(){

        if(drop==2){
            days = 0;
        }else{
            days = Integer.parseInt(editTextDays.getText().toString());
        }

        db.addClass(NavMenu.ui,editTextName.getText().toString(),editTextRoom.getText().toString(), editTextTime.getText().toString(),drop,days);


        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new FragmentHomeClass()).commit();



        Toast.makeText(getActivity(), "NEW CLASS ADDED SUCCESSFULLY", Toast.LENGTH_SHORT).show();

        editTextName.setText("");
        editTextRoom.setText("");
        editTextTime.setText("");
        editTextDays.setText("");
        dropswitch.setChecked(false);
        editTextDays.setText("");
        editTextDays.setEnabled(false);


    }

    public void updateClassList() {
        ArrayList<Model> clist;

        Toast.makeText(getActivity(), "HI", Toast.LENGTH_SHORT).show();

        clist = new ArrayList<>();

        clist.clear();
        db = new DatabaseAttendance(getActivity());
        c = db.selectClass(NavMenu.ui);

        for (int i= 0 ; i< c.size(); i++ ) {

            clist.add(new Model(c.get(i).subject,c.get(i).cde,c.get(i).tme,c.get(i).drop,c.get(i).numdays));
        }

    }

}
