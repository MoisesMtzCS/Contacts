package com.example.contacts.shared.contact.domain.use_case.get_contacts

import com.example.contacts.util.clean.InfallibleStatus

/** Type alias to manage the flow status */
typealias GetContactsStatus = InfallibleStatus<GetContactsResponse>