package com.example.contacts.shared.contact.data.data_source.local

import com.example.contacts.shared.contact.data.data_source.ContactDataSourceLocal
import com.example.contacts.shared.contact.data.data_source.local.model.ContactEntity
import com.example.contacts.shared.contact.data.data_source.local.model.toContact
import com.example.contacts.shared.contact.data.data_source.local.model.toContactEntity
import com.example.contacts.shared.contact.domain.entity.Contact
import com.example.contacts.shared.contact.domain.use_case.delete_contact.DeleteContactResponse
import com.example.contacts.shared.contact.domain.use_case.get_contact.GetContactResponse
import com.example.contacts.shared.contact.domain.use_case.get_contacts.GetContactsResponse
import com.example.contacts.util.segurity.randomUUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ContactDataSourceLocalImp @Inject constructor(
    private val contactDao: ContactDao,
) : ContactDataSourceLocal {

    /** Create a contact instance. */
    override suspend fun createContact(name: String, phone: String, email: String) {
        val contact = Contact(
            contactID = randomUUID(),
            userName = name,
            phone = phone,
            email = email
        )
        val contactEntity = contact.toContactEntity()
        contactDao.insert(contactEntity)
    }

    /** Obtains all contacts. */
    override suspend fun getContacts(): GetContactsResponse {
        val entities = contactDao.getAll()
        val contacts = entities.map(ContactEntity::toContact)
        return GetContactsResponse(contacts)
    }

    /** Obtains contact by id. */
    override suspend fun getContactByID(contactId: String): GetContactResponse {
        val entity: ContactEntity = contactDao.getById(contactId)
        val contact = entity.toContact()
        return GetContactResponse(contact)
    }

    /** Remove contact by id. */
    override suspend fun deleteContact(contact: Contact): DeleteContactResponse {
        val contactEntity = contact.toContactEntity()
        contactDao.delete(contactEntity)
        return DeleteContactResponse()
    }

}