package com.fasoh.trialproject.di

import com.fasoh.trialproject.network.HerokuApi
import com.fasoh.trialproject.repository.TopLearnerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideTopLearnerRepository(api: HerokuApi): TopLearnerRepository {
        return TopLearnerRepository(api)
    }
}