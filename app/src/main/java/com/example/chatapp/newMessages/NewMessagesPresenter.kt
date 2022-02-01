package com.example.chatapp.newMessages

import com.example.chatapp.firebase.AllUsersData
import com.example.chatapp.firebase.UserData

class NewMessagesPresenter(private val view: NewMessagesContract.View) :
    NewMessagesContract.Contract,
    NewMessagesContract.Presenter {
    override fun fetchUsers() {
        view.onStarted()
        AllUsersData(this).fetch()
    }

    override fun onSuccess(list: ArrayList<UserData>) {
        view.onSuccess(list)
    }

    override fun onFailure(message: String) {
        view.onFailure(message)
    }
}