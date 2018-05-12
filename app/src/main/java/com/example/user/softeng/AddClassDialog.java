package com.example.user.softeng;

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

import java.util.ArrayList;

public class AddClassDialog  extends AppCompatDialogFragment {
    private EditText editTextName;
    private EditText editTextRoom;
    private EditText editTextTime,editTextDays,userId;
    int drop,days;

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
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
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


        editTextName.setText("");
        editTextRoom.setText("");
        editTextTime.setText("");
        editTextDays.setText("");
        dropswitch.setChecked(false);
        editTextDays.setText("");
        editTextDays.setEnabled(false);

    }

}
