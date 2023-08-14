package com.example.contacts.shared.contact.domain.use_case.get_contacts

import com.example.contacts.shared.contact.domain.entity.Contact

class GetContactsResponse(
    val contacts: List<Contact>
)