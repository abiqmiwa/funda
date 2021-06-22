package com.dicoding.picodiploma.mysubs2

import android.database.Cursor

object HelperMap {
    fun mapCursorToArrayList(favoriteCursor: Cursor?) : ArrayList<ModelFav>{
        val favoriteList = ArrayList<ModelFav>()

        favoriteCursor?.apply {
            while (moveToNext()) {
                val username = getString(getColumnIndexOrThrow(DbContract.FavoriteColumns.USERNAME))
                val avatar = getString(getColumnIndexOrThrow(DbContract.FavoriteColumns.AVATAR))
                favoriteList.add(ModelFav(username, avatar))
            }
        }
        return favoriteList
    }
}