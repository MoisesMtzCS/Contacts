package com.example.contacts.shared.contact.domain

import com.example.contacts.shared.contact.domain.use_case.get_contacts.GetContactsResponse

interface ContactRepository {

    /** Create a contact instance. */
    suspend fun createContact(
        name: String,
        phone: String,
        email: String,
    )

    /** Obtains all contacts. */
    suspend fun getContacts(): GetContactsResponse

}