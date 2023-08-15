package com.example.contacts.feature.contact_details

import androidx.lifecycle.ViewModel
import com.example.contacts.shared.contact.domain.use_case.get_contact.GetContactParams
import com.example.contacts.shared.contact.domain.use_case.get_contact.GetContactStatus
import com.example.contacts.shared.contact.domain.use_case.get_contact.GetContactUseCase
import com.example.contacts.util.clean.InfallibleStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class ContactDetailsViewModel @Inject constructor(
    private val getContactUseCase: GetContactUseCase,
) : ViewModel() {

    /*****************************************************************************************
     * USE CASE - GET CONTACTS BY ID
     ****************************************************************************************/

    /** Launch the flow to get contact by id. */
    fun launchGetContact(params: GetContactParams) =
        flow<GetContactStatus> {
            val response = getContactUseCase.runInfallible(params)
            emit(InfallibleStatus.Done(response))
        }

}