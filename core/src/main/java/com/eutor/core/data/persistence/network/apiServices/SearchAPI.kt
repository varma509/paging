package com.eutor.core.data.persistence.network.apiServices

import com.eutor.core.data.models.search.SearchModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchAPI{

    @GET("search/repositories")
    suspend   fun getSearchResults(
        @Query("q") searchQuery: String,
        @Query("page") page: Int,
        @Query("per_page") per_page: Int
    ):Response<SearchModel>
}