package com.example.user.softeng;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Jenny Rose on 4/28/18.
 */

public class AddStudentDialog extends AppCompatDialogFragment {

    private EditText txtName, txtID, txtParentName, txtCellNum;
    DatabaseAttendance db;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        db = new DatabaseAttendance(getActivity());

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_studentdialog, null);

        builder.setView(view)
                .setTitle("Add Student")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dismiss();
                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                            addstudent();
                    }
                });

        txtID = view.findViewById(R.id.txtID);
        txtName = view.findViewById(R.id.name);
        txtParentName = view.findViewById(R.id.txtParentName);
        txtCellNum = view.findViewById(R.id.txtCellNum);

        return builder.create();
    }
    public void addstudent(){


         db.addStudent(NavMenu.ui,FragmentHomeClass.ci, Integer.parseInt(txtID.getText().toString()), txtName.getText().toString(), txtParentName.getText().toString(), txtCellNum.getText().toString());

         getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_student_record,new FragmentStudents1()).commit();
        Toast.makeText(getActivity(), "NEW STUDENT ADDED SUCCESSFULLY", Toast.LENGTH_SHORT).show();

        txtName.setText("");
        txtID.setText("");
        txtParentName.setText("");
        txtCellNum.setText("");


    }

}
