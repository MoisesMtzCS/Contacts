package com.example.contacts.shared_tools.data_base

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.contacts.shared.contact.data.data_source.local.ContactDao
import com.example.contacts.shared.contact.data.data_source.local.model.ContactEntity

@Database(
    entities = [
        ContactEntity::class,
    ],
    version = 1
)
abstract class ContactsDatabase : RoomDatabase() {

    /** Obtains an instance of [ContactDao]. */
    abstract fun contactDao(): ContactDao

    companion object {

        /* */
        private const val NAME: String = "contacts-database"

        /** */
        fun getInstance(applicationContext: Context): ContactsDatabase =
            Room.databaseBuilder(
                applicationContext,
                ContactsDatabase::class.java,
                NAME
            ).build()

    }

}