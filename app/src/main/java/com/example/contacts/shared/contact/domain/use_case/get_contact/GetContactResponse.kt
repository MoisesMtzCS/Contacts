package com.example.contacts.shared.contact.domain.use_case.get_contact

import com.example.contacts.shared.contact.domain.entity.Contact

/** Data response to get contact by id flow. */
data class GetContactResponse(
    val contact: Contact,
)