package com.eutor.core.data.persistence.network.apiServices

import com.eutor.core.data.models.search.SearchModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface SearchAPI{
   @Headers("Authorization: token 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")
    @GET("search/repositories")
    suspend   fun getSearchResults(
        @Query("q") searchQuery: String,
        @Query("page") page: Int,
        @Query("per_page") per_page: Int
    ):Response<SearchModel>
}