package com.example.chatapp.main

import com.example.chatapp.firebase.UserData

interface MainContract {
    interface View {
        fun userData(): UserData
    }

    interface Presenter {
        fun getUserData(): UserData
    }

}