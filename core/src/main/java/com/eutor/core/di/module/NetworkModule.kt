package com.eutor.core.di.module

import com.eutor.core.BuildConfig
import com.eutor.core.ConstantValues
import com.eutor.core.data.persistence.network.apiServices.SearchAPI
import com.eutor.core.data.persistence.network.datasource.SearchDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideBaseUrl() = ConstantValues.BASE_URL

    @Provides
    @Singleton
    fun provideOkHttpClient():OkHttpClient {/*if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .build()
    } else {*/
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
       return  OkHttpClient
            .Builder()
           .addInterceptor(loggingInterceptor)
            .build()
    }


    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        BASE_URL: String
    ): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideSearchApi(retrofit: Retrofit): SearchAPI =
        retrofit.create(SearchAPI::class.java)

    @Provides
    @Singleton
    fun provideSearchDataSource(api: SearchAPI) = SearchDataSource(api)


}