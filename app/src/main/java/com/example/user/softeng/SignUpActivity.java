package com.example.user.softeng;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SignUpActivity extends AppCompatActivity {


    DatabaseAttendance db;
    ArrayList<User> users;
    int id;
    LinearLayout l;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
       l= findViewById(R.id.lin);

        db = new DatabaseAttendance(this);

        users  = db.selectUsers();
        id = users.size()+1;

        for (int i= 0 ; i< users.size(); i++ ){
            Button b = new Button(getApplicationContext());
            b.setText(users.get(i).username + " "+ users.get(i).id);
            l.addView(b);
        }

    }

    public void adduser(View v){
        EditText name = findViewById(R.id.name);
        EditText username = findViewById(R.id.r_username);
        EditText password = findViewById(R.id.r_password);

        Boolean cu = db.chkUser(username.getText().toString());

        if(cu==false){
            Toast.makeText(getApplicationContext(),"USERNAME IS ALREADY EXIST.",Toast.LENGTH_SHORT).show();

        }else{
            db.addUser(id,name.getText().toString(), username.getText().toString(),password.getText().toString());

            Button b = new Button(getApplicationContext());
            b.setText(name.getText()+" "+ id);
            l.addView(b);

            Toast.makeText(getApplicationContext(),"SUCCESSFULLY REGISTERED",Toast.LENGTH_SHORT).show();


            id +=1;
        }



    }

    public void back(View view){
        Intent back= new Intent(this, SignInActivity.class);
        startActivity(back);

    }

}
