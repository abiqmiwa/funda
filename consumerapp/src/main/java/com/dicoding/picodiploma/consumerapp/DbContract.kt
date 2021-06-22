package com.dicoding.picodiploma.consumerapp

import android.net.Uri
import android.provider.BaseColumns

object DbContract {
    const val AUTHORITY = "com.dicoding.picodiploma.fixfinalsub3"
    const val SCHEME = "content"

    class FavoriteColumns : BaseColumns {
        companion object {
            const val TABLE_NAME = "favorite_user"
            const val _ID = "_id"
            const val USERNAME = "username"
            const val AVATAR = "AVATAR"

            val CONTENT_URI: Uri = Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_NAME)
                .build()
        }
    }
}