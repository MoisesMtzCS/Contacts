package com.example.contacts.shared.contact.domain.use_case.add_contact

import com.example.contacts.shared.contact.domain.ContactRepository
import com.example.contacts.util.clean.Either
import com.example.contacts.util.clean.UseCase
import javax.inject.Inject

class CreateContactUseCase @Inject constructor(
    private val repository: ContactRepository
) : UseCase<CreateContactResponse, CreateContactParams, CreateContactFailure>() {

    /** Run use case. */
    override suspend fun run(params: CreateContactParams): Either<CreateContactFailure, CreateContactResponse> {
        val (name, phone, email) = params
        return when {
            name.isBlank() -> Either.Left(CreateContactFailure.InvalidName)
            phone.isBlank() -> Either.Left(CreateContactFailure.InvalidPhone)
            else -> {
                repository.createContact(name, phone, email)
                Either.Right(CreateContactResponse())
            }
        }
    }

}