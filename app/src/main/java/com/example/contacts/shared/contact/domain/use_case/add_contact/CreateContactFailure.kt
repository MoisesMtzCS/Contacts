package com.example.contacts.shared.contact.domain.use_case.add_contact

import com.example.contacts.util.clean.Failure

sealed class CreateContactFailure : Failure(){

    /** */
    object InvalidName: CreateContactFailure()

    /** */
    object InvalidPhone: CreateContactFailure()

}