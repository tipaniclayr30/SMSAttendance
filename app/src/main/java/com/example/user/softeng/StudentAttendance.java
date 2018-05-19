package com.example.user.softeng;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class StudentAttendance extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_attendance);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_student,new FragmentStudents()).commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_student,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //PARA NI SA CTION NA MAHITABO

        int res_id =item.getItemId();

        if(res_id==R.id.save){
           FragmentStudents.saveAttendance();
            Toast.makeText(this,"SUCCESFULLY SAVED",Toast.LENGTH_SHORT).show();
        }
        if (res_id== android.R.id.home){
            this.finish();
        }


        return super.onOptionsItemSelected(item);
    }


}
