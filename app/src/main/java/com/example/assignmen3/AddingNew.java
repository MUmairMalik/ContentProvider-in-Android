package com.example.assignmen3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddingNew extends AppCompatActivity {

    Button b;
    EditText nam;
    EditText phn;
    EditText emal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_new);

        b=findViewById(R.id.Button_save);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(AddingNew.this , "Saved", Toast.LENGTH_LONG).show();


                nam=findViewById(R.id.AddNew_name);
                phn=findViewById(R.id.AddNew_phone);
                emal=findViewById(R.id.AddNew_email);

                Contacts c=new Contacts(nam.getText().toString(),phn.getText().toString(),emal.getText().toString());
                ContactDatabase db = Room.databaseBuilder(getApplicationContext(),ContactDatabase.class, "contactdb").allowMainThreadQueries().build();
                db.contactDao().insert(c);
                Intent i=new Intent(AddingNew.this, contactsActivity.class);
                startActivity(i);

            }
        });

    }
}
