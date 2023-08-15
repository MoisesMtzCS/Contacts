package com.example.contacts.feature.contact_details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.contacts.R
import com.example.contacts.databinding.ActivityContactDetailsBinding
import com.example.contacts.databinding.ActivityMainBinding
import com.example.contacts.feature.main.MainViewModel
import com.example.contacts.feature_tools.flow.launchAndRepeatOnLifecycle
import com.example.contacts.feature_tools.flow.observeFor
import com.example.contacts.shared.contact.domain.entity.Contact
import com.example.contacts.shared.contact.domain.use_case.get_contact.GetContactParams
import com.example.contacts.shared.contact.domain.use_case.get_contact.GetContactResponse
import com.example.contacts.shared.contact.domain.use_case.get_contact.GetContactStatus
import com.example.contacts.shared.contact.domain.use_case.get_contacts.GetContactsResponse
import com.example.contacts.util.clean.InfallibleStatus
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactDetailsActivity : AppCompatActivity() {

    /* Android resources */
    private val binding by lazy { ActivityContactDetailsBinding.inflate(layoutInflater) }
    private val viewModel: ContactDetailsViewModel by viewModels()

    /*****************************************************************************************
     * LIFECYCLE
     ****************************************************************************************/

    /** Called at create the screen. */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        launchGetContact()
    }

    /*****************************************************************************************
     * USE CASE - GET CONTACT BY ID
     ****************************************************************************************/

    /** Launch the flow to get contact. */
    private fun launchGetContact() {
        launchAndRepeatOnLifecycle {
            val contactID by lazy { intent.getStringExtra(CONTACT_ID_KEY) as String }
            val params = GetContactParams(contactID)
            val response = viewModel.launchGetContact(params)
            observeFor(response, ::getContactCollector)
        }
    }

    /** Collect the status for get contact. */
    private fun getContactCollector(status: GetContactStatus) {
        if (status is InfallibleStatus.Done) {
            manageGetContactResponse(status.value)
        }
    }

    /** Manage get contact [response] instance. */
    private fun manageGetContactResponse(response: GetContactResponse) {
        setupViews(response.contact)
    }

    /** Setup views. */
    private fun setupViews(contact: Contact){
        binding.tvName.text = contact.userName
        binding.tvNumber.text = contact.phone
        binding.tvEmail.text = contact.email
    }

    companion object {

        /* Key value for contact id arg. */
        const val CONTACT_ID_KEY: String = "CONTACT_ID_KEY"

    }

}