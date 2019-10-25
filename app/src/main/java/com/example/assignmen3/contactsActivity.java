package com.example.assignmen3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

public class contactsActivity extends AppCompatActivity {
    List<Contacts> contact ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        contact = new ArrayList<Contacts>();
       /* Cursor cursor=getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,null,null,null);

        if(cursor!=null) {
            cursor.moveToFirst();
            while(cursor.moveToNext()) {
                String n = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                name.add(n);
                String p = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                phone.add(p);

            }
        }
*/
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
        RecyclerView list=findViewById(R.id.recycle);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(new RAdapter(contact));



    }
}
