package com.example.contacts.feature_tools.dialog.confirm

import androidx.annotation.StringRes

data class ConfirmDialogAction(
    val action: (() -> Unit)? = null,
)