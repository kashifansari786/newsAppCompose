package com.kashif.newsapp.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.kashif.newsapp.domain.model.Source

/**
 * Created by Mohammad Kashif Ansari on 22,May,2024
 */

@ProvidedTypeConverter
class NewsTypeConvertor {

    @TypeConverter
    fun sourceToString(source: Source):String{
        return "${source.id},${source.name}"
    }

    @TypeConverter
    fun stringToSource(source: String):Source{
        return source.split(",").let {sourceArray->
            Source(sourceArray[0],sourceArray[1])
        }
    }
}