package com.example.chatapp.main

import com.example.chatapp.firebase.CurrentUser
import com.example.chatapp.firebase.UserData

class MainPresenter : MainContract.Presenter {
    override fun getUserData(): UserData = CurrentUser().getUserData()

}