package com.example.chatapp.chatLog

class MessageLog(
    val id: String,
    val message: String,
    val fromId: String,
    val toId: String,
    val timeStamp: String, val userName: String, val photo: String
) {
    constructor() : this("", "", "", "", "", "", "")
}
