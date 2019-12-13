package com.example.assignmen3;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database (entities ={Contacts.class}, version = 1)
public abstract class ContactDatabase extends RoomDatabase {

    public abstract ContactDao contactDao();
}
