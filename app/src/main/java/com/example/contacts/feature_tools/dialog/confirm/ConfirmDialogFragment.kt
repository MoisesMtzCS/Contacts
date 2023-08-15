package com.example.contacts.feature_tools.dialog.confirm

import android.graphics.Typeface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import com.example.contacts.R
import com.example.contacts.databinding.FragmentConfirmDialogBinding
import com.example.contacts.databinding.FragmentInformativeDialogBinding
import com.example.contacts.feature_tools.dialog.FullWidthDialogFragment
import com.example.contacts.feature_tools.dialog.informative.InformativeDialogFragment
import com.google.android.material.button.MaterialButton

/**
 * COnfirm dialog definition.
 */
class ConfirmDialogFragment : FullWidthDialogFragment() {

    /* ViewBinding instance associated to the current screen */
    private val binding by lazy { FragmentConfirmDialogBinding.inflate(layoutInflater) }

    /* Informative message */
    private lateinit var message: String

    /* Actions to manage confirm flow */
    private lateinit var rightAction: ConfirmDialogAction

    /** Obtains the root view. */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root


    /** Called at moment that the root view is created. */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupActions()
        setupViews()
    }

    /** Setup the views information. */
    private fun setupViews() {
        binding.tvMessage.text = message
    }

    /** Setup the actions. */
    private fun setupActions() {
        binding.buttonCancel.setOnClickListener { dismiss() }
        binding.buttonConfirm.setOnClickListener(::onRightActionClickListener)
    }

    /** Setup confirm button. */
    private fun onRightActionClickListener(view: View) {
        rightAction.action?.invoke()
        dismiss()
    }


    /** Display the dialog, adding the fragment to the given FragmentManager. */
    override fun show(manager: FragmentManager, tag: String?) {
        try {
            val ft = manager.beginTransaction()
            ft.add(this, tag)
            ft.commitAllowingStateLoss()
        } catch (ignored: IllegalStateException) {
        }
    }

    companion object {

        fun newInstance(
            message: String,
            rightAction: ConfirmDialogAction,
        ) = ConfirmDialogFragment().apply {
            this.message = message
            this.rightAction = rightAction
        }

    }

}