package com.example.contacts.feature.contact_details

import androidx.lifecycle.ViewModel
import com.example.contacts.shared.contact.domain.entity.Contact
import com.example.contacts.shared.contact.domain.use_case.delete_contact.DeleteContactParams
import com.example.contacts.shared.contact.domain.use_case.delete_contact.DeleteContactStatus
import com.example.contacts.shared.contact.domain.use_case.delete_contact.DeleteContactUseCase
import com.example.contacts.shared.contact.domain.use_case.get_contact.GetContactParams
import com.example.contacts.shared.contact.domain.use_case.get_contact.GetContactStatus
import com.example.contacts.shared.contact.domain.use_case.get_contact.GetContactUseCase
import com.example.contacts.util.clean.InfallibleStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class ContactDetailsViewModel @Inject constructor(
    private val getContactUseCase: GetContactUseCase,
    private val deleteContactUseCase: DeleteContactUseCase,
) : ViewModel() {

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
     * USE CASE - DELETE CONTACT
     ****************************************************************************************/

    /** Launch the flow to delete contact. */
    fun launchDeleteContact(params: DeleteContactParams) =
        flow<DeleteContactStatus> {
            val response = deleteContactUseCase.runInfallible(params)
            emit(InfallibleStatus.Done(response))
        }

}