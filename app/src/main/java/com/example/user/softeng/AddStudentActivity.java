package com.example.user.softeng;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddStudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
    }

    public void addStudent(View view){
        openDialog();
    }

    public void openDialog(){

        AddDialog addDialog = new AddDialog();
        addDialog.show(getSupportFragmentManager(), "add dialog");
    }
}
