package com.eutor.core.data.persistence.database.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.eutor.core.data.models.RemoteKeys
import com.eutor.core.data.models.search.SearchItem
import com.eutor.core.data.persistence.converter.StringListConverter


@Database(entities = [(RemoteKeys::class),(SearchItem::class)],version = 1,exportSchema = false)
@TypeConverters(StringListConverter::class)
abstract class ManagersDataBase: RoomDatabase() {

    abstract fun getSearch() : GitDao
    abstract fun getRemoteKeysDao() : RemoteKeysDao

    companion object {
        @Volatile private var instance: ManagersDataBase? = null

        fun getDatabase(context: Context): ManagersDataBase =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, ManagersDataBase::class.java, "database-managers")
                .fallbackToDestructiveMigration()
                .build()
    }


}