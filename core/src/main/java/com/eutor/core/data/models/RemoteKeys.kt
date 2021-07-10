package com.eutor.core.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class RemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val movieId: Int,
    val nextKey: Int?,
    val prevKey: Int?
)