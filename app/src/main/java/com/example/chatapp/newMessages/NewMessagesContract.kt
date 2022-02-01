package com.example.chatapp.newMessages

import com.example.chatapp.firebase.UserData

interface NewMessagesContract {
    interface View {
        fun onStarted()
        fun onSuccess(list: ArrayList<UserData>)
        fun onFailure(Message: String)
    }

    interface Presenter {
        fun fetchUsers()
    }

    interface Contract {
        fun onSuccess(list: ArrayList<UserData>)
        fun onFailure(message: String)
    }

}