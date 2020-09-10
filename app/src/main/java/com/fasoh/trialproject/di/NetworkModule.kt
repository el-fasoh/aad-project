package com.fasoh.trialproject.di

import com.carepay.common.di.GoogleFormRetrofitRetrofit
import com.carepay.common.di.HerokuRetrofit
import com.fasoh.trialproject.BuildConfig
import com.fasoh.trialproject.network.GoogleApi
import com.fasoh.trialproject.network.HerokuApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    internal fun provideGson(): Gson {
        return GsonBuilder().setLenient().create()
    }

    @Provides
    @Singleton
    internal fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

    @Provides
    @Singleton
    internal fun provideOkhttpClientToken(
        logging: HttpLoggingInterceptor
    ): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.connectTimeout(BuildConfig.HOST_CONNECT_TIMEOUT, TimeUnit.SECONDS)
        httpClient.readTimeout(BuildConfig.HOST_READ_TIMEOUT, TimeUnit.SECONDS)
        httpClient.addInterceptor(logging)
        return httpClient.build()
    }


    @Provides
    @GoogleFormRetrofitRetrofit
    internal fun provideGoogleFormRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .baseUrl(BuildConfig.GOOGLE_FORM_BASE_URL)
            .client(okHttpClient)
            .build()
    }

    @Provides
    internal fun provideGoogleFormApi(@GoogleFormRetrofitRetrofit retrofit: Retrofit): GoogleApi {
        return retrofit.create(GoogleApi::class.java)
    }

    @Provides
    @HerokuRetrofit
    internal fun provideHerukuFormRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .baseUrl(BuildConfig.HEROKU_BASE_URL)
            .client(okHttpClient)
            .build()
    }

    @Provides
    internal fun provideHerokuFormApi(@HerokuRetrofit retrofit: Retrofit): HerokuApi {
        return retrofit.create(HerokuApi::class.java)
    }

}