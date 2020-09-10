package com.fasoh.trialproject

import android.view.View
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


fun View.toggleVisibility() {
    when (this.visibility) {
        View.GONE -> this.visibility = View.VISIBLE
        View.INVISIBLE -> this.visibility = View.VISIBLE
        View.VISIBLE -> this.visibility = View.GONE
    }
}

fun TextInputEditText.getInPut(): String {
    return this.text.toString()
}

fun TextInputEditText.getParentView(): TextInputLayout {
    return this.parent.parent as TextInputLayout
}

fun TextInputLayout.clearError() {
    this.error = null
    this.isErrorEnabled = false
}

fun TextInputEditText.validate(): Boolean {
    val input = this.text
    val parent = this.getParentView()
    return when {
        input.isNullOrBlank() -> {
            parent.error = this.context.getString(R.string.cannot_be_empty)
            true
        }
        else -> {
            parent.clearError()
            false
        }
    }
}