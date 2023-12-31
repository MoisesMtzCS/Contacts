package com.example.contacts.shared.contact

import com.example.contacts.shared.contact.data.data_source.local.ContactDao
import com.example.contacts.shared_tools.data_base.ContactsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ContactProviderModule {

    @Provides
    @Singleton
    fun providesContactDao(
        contactDataBase: ContactsDatabase
    ): ContactDao = contactDataBase.contactDao()

}