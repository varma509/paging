package com.eutor.core.data.persistence.network.datasource


import com.eutor.core.data.models.search.SearchModel
import com.eutor.core.data.persistence.network.BaseService
import com.eutor.core.data.persistence.network.Result
import com.eutor.core.data.persistence.network.apiServices.SearchAPI

class SearchDataSource(private val service: SearchAPI): BaseService() {

    suspend fun fetchRepositories( query:String, page: Int,pageSize:Int) : SearchModel {
        return   when(val result =   createCall { service.getSearchResults(query,page,pageSize) }){
            is Result.Success -> result.data
            is Result.Error -> throw result.error
        }
    }

}