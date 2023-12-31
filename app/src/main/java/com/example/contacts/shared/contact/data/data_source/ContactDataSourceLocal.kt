package com.example.contacts.shared.contact.data.data_source

import com.example.contacts.shared.contact.domain.entity.Contact
import com.example.contacts.shared.contact.domain.use_case.delete_contact.DeleteContactResponse
import com.example.contacts.shared.contact.domain.use_case.edit_contact.EditContactResponse
import com.example.contacts.shared.contact.domain.use_case.get_contact.GetContactResponse
import com.example.contacts.shared.contact.domain.use_case.get_contacts.GetContactsResponse

interface ContactDataSourceLocal {

    /** Create a contact instance. */
    suspend fun createContact(
        name: String,
        phone: String,
        email: String,
    )

    /** Obtains all contacts. */
    suspend fun getContacts(): GetContactsResponse

    /** Obtains contact by id. */
    suspend fun getContactByID(contactId: String): GetContactResponse

    /** Remove contact. */
    suspend fun deleteContact(contact: Contact): DeleteContactResponse

    /** Edit the contact. */
    suspend fun editContact(contact: Contact): EditContactResponse

}