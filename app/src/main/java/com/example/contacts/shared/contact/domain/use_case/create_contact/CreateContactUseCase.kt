package com.example.contacts.shared.contact.domain.use_case.create_contact

import com.example.contacts.shared.contact.domain.ContactRepository
import com.example.contacts.util.clean.Either
import com.example.contacts.util.clean.UseCase
import javax.inject.Inject

/**
 * Create contact.
 */
class CreateContactUseCase @Inject constructor(
    private val repository: ContactRepository
) : UseCase<CreateContactResponse, CreateContactParams, CreateContactFailure>() {

    /** Execute use case. */
    override suspend fun run(params: CreateContactParams): Either<CreateContactFailure, CreateContactResponse> {
        val (name, phone, email) = params
        return when {
            name.isBlank() -> Either.Left(CreateContactFailure.InvalidName)
            phone.isBlank() -> Either.Left(CreateContactFailure.InvalidPhone)
            email.isBlank() -> Either.Left(CreateContactFailure.InvalidEmail)
            else -> {
                repository.createContact(name, phone, email)
                Either.Right(CreateContactResponse())
            }
        }
    }

}