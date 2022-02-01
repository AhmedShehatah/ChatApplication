package com.example.chatapp.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase

class CurrentUser {
    private lateinit var auth: FirebaseAuth
    fun getUserData(): UserData {
        auth = Firebase.auth
        val uid = auth.currentUser?.uid
        var currentUser = UserData()
        val ref = FirebaseDatabase.getInstance().getReference("/users")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEach {
                    val user = it.getValue(UserData::class.java)!!
                    if (user.uid == uid) {
                        currentUser = user
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        return currentUser

    }
}