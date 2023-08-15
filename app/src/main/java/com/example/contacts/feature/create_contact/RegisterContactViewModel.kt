package com.example.contacts.feature.create_contact

import androidx.lifecycle.ViewModel
import com.example.contacts.shared.contact.domain.entity.Contact
import com.example.contacts.shared.contact.domain.use_case.create_contact.CreateContactParams
import com.example.contacts.shared.contact.domain.use_case.create_contact.CreateContactStatus
import com.example.contacts.shared.contact.domain.use_case.create_contact.CreateContactUseCase
import com.example.contacts.shared.contact.domain.use_case.edit_contact.EditContactParams
import com.example.contacts.shared.contact.domain.use_case.edit_contact.EditContactStatus
import com.example.contacts.shared.contact.domain.use_case.edit_contact.EditContactUseCase
import com.example.contacts.shared.contact.domain.use_case.get_contact.GetContactParams
import com.example.contacts.shared.contact.domain.use_case.get_contact.GetContactUseCase
import com.example.contacts.util.clean.Status
import com.example.contacts.util.clean.onLeft
import com.example.contacts.util.clean.onRight
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class RegisterContactViewModel @Inject constructor(
    private val createContactUseCase: CreateContactUseCase,
    private val getContactUseCase: GetContactUseCase,
    private val editContactUseCase: EditContactUseCase,
) : ViewModel() {

    /*****************************************************************************************
     * USE CASE - CREATE CONTACTS
     ****************************************************************************************/

    /** Launch the flow to create contact. */
    fun launchCreateContact(params: CreateContactParams) =
        flow<CreateContactStatus> {
            emit(Status.Loading())
            createContactUseCase.run(params)
                .onLeft { emit(Status.Failed(it)) }
                .onRight { emit(Status.Done(it)) }
        }

    /*****************************************************************************************
     * USE CASE - GET CONTACTS BY ID
     ****************************************************************************************/

    /** Launch the flow to get contact by id. */
    fun getContact(contactId: String): Contact = runBlocking {
        val params = GetContactParams(contactId)
        val response = getContactUseCase.runInfallible(params)
        response.contact
    }

    /*****************************************************************************************
     * USE CASE - EDIT CONTACTS
     ****************************************************************************************/

    /** Launch the flow to edit contact. */
    fun launchEditContact(params: EditContactParams) =
        flow<EditContactStatus> {
            emit(Status.Loading())
            editContactUseCase.run(params)
                .onLeft { emit(Status.Failed(it)) }
                .onRight { emit(Status.Done(it)) }
        }

}