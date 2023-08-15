package com.example.contacts.shared.contact.domain.use_case.edit_contact

import com.example.contacts.shared.contact.domain.ContactRepository
import com.example.contacts.shared.contact.domain.use_case.create_contact.CreateContactFailure
import com.example.contacts.shared.contact.domain.use_case.create_contact.CreateContactResponse
import com.example.contacts.util.clean.Either
import com.example.contacts.util.clean.UseCase
import javax.inject.Inject

/**
 * Edit data contact.
 */
class EditContactUseCase @Inject constructor(
    private val repository: ContactRepository,
) : UseCase<EditContactResponse, EditContactParams, EditContactFailure>() {

    /** Execute use case. */
    override suspend fun run(params: EditContactParams): Either<EditContactFailure, EditContactResponse> {
        val (_, name, phone, email) = params.contact
        return when {
            name.isBlank() -> Either.Left(EditContactFailure.InvalidName)
            phone.isBlank() -> Either.Left(EditContactFailure.InvalidPhone)
            email.isBlank() -> Either.Left(EditContactFailure.InvalidEmail)
            else -> {
                repository.editContact(params.contact)
                Either.Right(EditContactResponse())
            }
        }
    }

}