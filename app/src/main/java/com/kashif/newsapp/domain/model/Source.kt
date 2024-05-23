package com.kashif.newsapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


/**
 * Created by Mohammad Kashif Ansari on 19,May,2024
 */
@Parcelize
data class Source(
    val id: String,
    val name: String
): Parcelable
