package com.example.user.softeng;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import java.text.DateFormat;
import java.util.Calendar;

public class AttendanceSet extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    Button btndate, btnsa , btnsr;
    static String date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_set);

        //BACK BUTTON
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Calendar c = Calendar.getInstance();
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());

        btndate = findViewById(R.id.btndate);
        btnsa = findViewById(R.id.btnsa);
        btnsr = findViewById(R.id.btnsr);

        btndate.setText(currentDateString);
        btndate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v4.app.DialogFragment datepicker = new DatePickerFragment();
                datepicker.show(getSupportFragmentManager(), "date picker");

            }
        });

        btnsr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(AttendanceSet.this,StudentRecord.class);
                startActivity(in);
            }
        });


        btnsa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                date = btndate.getText().toString();

                Intent in = new Intent(AttendanceSet.this,StudentAttendance.class);
                startActivity(in);
            }
        });

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());

        btndate.setText(currentDateString);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();

        if (id== android.R.id.home){
            this.finish();
        }

        return super.onOptionsItemSelected(item);

    }
}
