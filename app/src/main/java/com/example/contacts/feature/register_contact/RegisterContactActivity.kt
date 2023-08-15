package com.example.contacts.feature.register_contact

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.contacts.R
import com.example.contacts.databinding.ActivityRegisterContactBinding
import com.example.contacts.feature_tools.dialog.informative.showInformativeDialog
import com.example.contacts.feature_tools.dialog.loading.dismissProgressDialog
import com.example.contacts.feature_tools.dialog.loading.showProgressDialog
import com.example.contacts.feature_tools.flow.launchAndRepeatOnLifecycle
import com.example.contacts.feature_tools.flow.observeFor
import com.example.contacts.shared.contact.domain.entity.Contact
import com.example.contacts.shared.contact.domain.use_case.create_contact.CreateContactFailure
import com.example.contacts.shared.contact.domain.use_case.create_contact.CreateContactParams
import com.example.contacts.shared.contact.domain.use_case.create_contact.CreateContactResponse
import com.example.contacts.shared.contact.domain.use_case.create_contact.CreateContactStatus
import com.example.contacts.shared.contact.domain.use_case.edit_contact.EditContactFailure
import com.example.contacts.shared.contact.domain.use_case.edit_contact.EditContactParams
import com.example.contacts.shared.contact.domain.use_case.edit_contact.EditContactResponse
import com.example.contacts.shared.contact.domain.use_case.edit_contact.EditContactStatus
import com.example.contacts.util.clean.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterContactActivity : AppCompatActivity() {

    /* Android resources */
    private val binding by lazy { ActivityRegisterContactBinding.inflate(layoutInflater) }
    private val viewModel: RegisterContactViewModel by viewModels()
    private val contactID by lazy { intent.getStringExtra(CONTACT_ID_KEY) as String }
    private val editMode by lazy { intent?.getBooleanExtra(EDIT_MODE_KEY, false) as Boolean }

    /*****************************************************************************************
     * LIFECYCLE
     ****************************************************************************************/

    /** Called at create the screen. */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupViews()
    }

    /** Setup the views by mode. */
    private fun setupViews() {
        if (editMode) {
            setupViewsForEditMode()
            binding.buttonSave.setOnClickListener(::launchEditContact)
        } else binding.buttonSave.setOnClickListener(::launchCreateContact)
    }

    /*********************************
     * CREATION MODE
     *********************************/

    /*****************************************************************************************
     * USE CASE - CREATE CONTACT
     ****************************************************************************************/

    /** Launch the flow to create contact. */
    private fun launchCreateContact(view: View) {
        launchAndRepeatOnLifecycle {
            val params = CreateContactParams(
                name = binding.etNameUser.text.toString().trim(),
                phone = binding.etNumber.text.toString().trim(),
                email = binding.etEmail.text.toString().trim(),
            )
            val response = viewModel.launchCreateContact(params)
            observeFor(response, ::createContactCollector)
        }
    }

    /** Collect the status for create contact. */
    private fun createContactCollector(status: CreateContactStatus) {
        when (status) {
            is Status.Loading -> showProgressDialog()
            is Status.Failed -> manageCreateContactFailure(status.failure)
            is Status.Done -> manageCreateContactsResponse(status.value)
        }
    }

    /** Manage create contact [failure] instance. */
    private fun manageCreateContactFailure(failure: CreateContactFailure) {
        dismissProgressDialog()
        when (failure) {
            CreateContactFailure.InvalidName -> showInformativeDialog(getString(R.string.create_contact_failure_name))
            CreateContactFailure.InvalidPhone -> showInformativeDialog(getString(R.string.create_contact_failure_phone_number))
            CreateContactFailure.InvalidEmail -> showInformativeDialog(getString(R.string.create_contact_failure_email))
        }
    }

    /** Manage create contact [response] instance. */
    private fun manageCreateContactsResponse(response: CreateContactResponse) {
        dismissProgressDialog()
        finish()
    }

    /*********************************
     * EDIT MODE
     *********************************/

    /** Setup views for edit mode. */
    private fun setupViewsForEditMode() {
        val contact = viewModel.getContact(contactID)
        binding.etNameUser.setText(contact.userName)
        binding.etNumber.setText(contact.phone)
        binding.etEmail.setText(contact.email)
    }

    /*****************************************************************************************
     * USE CASE - EDIT CONTACT
     ****************************************************************************************/

    /** Launch the flow to edit contact. */
    private fun launchEditContact(view: View) {
        launchAndRepeatOnLifecycle {
            val contact = Contact(
                contactID = contactID,
                userName = binding.etNameUser.text.toString().trim(),
                phone = binding.etNumber.text.toString().trim(),
                email = binding.etEmail.text.toString().trim(),
            )
            val params = EditContactParams(contact)
            val response = viewModel.launchEditContact(params)
            observeFor(response, ::editContactCollector)
        }
    }

    /** Collect the status for edit contact. */
    private fun editContactCollector(status: EditContactStatus) {
        when (status) {
            is Status.Loading -> showProgressDialog()
            is Status.Failed -> manageEditContactFailure(status.failure)
            is Status.Done -> manageEditContactsResponse(status.value)
        }
    }

    /** Manage edit contact [failure] instance. */
    private fun manageEditContactFailure(failure: EditContactFailure) {
        dismissProgressDialog()
        when (failure) {
            EditContactFailure.InvalidName -> showInformativeDialog(getString(R.string.create_contact_failure_name))
            EditContactFailure.InvalidPhone -> showInformativeDialog(getString(R.string.create_contact_failure_phone_number))
            EditContactFailure.InvalidEmail -> showInformativeDialog(getString(R.string.create_contact_failure_email))
        }
    }

    /** Manage edit contact [response] instance. */
    private fun manageEditContactsResponse(response: EditContactResponse) {
        dismissProgressDialog()
        finish()
    }

    companion object {

        /* Key value for edit model arg. */
        const val EDIT_MODE_KEY: String = "EDIT_MODE_KEY"

        /* Key value for contact id arg. */
        const val CONTACT_ID_KEY: String = "CONTACT_ID_KEY"

    }

}