package com.example.chatapp.firebase

import com.example.chatapp.chatLog.MessageLog
import com.example.chatapp.currentMessages.CurrentMessagesContract
import com.example.chatapp.utlis.getUID
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase

class LatestMessagesData(private val contract: CurrentMessagesContract.Contract) {
    fun getLatestMessages() {
        val ref = FirebaseDatabase.getInstance()
            .getReference("/last-messages/${getUID()}")
        ref.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val message = snapshot.getValue(MessageLog::class.java)!!
                contract.onAdded(message)
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                val message = snapshot.getValue(MessageLog::class.java)!!
                contract.onAdded(message)
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