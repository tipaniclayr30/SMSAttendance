package com.example.user.softeng;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Jenny Rose on 4/28/18.
 */

public class AddDialog extends AppCompatDialogFragment {

    private EditText txtName, txtID, txtParentName, txtCellNum;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog, null);

        builder.setView(view)
                .setTitle("Add Class")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

        txtID = view.findViewById(R.id.txtID);
        txtName = view.findViewById(R.id.txtName);
        txtParentName = view.findViewById(R.id.txtParentName);
        txtCellNum = view.findViewById(R.id.txtCellNum);

        return builder.create();
    }
}
