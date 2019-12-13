package com.example.assignmen3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

public class ViewContact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contact);
        Gson gson = new Gson();
        final Contacts ob = gson.fromJson(getIntent().getStringExtra("myjson"), Contacts.class);
        TextView nam=findViewById(R.id.nameVieww);
        TextView phnn=findViewById(R.id.phnVieww);
        TextView email=findViewById(R.id.boxEmail);


        nam.setText(ob.name);
        phnn.setText(ob.phone);
        email.setText(ob.email);
        ContactDatabase db = Room.databaseBuilder(getApplicationContext(),ContactDatabase.class, "contactdb").allowMainThreadQueries().build();

        Button b=findViewById(R.id.button_delete);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactDatabase db = Room.databaseBuilder(getApplicationContext(),ContactDatabase.class, "contactdb").allowMainThreadQueries().build();
                Toast.makeText(ViewContact.this , (String) ob.name.toString()+ " is deleted", Toast.LENGTH_LONG).show();

                db.contactDao().delete(ob);
                Intent i=new Intent(ViewContact.this,contactsActivity.class);
                startActivity(i);

            }
        });

    }
}
