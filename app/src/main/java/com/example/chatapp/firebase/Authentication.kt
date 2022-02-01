package com.example.chatapp.firebase

import android.net.Uri
import android.util.Log
import com.example.chatapp.register.RegisterContract
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import java.util.*

class Authentication(private val contract: RegisterContract.Contract) {
    private lateinit var auth: FirebaseAuth

    fun signUp(userName: String, email: String, password: String, url: Uri?) {

        auth = Firebase.auth
        auth.createUserWithEmailAndPassword(
            email, password
        ).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                if (url != null) {
                    val fileName = UUID.randomUUID().toString()
                    val ref = FirebaseStorage.getInstance().getReference("/images/$fileName")
                    ref.putFile(url).addOnCompleteListener { task2->
                        if (task2.isSuccessful) {
                            val uid = auth.uid ?: ""
                            val reff = FirebaseDatabase.getInstance().getReference("/users/$uid")

                            ref.downloadUrl.addOnSuccessListener {

                                val user = UserData(email, uid, it.toString(), userName)
                                Log.d("photo", it.toString())
                                reff.setValue(user)
                                    .addOnSuccessListener { contract.onSuccess() }
                            }


                        }

                    }
                } else {
                    val uid = auth.uid ?: ""
                    val ref = FirebaseDatabase.getInstance().getReference("/users/$uid")
                    val user = UserData(email, uid, url.toString(), userName)
                    ref.setValue(user)
                        .addOnSuccessListener { contract.onSuccess() }

                }


            } else contract.onFailure("Failed")

        }.addOnFailureListener {
            contract.onFailure(it.message.toString())
        }

    }

    fun signIn(email: String, password: String) {
        auth = Firebase.auth
        auth.signInWithEmailAndPassword(
            email, password
        ).addOnCompleteListener { task ->
            if (task.isSuccessful) {

                contract.onSuccess()
            } else contract.onFailure("Failed")

        }.addOnFailureListener {
            contract.onFailure(it.message.toString())
        }
    }


}