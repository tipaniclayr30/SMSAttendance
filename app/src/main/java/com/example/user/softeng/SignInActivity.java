package com.example.user.softeng;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignInActivity extends AppCompatActivity {

    DatabaseAttendance db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        db = new DatabaseAttendance(this);
    }

    public void add(View view){
        Intent addUser= new Intent(this, SignUpActivity.class);
        startActivity(addUser);
    }



    public void authenticate(View v){
        EditText username = findViewById(R.id.s_username);
        EditText password = findViewById(R.id.s_pass);


        Boolean a = db.emailpass(username.getText().toString(),password.getText().toString());

         if(a==true){

            Toast.makeText(getApplicationContext(),"Successfully Login",Toast.LENGTH_SHORT).show();

            Intent home= new Intent(this, NavMenu.class);
            Bundle b = new Bundle();
            b.putString("username",username.getText().toString());
            home.putExtras(b);
            startActivity(home);


        }else{
            Toast.makeText(getApplicationContext(),"Wrong Email or Password",Toast.LENGTH_SHORT).show();
        }


    }

}
