package com.example.contacts.shared.contact.data.data_source.local.model

import com.example.contacts.shared.contact.domain.entity.Contact

/** Convert ContactEntity to contact*/
fun ContactEntity.toContact(): Contact =
    Contact(
        contactID = contactID,
        userName = userName,
        phone = phone,
        email = email,
    )

/** Convert Contact to ContactEntity. */
fun Contact.toContactEntity(): ContactEntity =
    ContactEntity(
        contactID = contactID,
        userName = userName,
        phone = phone,
        email = email,
    )