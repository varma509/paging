package com.eutor.core.di.module

import android.content.Context
import com.eutor.core.data.persistence.database.dao.ManagersDataBase
import com.eutor.core.data.persistence.network.datasource.SearchDataSource
import com.eutor.core.data.persistence.repositories.search.SearchRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class PersistenceModule {

    @Provides
    @Singleton
    fun providesRoom( @ApplicationContext appContext : Context)= ManagersDataBase.getDatabase(appContext)

    @Provides
    @Singleton
    fun provideSearchRepository(datasour:SearchDataSource)= SearchRepositoryImpl(datasour)


    @Provides
    @Singleton
    fun provideRemoteKeyDao(featuredatabase: ManagersDataBase)= featuredatabase.getRemoteKeysDao()



}