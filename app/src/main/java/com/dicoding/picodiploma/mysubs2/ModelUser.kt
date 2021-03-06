package com.dicoding.picodiploma.mysubs2

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ModelUser(
    var username: String? = null,
    var avatar: String? = null,
    var name: String? = null,
    var company: String? = null,
    var location: String? = null,
    var public_repos: Int = 0,
    var followers: Int = 0,
    var following: Int = 0,
    var usernameFollowers: String? = null,
    var usernameFollowing: String? = null,
    var avatarFollowers: String? = null,
    var avatarFollowing: String? = null,
)   :   Parcelable
