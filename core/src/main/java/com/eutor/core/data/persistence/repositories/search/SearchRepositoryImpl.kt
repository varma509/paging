package com.eutor.core.data.persistence.repositories.search
import com.eutor.core.data.models.search.SearchModel
import com.eutor.core.data.persistence.network.datasource.SearchDataSource


class SearchRepositoryImpl (private val rs: SearchDataSource):SearchRepository{
    override suspend fun getSearchData(query: String, page: Int, pageSize: Int): SearchModel{
        return rs.fetchRepositories(query, page, pageSize)
    }


}
