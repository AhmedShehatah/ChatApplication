package com.example.chatapp.register

import android.net.Uri
import com.example.chatapp.firebase.Authentication

class RegisterPresenter(private val view: RegisterContract.View) :
    RegisterContract.Presenter, RegisterContract.Contract {
    override fun signUp(userName: String, email: String, password: String, url: Uri?) {
        view.onStarted()
        Authentication(this).signUp(userName, email, password, url)
    }

    override fun onSuccess() {
        view.onSuccess()
    }

    override fun onFailure(message: String) {
        view.onFailure(message)
    }

}