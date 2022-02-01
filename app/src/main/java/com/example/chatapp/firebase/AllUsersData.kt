package com.example.chatapp.firebase

import com.example.chatapp.newMessages.NewMessagesContract
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AllUsersData(private val contract: NewMessagesContract.Contract) {
    fun fetch() {
        val data = ArrayList<UserData>()
        val ref = FirebaseDatabase.getInstance().getReference("/users/")
        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEach {
                    println(it)
                    val user = it.getValue(UserData::class.java)!!
                    data.add(user)
                }
                contract.onSuccess(data)
            }

            override fun onCancelled(error: DatabaseError) {
                contract.onFailure(error.message)
            }

        })
    }
}