package com.example.assignmen3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

public class contacts extends AppCompatActivity {
    List<String> name ;
    List<String> phone ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        name = new ArrayList<String>();
        phone = new ArrayList<String>();
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
        String s=new String("Ali");
        name.add(s);
        s=new String("123");
        phone.add(s);
        s=new String("Ali1");
        name.add(s);
        s=new String("234");
        phone.add(s);
        s=new String("wes");
        name.add(s);
        s=new String("432");
        phone.add(s);
        s=new String("Aqq");
        name.add(s);
        s=new String("5432");
        phone.add(s);
        s=new String("ytre");
        name.add(s);
        s=new String("234");
        phone.add(s);

        RecyclerView list=findViewById(R.id.recycle);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(new RAdapter(name,phone));



    }
}
