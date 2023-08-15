package com.example.contacts.shared.contact.domain.use_case.delete_contact

import com.example.contacts.shared.contact.domain.ContactRepository
import com.example.contacts.util.clean.InfallibleUseCase
import javax.inject.Inject

/**
 * Remove contact.
 */
class DeleteContactUseCase @Inject constructor(
    private val repository: ContactRepository
) : InfallibleUseCase<DeleteContactResponse, DeleteContactParams>() {

    /** Execute infallible use case. */
    override suspend fun runInfallible(params: DeleteContactParams): DeleteContactResponse =
        repository.deleteContact(params.contact)

}