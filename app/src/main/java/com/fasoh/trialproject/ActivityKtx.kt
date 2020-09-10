package com.fasoh.trialproject

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat

fun Context.getDialog(
    message: String,
    iconId: Int? = null,
    layoutRes: Int,
    listener: DialogButtonEvents? = null
): Dialog {
    val dialog = Dialog(this, R.style.DialogStyle)
    val inflater = LayoutInflater.from(this)
    val view = inflater.inflate(layoutRes, null, false)

    val displayMessage = view.findViewById<TextView>(R.id.message)
    displayMessage.text = message

    view.findViewById<Button>(R.id.positiveButton)?.let { button ->
        button.setOnClickListener {
            listener?.onButtonClicked(it.id)
        }
    }

    view.findViewById<Button>(R.id.close)?.let { button ->
        button.setOnClickListener {
            listener?.onButtonClicked(it.id)
        }
    }

    iconId?.let {
        view.findViewById<ImageView>(R.id.icon)
            .setImageDrawable(ContextCompat.getDrawable(this, iconId))

    }

    dialog.setContentView(view)
    dialog.setCancelable(false)
    return dialog
}

interface DialogButtonEvents {
    fun onButtonClicked(id: Int)
}