package com.fasoh.trialproject.flow.submission

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fasoh.trialproject.AutoDisposable
import com.fasoh.trialproject.repository.SubmissionRepository
import com.fasoh.trialproject.repository.SubmissionStatus

class SubmissionViewModel @ViewModelInject constructor(
    private val submissionRepository: SubmissionRepository
): ViewModel(){

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _status = MutableLiveData<SubmissionStatus>()
    val status: LiveData<SubmissionStatus> = _status

    fun submit(firstName: String, lastName: String, email: String, link: String, autoDisposable: AutoDisposable) {
        submissionRepository.submit(firstName, lastName, email, link, _status, _error, autoDisposable)
    }
}