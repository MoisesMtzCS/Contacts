package com.example.contacts.shared.contact.domain.use_case.get_contact

import com.example.contacts.shared.contact.domain.ContactRepository
import com.example.contacts.util.clean.InfallibleUseCase
import javax.inject.Inject

/**
 * Get contact by id.
 */
class GetContactUseCase @Inject constructor(
    private val repository: ContactRepository
): InfallibleUseCase<GetContactResponse, GetContactParams>(){

    /** Run infallible use case. */
    override suspend fun runInfallible(params: GetContactParams): GetContactResponse =
        repository.getContactByID(params.contactId)

}