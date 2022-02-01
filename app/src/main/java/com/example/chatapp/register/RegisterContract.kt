package com.example.chatapp.register

import android.net.Uri

interface RegisterContract {
    interface View {
        fun onStarted()
        fun onSuccess()
        fun onFailure(message: String)

    }

    interface Presenter {
        fun signUp(userName: String, email: String, password: String, url: Uri?)
    }

    interface Contract {
        fun onSuccess()
        fun onFailure(message: String)
    }

}