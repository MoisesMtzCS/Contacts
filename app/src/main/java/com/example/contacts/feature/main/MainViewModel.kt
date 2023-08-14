package com.example.contacts.feature.main

import androidx.lifecycle.ViewModel
import com.example.contacts.shared.contact.domain.use_case.get_contacts.GetContactsParams
import com.example.contacts.shared.contact.domain.use_case.get_contacts.GetContactsStatus
import com.example.contacts.shared.contact.domain.use_case.get_contacts.GetContactsUseCase
import com.example.contacts.util.clean.InfallibleStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getContactsUseCase: GetContactsUseCase,
) : ViewModel() {

    /*****************************************************************************************
     * USE CASE - GET CONTACTS
     ****************************************************************************************/

    /** Launch the flow to get contacts. */
    fun launchGetContacts() =
        flow<GetContactsStatus> {
            val params = GetContactsParams()
            val response = getContactsUseCase.runInfallible(params)
            emit(InfallibleStatus.Done(response))
        }

}