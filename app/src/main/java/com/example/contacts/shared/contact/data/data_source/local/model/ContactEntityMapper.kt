package com.example.contacts.shared.contact.data.data_source.local.model

import com.example.contacts.shared.contact.domain.entity.Contact

fun ContactEntity.toContact(): Contact =
    Contact(
        contactID = contactID,
        userName = userName,
        phone = phone,
        email = email,
    )

fun Contact.toContactEntity(): ContactEntity =
    ContactEntity(
        contactID = contactID,
        userName = userName,
        phone = phone,
        email = email,
    )

fun ContactEntity.Companion.formContact(contact: Contact): Contact =
    Contact(
        contactID = contact.contactID,
        userName = contact.userName,
        phone = contact.phone,
        email = contact.email,
    )