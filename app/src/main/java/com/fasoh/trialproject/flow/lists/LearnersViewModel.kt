package com.fasoh.trialproject.flow.lists

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fasoh.trialproject.AutoDisposable
import com.fasoh.trialproject.model.TopHourLearner
import com.fasoh.trialproject.model.TopSkillIqLearner
import com.fasoh.trialproject.repository.TopLearnerRepository

class LearnersViewModel @ViewModelInject constructor(private val learnerRepo: TopLearnerRepository) : ViewModel() {
    companion object {
        const val IQ = 1
        const val HOURS = 2
    }

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _hourLearners = MutableLiveData<List<TopHourLearner>>()
    val hourLearners: LiveData<List<TopHourLearner>> = _hourLearners

    private val _iqLearners=  MutableLiveData<List<TopSkillIqLearner>>()
    val iqLearners: LiveData<List<TopSkillIqLearner>> = _iqLearners

    fun setIndex(index: Int, autoDisposable: AutoDisposable) = when (index) {
        HOURS -> learnerRepo.getTopHourLearners(autoDisposable,_hourLearners, _error)
        else -> learnerRepo.getTopIqLearners(autoDisposable, _iqLearners, _error)
    }
}