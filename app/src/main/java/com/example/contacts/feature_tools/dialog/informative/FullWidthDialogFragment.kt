package com.example.contacts.feature_tools.dialog.informative

import android.view.WindowManager
import androidx.fragment.app.DialogFragment

abstract class FullWidthDialogFragment : DialogFragment() {

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

}