package com.example.contacts.shared.contact.domain.use_case.add_contact

data class CreateContactParams(
    val name: String,
    val phone: String,
    val email: String
)