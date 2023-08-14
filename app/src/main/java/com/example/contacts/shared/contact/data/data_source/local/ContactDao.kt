package com.example.contacts.shared.contact.data.data_source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.contacts.shared.contact.data.data_source.local.model.ContactEntity

@Dao
interface ContactDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: ContactEntity)

    @Query("SELECT * FROM cont")
    suspend fun getAll():List<ContactEntity>

}