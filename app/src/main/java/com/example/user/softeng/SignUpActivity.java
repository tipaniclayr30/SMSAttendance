package com.example.user.softeng;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class SignUpActivity extends AppCompatActivity {


    DatabaseAttendance db;
    ArrayList<User> users;
    int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        db = new DatabaseAttendance(this);

        users  = db.selectUsers();
        id = users.size()+1;

    }

    public void adduser(View v){
        EditText name = findViewById(R.id.name);
        EditText username = findViewById(R.id.r_username);
        EditText password = findViewById(R.id.r_password);
        Button register = findViewById(R.id.btnCreate);


        if (name.getText().toString().equals("") || username.getText().toString().equals("") || password.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(),"FILL IN ALL FIELDS",Toast.LENGTH_SHORT).show();
        }
        else{
            register.isEnabled();

            if (password.length()==0 || password.length()<4){
                password.setError("Requires above 4 characters");
            }
            else{
                password.setError(null);

                Boolean cu = db.chkUser(username.getText().toString());

                if(cu==false){
                    Toast.makeText(getApplicationContext(),"USERNAME IS ALREADY EXIST.",Toast.LENGTH_SHORT).show();

                }else{
                    db.addUser(id,name.getText().toString(), username.getText().toString(),password.getText().toString());


                    Toast.makeText(getApplicationContext(),"SUCCESSFULLY REGISTERED",Toast.LENGTH_SHORT).show();

                    id +=1;

                    name.setText(""); username.setText(""); password.setText("");
                }

            }

        }


    }

    public void signIn(View view){
        Intent signin= new Intent(this, SignInActivity.class);
        startActivity(signin);
    }

}
