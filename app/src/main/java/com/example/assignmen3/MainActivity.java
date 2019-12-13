package com.example.assignmen3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends androidx.appcompat.app.AppCompatActivity{


    EditText email,password ;
    CheckBox cb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences= getSharedPreferences("loginData",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        if(sharedPreferences.getBoolean("checkbox",false))
        {
            email= findViewById(R.id.emailbox);
            email.setText(sharedPreferences.getString("email","not found"));
            password=findViewById(R.id.passwordbox);
            password.setText(sharedPreferences.getString("password","not found"));
            cb=findViewById(R.id.checkBox);
            cb.setChecked(true);
        }

        Button boo = findViewById(R.id.btsignIn);
        boo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sharedPreferences= getSharedPreferences("loginData",MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                cb=findViewById(R.id.checkBox);
                if(cb.isChecked())
                {
                    email= findViewById(R.id.emailbox);
                    password=findViewById(R.id.passwordbox);
                    editor.putBoolean("checkbox", cb.isChecked());
                    editor.putString("password", password.getText().toString());
                    editor.putString("email",email.getText().toString());
                    editor.commit();
                }
                else
                {
                   editor.putBoolean("checkbox", cb.isChecked());
                   editor.commit();
                }
                Intent i=new Intent(MainActivity.this, contactsActivity.class);
                startActivity(i);
            }
        });
        }






    }
