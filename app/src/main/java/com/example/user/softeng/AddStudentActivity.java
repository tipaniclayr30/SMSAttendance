package com.example.user.softeng;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AddStudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
    }
//hdhdghdg
    public void addStudent(View view){
        openDialog();
    }

    public void openDialog(){

        AddStudentDialog addDialog = new AddStudentDialog();
        addDialog.show(getSupportFragmentManager(), "add dialog");
    }
}
