package com.kashif.newsapp.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

/**
 * Created by Mohammad Kashif Ansari on 19,May,2024
 */
@Parcelize
@Entity()
data class Article(
    val author: String?,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,  //room want every element is in primitive dfatatypes(Int,String,etc)
                        // so you have top convert Source object to primitive data types so we are using TypeConverter
                        // class to convert this into string
    val title: String,
    @PrimaryKey
    val url: String,
    val urlToImage: String
): Parcelable
