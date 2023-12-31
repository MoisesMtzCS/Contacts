package com.example.contacts.shared.contact.data.data_source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.contacts.shared.contact.data.data_source.local.model.ContactEntity

@Dao
interface ContactDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: ContactEntity)

    @Query("SELECT * FROM contact")
    suspend fun getAll():List<ContactEntity>

    @Query("SELECT * FROM contact WHERE contact_id IS :id")
    suspend fun getById(id: String): ContactEntity

    @Delete
    suspend fun delete(entity: ContactEntity)

}