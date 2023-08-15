package com.example.contacts.feature.create_contact

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.contacts.R
import com.example.contacts.databinding.ActivityCreateContactBinding
import com.example.contacts.feature_tools.context.showLongToast
import com.example.contacts.feature_tools.dialog.informative.showInformativeDialog
import com.example.contacts.feature_tools.dialog.loading.dismissProgressDialog
import com.example.contacts.feature_tools.dialog.loading.showProgressDialog
import com.example.contacts.feature_tools.flow.launchAndRepeatOnLifecycle
import com.example.contacts.feature_tools.flow.observeFor
import com.example.contacts.shared.contact.domain.use_case.create_contact.CreateContactFailure
import com.example.contacts.shared.contact.domain.use_case.create_contact.CreateContactParams
import com.example.contacts.shared.contact.domain.use_case.create_contact.CreateContactResponse
import com.example.contacts.shared.contact.domain.use_case.create_contact.CreateContactStatus
import com.example.contacts.util.clean.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateContactActivity : AppCompatActivity() {

    /* Android resources */
    private val binding by lazy { ActivityCreateContactBinding.inflate(layoutInflater) }
    private val viewModel: CreateContactViewModel by viewModels()

    /*****************************************************************************************
     * LIFECYCLE
     ****************************************************************************************/

    /** Called at create the screen. */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupViews()
    }

    /** Setup the views data. */
    private fun setupViews() {
        setupActions()
    }

    /** Setup the actions. */
    private fun setupActions() {
        binding.buttonSave.setOnClickListener(::launchCreateContact)
    }

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
        when (status){
            is Status.Loading -> showProgressDialog()
            is Status.Failed -> manageCreateContactFailure(status.failure)
            is Status.Done -> manageGetContactsResponse(status.value)
        }
    }

    /** Manage create contact [failure] instance. */
    private fun manageCreateContactFailure(failure: CreateContactFailure) {
        dismissProgressDialog()
        when (failure){
            CreateContactFailure.InvalidName -> showInformativeDialog(getString(R.string.create_contact_failure_name))
            CreateContactFailure.InvalidPhone -> showInformativeDialog(getString(R.string.create_contact_failure_phone_number))
        }
    }

    /** Manage create contact [response] instance. */
    private fun manageGetContactsResponse(response: CreateContactResponse) {
        dismissProgressDialog()
        finish()
    }

}