package com.fasoh.trialproject.repository

import androidx.lifecycle.MutableLiveData
import com.fasoh.trialproject.AutoDisposable
import com.fasoh.trialproject.addTo
import com.fasoh.trialproject.model.TopHourLearner
import com.fasoh.trialproject.model.TopSkillIqLearner
import com.fasoh.trialproject.network.HerokuApi
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class TopLearnerRepository @Inject constructor(private val api: HerokuApi) {

    fun getTopIqLearners(
        autoDisposable: AutoDisposable,
        learners: MutableLiveData<List<TopSkillIqLearner>>,
        error: MutableLiveData<String>
    ) {
        api.getTopSkillIqLearners()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                learners.postValue(it)
            }, {
                error.postValue(it.message)
            }).addTo(autoDisposable)
    }

    fun getTopHourLearners(
        autoDisposable: AutoDisposable,
        learners: MutableLiveData<List<TopHourLearner>>,
        error: MutableLiveData<String>
    ) {
        api.getTopHourLearners()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                learners.postValue(it)
            }, {
                error.postValue(it.message)
            }).addTo(autoDisposable)
    }
}