package com.example.assignmen3;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ContactDao {
    @Insert
    void insert(List<Contacts> contact);

    @Insert
    void insert(Contacts contact);


    @Update
    void update(Contacts contact);

    @Delete
    void delete(Contacts contact);

    @Query("DELETE FROM contact_table")
    void deleteAllContacts();

    @Query("SELECT * FROM contact_table")
    List<Contacts> getAllContacts();
}
