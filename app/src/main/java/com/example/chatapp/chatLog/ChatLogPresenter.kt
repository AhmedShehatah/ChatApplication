package com.example.chatapp.chatLog

import com.example.chatapp.firebase.MessagesData
import com.example.chatapp.firebase.UserData

class ChatLogPresenter(private val view: ChatLogContract.View) : ChatLogContract.Presenter,
    ChatLogContract.InterActor {
    private val messageData = MessagesData(this)

    override fun listenToMessages(senderData: UserData) {
        messageData.onSent(senderData)
    }


    override fun onFailure(message: String) {
        TODO("Not yet implemented")
    }

    override fun sendMessages(message: String, receiverData: UserData) {
        messageData.sendMessage(message, receiverData)
    }

    override fun onSucceed(message: MessageLog) {
        view.onSuccess(message)
    }


}