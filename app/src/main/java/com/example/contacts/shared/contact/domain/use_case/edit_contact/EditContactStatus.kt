package com.example.contacts.shared.contact.domain.use_case.edit_contact

import com.example.contacts.util.clean.Status

/** Type alias to manage the flow status */
typealias EditContactStatus = Status<EditContactFailure, EditContactResponse>