package com.eutor.core.data.models.search
import com.google.gson.annotations.SerializedName

data class SearchModel(
    @SerializedName("items")
    val searchItems: List<SearchItem> = emptyList(),
    @SerializedName("total_count")
    val totalCount: Int=0,
    val nextPage: Int? = null
)