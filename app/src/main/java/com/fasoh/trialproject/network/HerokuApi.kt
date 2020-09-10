package com.fasoh.trialproject.network

import com.fasoh.trialproject.model.TopHourLearner
import com.fasoh.trialproject.model.TopSkillIqLearner
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface HerokuApi {
    @GET("api/hours")
    fun getTopHourLearners(): Single<List<TopHourLearner>>

    @GET("api/skilliq")
    fun getTopSkillIqLearners(): Single<List<TopSkillIqLearner>>
}