package com.fasoh.trialproject.flow.submission

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import com.fasoh.trialproject.*
import com.fasoh.trialproject.repository.SubmissionStatus
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_submission.*

@AndroidEntryPoint
class SubmissionActivity : AppCompatActivity() {
    private val viewModel by viewModels<SubmissionViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_submission)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setDisplayShowHomeEnabled(true);

        submit.setOnClickListener {
            validateViews()
            it.isEnabled = false
        }

        viewModel.error.observe(this) {
            getDialog(it, R.drawable.ic_baseline_error_24, R.layout.layout_status_dialog).show()
        }

        viewModel.status.observe(this) {
            val dialog: Dialog = when (it) {
                SubmissionStatus.SUCCESS -> getDialog(
                    getString(R.string.submission_success),
                    R.drawable.ic_done,
                    R.layout.layout_status_dialog
                )
                else -> getDialog(
                    getString(R.string.unknown_error),
                    R.drawable.ic_baseline_error_24,
                    R.layout.layout_status_dialog
                )
            }
            dialog.show()
        }
    }

    private fun validateViews() {
        var hasError = false
        listOf(firstName, lastName, emailAddress, link).forEach {
            if (it.validate())
                hasError = true
        }

        if (hasError) {
            submit.isEnabled = true
            return
        }
        progressBar.toggleVisibility()
        viewModel.submit(
            firstName.getInPut(),
            lastName.getInPut(),
            emailAddress.getInPut(),
            link.getInPut(),
            AutoDisposable().bindTo(lifecycle)
        )
    }
}
