package com.example.chatapp.login

import com.example.chatapp.firebase.Authentication
import com.example.chatapp.register.RegisterContract

class LoginPresenter(private val view: LoginContract.View) : LoginContract.Presenter,
    RegisterContract.Contract {
    override fun signIn(email: String, password: String) {
        view.onStarted()
        Authentication(this).signIn(email, password)
    }

    override fun onSuccess() {
        view.onSuccess()
    }

    override fun onFailure(message: String) {
        view.onFailure(message)
    }
}