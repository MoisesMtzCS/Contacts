package com.example.contacts.shared.contact

import com.example.contacts.shared.contact.data.ContactRepositoryImpl
import com.example.contacts.shared.contact.data.data_source.ContactDataSourceLocal
import com.example.contacts.shared.contact.data.data_source.local.ContactDataSourceLocalImp
import com.example.contacts.shared.contact.domain.ContactRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ContactBindModule {

    @Binds
    @Singleton
    abstract fun providesContactRepository(
        contactRepositoryImpl: ContactRepositoryImpl
    ): ContactRepository

    @Binds
    @Singleton
    abstract fun providesContactDataSourceLocal(
        contactDataSourceLocalImpl: ContactDataSourceLocalImp
    ): ContactDataSourceLocal

}