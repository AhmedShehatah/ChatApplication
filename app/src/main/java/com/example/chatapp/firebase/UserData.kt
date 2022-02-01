package com.example.chatapp.firebase

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class UserData(
    val email: String,
    val uid: String,
    val url: String,
    val userName: String
) : Parcelable {
    constructor() : this("", "", "", "")
}
