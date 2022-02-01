package com.example.chatapp.currentMessages

import com.example.chatapp.chatLog.MessageLog

interface CurrentMessagesContract {
    interface View {
        fun onAddedMessage(message: MessageLog)
    }

    interface Presenter {
        fun listenLatestMessages()
    }

    interface Contract {
        fun onAdded(message: MessageLog)
    }
}