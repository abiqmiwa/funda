package com.dicoding.picodiploma.mysubs2

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ModelErs(
    var username: String? = null,
    var avatar: String? = null,
) : Parcelable