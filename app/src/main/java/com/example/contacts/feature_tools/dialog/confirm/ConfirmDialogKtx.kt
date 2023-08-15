package com.example.contacts.feature_tools.dialog.confirm

import androidx.fragment.app.FragmentActivity

private var confirmDialogFragment: ConfirmDialogFragment? = null

private fun reset() {
    confirmDialogFragment?.dismiss()
    confirmDialogFragment = null
}

/** Show the confirm dialog with [message]. */
fun FragmentActivity.showConfirmDialog(
    message: String,
    rightAction: ConfirmDialogAction,
) {
    reset()
    confirmDialogFragment = ConfirmDialogFragment.newInstance(message, rightAction)
    confirmDialogFragment?.show(supportFragmentManager, null)
}
