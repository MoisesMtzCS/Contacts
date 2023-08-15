package com.example.contacts.feature.contact_details

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.contacts.R
import com.example.contacts.databinding.ActivityContactDetailsBinding
import com.example.contacts.feature_tools.context.showLongToast
import com.example.contacts.feature_tools.dialog.confirm.ConfirmDialogAction
import com.example.contacts.feature_tools.dialog.confirm.showConfirmDialog
import com.example.contacts.feature_tools.flow.launchAndRepeatOnLifecycle
import com.example.contacts.feature_tools.flow.observeFor
import com.example.contacts.shared.contact.domain.use_case.delete_contact.DeleteContactParams
import com.example.contacts.shared.contact.domain.use_case.delete_contact.DeleteContactResponse
import com.example.contacts.shared.contact.domain.use_case.delete_contact.DeleteContactStatus
import com.example.contacts.util.clean.InfallibleStatus
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactDetailsActivity : AppCompatActivity() {

    /* Android resources */
    private val binding by lazy { ActivityContactDetailsBinding.inflate(layoutInflater) }
    private val viewModel: ContactDetailsViewModel by viewModels()
    private val contactID by lazy { intent.getStringExtra(CONTACT_ID_KEY) as String }

    /*****************************************************************************************
     * LIFECYCLE
     ****************************************************************************************/

    /** Called at create the screen. */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupViews()
        setupActions()
    }

    /** Setup the actions. */
    private fun setupActions() {
        binding.buttonDelete.setOnClickListener(::deleteContactDialog)
        binding.buttonEdit.setOnClickListener { showLongToast("Edit") }
    }

    /** Setup views. */
    private fun setupViews() {
        val contact = viewModel.getContact(contactID)
        binding.tvName.text = contact.userName
        binding.tvNumber.text = contact.phone
        binding.tvEmail.text = contact.email
    }

    /*****************************************************************************************
     * USE CASE - DELETE CONTACT
     ****************************************************************************************/

    /** Launch the flow to delete contact. */
    private fun launchDeleteContact() {
        launchAndRepeatOnLifecycle {
            val contactID by lazy { intent.getStringExtra(CONTACT_ID_KEY) as String }
            val contact = viewModel.getContact(contactID)
            val params = DeleteContactParams(contact)
            val response = viewModel.launchDeleteContact(params)
            observeFor(response, ::deleteContactCollector)
        }
    }

    /** Collect the status for delete contact. */
    private fun deleteContactCollector(status: DeleteContactStatus) {
        if (status is InfallibleStatus.Done) {
            manageDeleteContactResponse(status.value)
        }
    }

    /** Manage delete contact [response] instance. */
    private fun manageDeleteContactResponse(response: DeleteContactResponse) {
        finish()
    }

    /** Show cancel downloading region dialog. */
    private fun deleteContactDialog(view: View) {
        val message: String = getString(R.string.contact_details_delete_message)
        showConfirmDialog(
            message = message,
            rightAction = ConfirmDialogAction(
                action = { launchDeleteContact() }
            )
        )
    }

    companion object {

        /* Key value for contact id arg. */
        const val CONTACT_ID_KEY: String = "CONTACT_ID_KEY"

    }

}