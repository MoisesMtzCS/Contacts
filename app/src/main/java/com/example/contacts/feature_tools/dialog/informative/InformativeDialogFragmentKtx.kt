package com.example.contacts.feature_tools.dialog.informative

import androidx.fragment.app.FragmentActivity

/* Define the instances of informative dialog */
private val informativeDialogInstances: ArrayList<InformativeDialogFragment> = arrayListOf()

/** Show the informative dialog with [message]. */
fun FragmentActivity.showInformativeDialog(message: String? = null) {
    val informativeDialogFragment = InformativeDialogFragment.newInstance(message)
    informativeDialogFragment.show(supportFragmentManager, null)
    informativeDialogInstances.add(informativeDialogFragment)
}
