package com.dicoding.picodiploma.consumerapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ModelFav(
    var username: String? = null,
    var avatar: String? = null,
) : Parcelable