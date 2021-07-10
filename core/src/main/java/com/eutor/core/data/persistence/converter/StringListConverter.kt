package com.eutor.core.data.persistence.converter

import androidx.room.TypeConverter
import com.eutor.core.data.models.search.Owner
import com.google.gson.Gson

class StringListConverter {
    @TypeConverter
    fun  appToString(app: Owner): String = Gson().toJson(app)

    @TypeConverter
     fun stringToApp(string: String):Owner= Gson().fromJson(string, Owner::class.java)

}