package com.example.user.softeng;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class SignInActivity extends AppCompatActivity {

    DatabaseAttendance db;
    ArrayList<User> u ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        db = new DatabaseAttendance(this);

        getSupportActionBar().setTitle("Attendance Monitoring with SMS");
    }

    public void signUp(View view){
        Intent signup= new Intent(this, SignUpActivity.class);
        startActivity(signup);
    }



    public void authenticate(View v){
        EditText username = findViewById(R.id.s_username);
        EditText password = findViewById(R.id.s_pass);

        if (username.getText().toString().isEmpty()||password.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(),"Fill in all fields",Toast.LENGTH_SHORT).show();
        }
        else{

            Boolean a = db.emailpass(username.getText().toString(),password.getText().toString());

            if(a==true){

                u = db.LogInUsers(username.getText().toString(),password.getText().toString());

               String name = u.get(0).name;
               int id = u.get(0).id;



                Toast.makeText(getApplicationContext(),"Successfully Login",Toast.LENGTH_SHORT).show();
                Intent home= new Intent(this, NavMenu.class);
                home.putExtra("username", name);
                home.putExtra("id", id);
                startActivity(home);

                username.setText(""); password.setText("");

            }else{
                Toast.makeText(getApplicationContext(),"Wrong Email or Password",Toast.LENGTH_SHORT).show();
                username.setText(username.getText().toString());
                password.setText(password.getText().toString());
            }

        }

    }

}

