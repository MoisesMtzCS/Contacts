package com.example.contacts.shared.contact.data

import com.example.contacts.shared.contact.data.data_source.ContactDataSourceLocal
import com.example.contacts.shared.contact.domain.ContactRepository
import com.example.contacts.shared.contact.domain.use_case.get_contact.GetContactResponse
import com.example.contacts.shared.contact.domain.use_case.get_contacts.GetContactsResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ContactRepositoryImpl @Inject constructor(
    private val dataSourceLocal: ContactDataSourceLocal,
) : ContactRepository {

    /** Create a contact instance. */
    override suspend fun createContact(name: String, phone: String, email: String) =
        dataSourceLocal.createContact(name, phone, email)

    /** Obtains all contacts. */
    override suspend fun getContacts(): GetContactsResponse =
        dataSourceLocal.getContacts()

    /** Obtains contact by id. */
    override suspend fun getContactByID(contactId: String): GetContactResponse =
        dataSourceLocal.getContactByID(contactId)

}