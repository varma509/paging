package com.eutor.core.data.persistence.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.eutor.core.data.models.search.SearchItem

@Dao
interface GitDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<SearchItem>)

    @Query(
        "SELECT * FROM search "
    )
    fun pagingSource(): PagingSource<Int, SearchItem>

    @Query("DELETE FROM search")
    suspend fun clearAll()
}