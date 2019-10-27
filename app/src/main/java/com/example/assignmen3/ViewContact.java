package com.example.assignmen3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;

public class ViewContact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contact);
        Gson gson = new Gson();
        Contacts ob = gson.fromJson(getIntent().getStringExtra("myjson"), Contacts.class);
        TextView nam=findViewById(R.id.nameVieww);
        TextView phnn=findViewById(R.id.phnVieww);
        nam.setText(ob.name);
        phnn.setText(ob.phone);

    }
}
