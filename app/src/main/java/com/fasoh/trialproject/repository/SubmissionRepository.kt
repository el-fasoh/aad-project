package com.fasoh.trialproject.repository

import androidx.lifecycle.MutableLiveData
import com.fasoh.trialproject.AutoDisposable
import com.fasoh.trialproject.addTo
import com.fasoh.trialproject.network.GoogleApi
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class SubmissionRepository @Inject constructor(private val googleApi: GoogleApi) {

    fun submit(
        firstName: String,
        lastName: String,
        email: String,
        link: String,
        submissionStatus: MutableLiveData<SubmissionStatus>,
        error: MutableLiveData<String>,
        autoDisposable: AutoDisposable
    ){
        googleApi.submit(email,firstName,lastName,link)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                submissionStatus.postValue(SubmissionStatus.SUCCESS)
            },{
                submissionStatus.postValue(SubmissionStatus.FAILED)
                error.postValue(it.message)
            }).addTo(autoDisposable)
    }
}

enum class SubmissionStatus {
    SUCCESS, FAILED
}