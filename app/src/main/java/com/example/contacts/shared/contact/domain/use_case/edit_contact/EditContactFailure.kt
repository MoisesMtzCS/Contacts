package com.example.contacts.shared.contact.domain.use_case.edit_contact

import com.example.contacts.util.clean.Failure

/** Possibles failures associated to edit contact.  */
sealed class EditContactFailure: Failure() {

    /** Failed to leave an empty name. */
    object InvalidName: EditContactFailure()

    /** Failed to leave an empty number. */
    object InvalidPhone: EditContactFailure()

    /** Failed to leave an empty email. */
    object InvalidEmail: EditContactFailure()

}