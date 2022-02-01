package com.example.chatapp.utlis

import android.content.Context
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

fun emailValidator(email: String): Boolean =
    Patterns.EMAIL_ADDRESS.matcher(email).matches()

fun passwordValidator(password: String): Boolean =
    password.length >= 8

fun Context.toast(message: String) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun getUID(): String = FirebaseAuth.getInstance().uid!!