package com.example.contacts.feature_tools.context

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

fun Context.showLongToast(message: String) =
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()

fun Context.showLongToast(@StringRes message: Int) =
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
