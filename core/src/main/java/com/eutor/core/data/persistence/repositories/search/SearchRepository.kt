package com.eutor.core.data.persistence.repositories.search


import com.eutor.core.data.models.search.SearchModel


interface SearchRepository {
    suspend fun getSearchData( query:String, page: Int,pageSize:Int ): SearchModel
}