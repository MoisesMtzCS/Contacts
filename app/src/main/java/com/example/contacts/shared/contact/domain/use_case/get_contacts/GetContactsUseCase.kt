package com.example.contacts.shared.contact.domain.use_case.get_contacts

import com.example.contacts.shared.contact.domain.ContactRepository
import com.example.contacts.util.clean.InfallibleUseCase
import javax.inject.Inject

/**
 * Obtains all contacts.
 */
class GetContactsUseCase @Inject constructor(
    private val repository: ContactRepository
) : InfallibleUseCase<GetContactsResponse, GetContactsParams>() {

    /** Run infallible use case. */
    override suspend fun runInfallible(params: GetContactsParams): GetContactsResponse =
        repository.getContacts()

}