package com.example.assignmen3;

import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
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
        Cursor cur = null;
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            // cur = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null);


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
                            Contacts cn = new Contacts(name, phoneNo);
                            contact.add(cn);

                        }
                        pCur.close();
                    }


                }
            }
        } else {
            requestPermission();
        }
        if (cur == null) {
        /*    ContentResolver cr = getContentResolver();
            // String id = cur.getString(
            //       cur.getColumnIndex(ContactsContract.Contacts._ID));
            cur = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

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
                    Contacts cn = new Contacts(name, phoneNo);
                    contact.add(cn);
                }
                pCur.close();
            }




*/



            /*cursor.moveToFirst();
                while (cursor.moveToNext()) {
                    String n = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    String p = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    Contacts cc = new Contacts(n, p);
                    contact.add(cc);
                }


/*
       Contacts c=new Contacts("ahmn","56789");
       contact.add(c);
        c=new Contacts("ahmn1","56789");
        contact.add(c);
        c=new Contacts("ahmn2","56789");

        contact.add(c);
        c=new Contacts("ahm3n","56789");

        contact.add(c);
        c=new Contacts("ahm4n","56789");
        contact.add(c);
        c=new Contacts("ahm5n","56789");
        contact.add(c);
        c=new Contacts("ahm6n","56789");
        contact.add(c);
        c=new Contacts("ahm5n","56789");
        contact.add(c);
        c=new Contacts("ahm6n","56789");
        contact.add(c);
*/
        }


        RecyclerView list = findViewById(R.id.recycle);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(new RAdapter(contact, new RAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Contacts item) {

                Toast.makeText(contactsActivity.this , (String) "chceking", Toast.LENGTH_LONG).show();
                Gson gson = new Gson();
                Intent intent=new Intent(contactsActivity.this,ViewContact.class);
                String myJson = gson.toJson(item);
                intent.putExtra("myjson", myJson);
                startActivity(intent);

            }
        }));


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
/*


    private ArrayList getAllContacts() {
        ArrayList<String> nameList = new ArrayList<>();
        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);
        if ((cur != null ? cur.getCount() : 0) > 0) {
            while (cur != null && cur.moveToNext()) {
                String id = cur.getString(
                        cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(cur.getColumnIndex(
                        ContactsContract.Contacts.DISPLAY_NAME));
                nameList.add(name);

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
                        numberArray.add(phoneNo);

                    }
                    pCur.close();
                }
            }
        }
        if (cur != null) {
            cur.close();
        }
        return nameList;
    }

*/


}
