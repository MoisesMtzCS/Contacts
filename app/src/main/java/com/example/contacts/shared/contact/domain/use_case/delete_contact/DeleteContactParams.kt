package com.example.contacts.shared.contact.domain.use_case.delete_contact

import com.example.contacts.shared.contact.domain.entity.Contact

/** Required params for remove contact. */
data class DeleteContactParams(
    val contact: Contact,
)