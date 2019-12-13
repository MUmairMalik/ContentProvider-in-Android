package com.example.assignmen3;

import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

public class contactsActivity extends AppCompatActivity {
    List<Contacts> contact ;
    public static final int REQUEST_READ_CONTACTS = 79;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        contact = new ArrayList<Contacts>();
        ContactDatabase db = Room.databaseBuilder(getApplicationContext(),ContactDatabase.class, "contactdb").allowMainThreadQueries().build();

        Cursor cur = null;
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {

            ContentResolver cr = getContentResolver();
            cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                    null, null, null, null);
            if ((cur != null ? cur.getCount() : 0) > 0) {
                while (cur != null && cur.moveToNext()) {
                    String id = cur.getString(
                            cur.getColumnIndex(ContactsContract.Contacts._ID));
                    String name = cur.getString(cur.getColumnIndex(
                            ContactsContract.Contacts.DISPLAY_NAME));
                    if (cur.getInt(cur.getColumnIndex(
                            ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                        Cursor pCur = cr.query(
                                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                null,
                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                                new String[]{id}, null);
                        while (pCur.moveToNext()) {
                            String phoneNo = pCur.getString(pCur.getColumnIndex(
                                    ContactsContract.CommonDataKinds.Phone.NUMBER));
                            String email=getEmail(id);
                            Contacts cn = new Contacts(name, phoneNo,email);
                            contact.add(cn);

                        }
                        pCur.close();
                    }


                }


          }
            db.contactDao().insert(contact);

        } else {
            requestPermission();
        }

        contact=db.contactDao().getAllContacts();
        RecyclerView list = findViewById(R.id.recycle);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(new RAdapter(contact, new RAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Contacts item) {

                Gson gson = new Gson();
                Intent intent=new Intent(contactsActivity.this,ViewContact.class);
                String myJson = gson.toJson(item);
                intent.putExtra("myjson", myJson);
                startActivity(intent);

            }
        }));

        Button bt=findViewById(R.id.button_addNew);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(contactsActivity.this,AddingNew.class);
                startActivity(i);

            }
        });


    }
    private void requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,android.Manifest.permission.READ_CONTACTS)) {
            // show UI part if you want here to show some rationale !!!
        } else {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.READ_CONTACTS},
                    REQUEST_READ_CONTACTS);
        }
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.READ_CONTACTS)) {
        } else {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.READ_CONTACTS},
                    REQUEST_READ_CONTACTS);
        }
    }

    public String getEmail(String contactId) {
        String emailStr = "";
        final String[] projection = new String[]{ContactsContract.CommonDataKinds.Email.DATA,
                ContactsContract.CommonDataKinds.Email.TYPE};

        Cursor emailq = getContentResolver().query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, projection, ContactsContract.Data.CONTACT_ID + "=?", new String[]{contactId}, null);

        if (emailq.moveToFirst()) {
            final int contactEmailColumnIndex = emailq.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA);

            while (!emailq.isAfterLast()) {
                emailStr = emailStr + emailq.getString(contactEmailColumnIndex);
                emailq.moveToNext();
            }
        }
        return emailStr;
    }
}
