package com.example.contacts.shared.contact.domain.use_case.create_contact

import com.example.contacts.shared.contact.domain.use_case.edit_contact.EditContactFailure
import com.example.contacts.util.clean.Failure

/** Possibles failures associated to create contact.  */
sealed class CreateContactFailure : Failure(){

    /** Failed to leave an empty name. */
    object InvalidName: CreateContactFailure()

    /** Failed to leave an empty number. */
    object InvalidPhone: CreateContactFailure()

    /** Failed to leave an empty email. */
    object InvalidEmail: CreateContactFailure()

}