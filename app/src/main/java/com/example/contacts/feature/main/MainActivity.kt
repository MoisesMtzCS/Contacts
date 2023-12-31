package com.example.contacts.feature.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contacts.databinding.ActivityMainBinding
import com.example.contacts.feature.contact_details.ContactDetailsActivity
import com.example.contacts.feature.register_contact.RegisterContactActivity
import com.example.contacts.feature.main.adapter.ContactAdapter
import com.example.contacts.feature_tools.flow.launchAndRepeatOnLifecycle
import com.example.contacts.feature_tools.flow.observeFor
import com.example.contacts.shared.contact.domain.entity.Contact
import com.example.contacts.shared.contact.domain.use_case.get_contacts.GetContactsResponse
import com.example.contacts.shared.contact.domain.use_case.get_contacts.GetContactsStatus
import com.example.contacts.util.clean.InfallibleStatus
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    /* Android resources */
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel: MainViewModel by viewModels()

    /*****************************************************************************************
     * LIFECYCLE
     ****************************************************************************************/

    /** Called at create the screen. */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    /** Called at start the screen. */
    override fun onStart() {
        super.onStart()
        setupViews()
    }

    /*****************************************************************************************
     * VIEWS - SETUP
     ****************************************************************************************/

    /** Setup the views data. */
    private fun setupViews() {
        setupActions()
        launchGetContacts()
    }

    /** Setup the actions. */
    private fun setupActions() {
        binding.floatingActionButton.setOnClickListener(::navigateToCreateContact)
    }

    /** Navigate to create contact. */
    private fun navigateToCreateContact(view: View) {
        val intent = Intent(this, RegisterContactActivity::class.java)
        startActivity(intent)
    }

    /** Setup the recycler view contacts. */
    private fun fillRecyclerView(contacts: List<Contact>) {
        val contactAdapter = ContactAdapter(contacts, ::onClientClickListener)
        binding.rvContact.adapter = contactAdapter
        binding.rvContact.layoutManager = LinearLayoutManager(this)
    }

    /** Called at moment that [contact] item are clicked. */
    private fun onClientClickListener(contact: Contact) {
        navigateToContactDetails(contact.contactID)
    }

    /** Navigate to contact details. */
    private fun navigateToContactDetails(contactId: String) {
        val intent = Intent(this, ContactDetailsActivity::class.java)
        intent.putExtra(ContactDetailsActivity.CONTACT_ID_KEY, contactId)
        startActivity(intent)
    }

    /*****************************************************************************************
     * USE CASE - GET CONTACTS
     ****************************************************************************************/

    /** Launch the flow to get contacts. */
    private fun launchGetContacts() {
        launchAndRepeatOnLifecycle {
            val response = viewModel.launchGetContacts()
            observeFor(response, ::getContactsCollector)
        }
    }

    /** Collect the status for get contacts. */
    private fun getContactsCollector(status: GetContactsStatus) {
        if (status is InfallibleStatus.Done) {
            manageGetContactsResponse(status.value)
        }
    }

    /** Manage get contacts [response] instance. */
    private fun manageGetContactsResponse(response: GetContactsResponse) {
        fillRecyclerView(response.contacts)
        setupVisibilityView(response.contacts)
    }

    /** Setup visibility views */
    private fun setupVisibilityView(contacts: List<Contact>) {
        if (contacts.isEmpty()) {
            binding.llContactsEmpty.visibility = View.VISIBLE
        } else binding.llContactsEmpty.visibility = View.GONE

    }

}