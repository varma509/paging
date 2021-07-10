package com.storemate.storemate.ui.search

import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.eutor.core.base.BaseViewModel
import com.eutor.core.data.models.search.SearchItem
import com.eutor.core.data.persistence.database.dao.ManagersDataBase
import com.eutor.core.data.persistence.network.paging.remotemediator.SearchRemoteMediator
import com.eutor.core.data.persistence.repositories.search.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class SearchViewModel  @Inject constructor(
    private val searchRepository: SearchRepository,
    private val appDataBase : ManagersDataBase
) : BaseViewModel() {

        @ExperimentalPagingApi
        fun getSearch(query:String) : Flow<PagingData<SearchItem>>{
      return Pager(
          PagingConfig(pageSize = 10,prefetchDistance = 5),
          remoteMediator = SearchRemoteMediator(query,searchRepository, appDataBase),
      ) {
          appDataBase.getSearch().pagingSource()
      }.flow.cachedIn(viewModelScope)
  }




}