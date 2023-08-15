package com.example.contacts.feature.create_contact

import androidx.lifecycle.ViewModel
import com.example.contacts.shared.contact.domain.use_case.create_contact.CreateContactParams
import com.example.contacts.shared.contact.domain.use_case.create_contact.CreateContactStatus
import com.example.contacts.shared.contact.domain.use_case.create_contact.CreateContactUseCase
import com.example.contacts.shared.contact.domain.use_case.get_contacts.GetContactsParams
import com.example.contacts.util.clean.InfallibleStatus
import com.example.contacts.util.clean.Status
import com.example.contacts.util.clean.onLeft
import com.example.contacts.util.clean.onRight
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class CreateContactViewModel @Inject constructor(
    private val createContactUseCase: CreateContactUseCase
) : ViewModel() {

    /*****************************************************************************************
     * USE CASE - GET CONTACTS
     ****************************************************************************************/

    /** Launch the flow to create contact. */
    fun launchCreateContact(params: CreateContactParams) =
        flow<CreateContactStatus> {
            emit(Status.Loading())
            createContactUseCase.run(params)
                .onLeft { emit(Status.Failed(it)) }
                .onRight { emit(Status.Done(it)) }
        }

}