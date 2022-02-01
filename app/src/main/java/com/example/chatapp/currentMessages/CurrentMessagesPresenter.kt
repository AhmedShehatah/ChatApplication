package com.example.chatapp.currentMessages

import com.example.chatapp.chatLog.MessageLog
import com.example.chatapp.firebase.LatestMessagesData

class CurrentMessagesPresenter(private val view: CurrentMessagesContract.View) :
    CurrentMessagesContract.Presenter, CurrentMessagesContract.Contract {
    private val messagesData = LatestMessagesData(this)
    override fun listenLatestMessages() {
        messagesData.getLatestMessages()
    }

    override fun onAdded(message: MessageLog) {
        view.onAddedMessage(message)
    }
}