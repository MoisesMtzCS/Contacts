package com.example.contacts.shared.contact.domain.use_case.delete_contact

import com.example.contacts.util.clean.InfallibleStatus

/** Type alias to manage the flow status */
typealias DeleteContactStatus = InfallibleStatus<DeleteContactResponse>