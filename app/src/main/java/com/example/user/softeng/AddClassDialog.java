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
import android.widget.Toast;

public class AddClassDialog  extends AppCompatDialogFragment {
    private EditText editTextName;
    private EditText editTextRoom;
    private EditText editTextTime,editTextDays;

    private Switch dropswitch;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

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

                    }
                });

        editTextName = view.findViewById(R.id.editTextName);
        editTextRoom = view.findViewById(R.id.editTextRoom);
        editTextDays = view.findViewById(R.id.editTextDays);
        dropswitch = view.findViewById(R.id.dropswitch);
        editTextDays.setEnabled(false);

        dropswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    editTextDays.setEnabled(true);
                } else {
                    editTextDays.setEnabled(false);
                    editTextDays.setText("");
                }
            }
        });

        return builder.create();
    }


}
