package com.example.user.softeng;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

public class StudentRecord extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_record);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_student_record,new FragmentStudents1()).commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int res_id =item.getItemId();


        if (res_id== android.R.id.home){
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
