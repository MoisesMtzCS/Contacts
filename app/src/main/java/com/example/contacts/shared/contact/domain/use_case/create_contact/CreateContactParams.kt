package com.example.contacts.shared.contact.domain.use_case.create_contact

/** Required params for create contact. */
data class CreateContactParams(
    val name: String,
    val phone: String,
    val email: String
)