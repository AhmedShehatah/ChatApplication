package com.example.chatapp.chatLog

interface ChatLogContract {
    interface View {
        fun onStarted()
        fun onSuccess(message: MessageLog)
        fun onFailure()
    }

    interface Presenter {
        fun listenToMessages(senderData: com.example.chatapp.firebase.UserData)
        fun sendMessages(message: String, receiverData: com.example.chatapp.firebase.UserData)
    }

    interface InterActor {
        fun onSucceed(message: MessageLog)
        fun onFailure(message: String)


    }
}