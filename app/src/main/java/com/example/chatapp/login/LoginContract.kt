package com.example.chatapp.login

interface LoginContract {
    interface View {
        fun onStarted()
        fun onSuccess()
        fun onFailure(message: String)
    }

    interface Presenter {
        fun signIn(email: String, password: String)
    }
}