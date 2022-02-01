package com.example.chatapp.firebase

import com.example.chatapp.chatLog.ChatLogContract
import com.example.chatapp.chatLog.MessageLog
import com.example.chatapp.utlis.getUID
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase


class MessagesData(private val contract: ChatLogContract.InterActor) {


    fun sendMessage(message: String, receiverData: UserData) {
        val ref = FirebaseDatabase.getInstance()
            .getReference("/user-messages/${getUID()}/${receiverData.uid}").push()
        val toRef = FirebaseDatabase.getInstance()
            .getReference("/user-messages/${receiverData.uid}/${getUID()}").push()
        val messageLog = MessageLog(
            ref.key!!,
            message,
            getUID(),
            receiverData.uid,
            "${System.currentTimeMillis()}",
            receiverData.userName,
            receiverData.url
        )
        ref.setValue(messageLog)
        toRef.setValue(messageLog)
        val lastRef = FirebaseDatabase.getInstance()
            .getReference("/last-messages/${getUID()}/${receiverData.uid}")
        lastRef.setValue(messageLog)
        val lastToRef = FirebaseDatabase.getInstance()
            .getReference("/last-messages/${receiverData.uid}/${getUID()}")
        lastToRef.setValue(messageLog)

    }

    fun onSent(userData: UserData) {
        val ref = FirebaseDatabase.getInstance()
            .getReference("/user-messages/${getUID()}/${userData.uid}")
        ref.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val message = snapshot.getValue(MessageLog::class.java)!!
                contract.onSucceed(message)

            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                TODO("Not yet implemented")
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

    }
}