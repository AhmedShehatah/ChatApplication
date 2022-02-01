package com.example.chatapp.chatLog

import android.view.View
import com.bumptech.glide.Glide
import com.example.chatapp.R
import com.example.chatapp.databinding.MessageFormBinding
import com.example.chatapp.databinding.MessageToBinding
import com.example.chatapp.firebase.UserData
import com.xwray.groupie.viewbinding.BindableItem


class ChatFromItem(
    private val message: MessageLog,
    private val currentUser: UserData

) : BindableItem<MessageFormBinding>() {
    override fun bind(holder: MessageFormBinding, p1: Int) {
        val text = holder.messegeText
        text.text = message.message
        val photo = holder.userImage
        Glide.with(holder.layout.context).load(currentUser.url).circleCrop().into(photo)
    }

    override fun getLayout(): Int {
        return R.layout.message_form
    }

    override fun initializeViewBinding(p0: View): MessageFormBinding {
        return MessageFormBinding.bind(p0)
    }
}

class ChatToItem(
    private val message: MessageLog,
    private val userData: UserData
) : BindableItem<MessageToBinding>() {
    override fun bind(holder: MessageToBinding, p1: Int) {
        val text = holder.messegeText
        val photo = holder.userImage
        text.text = message.message
        Glide.with(holder.root.context).load(userData.url).circleCrop().into(photo)
    }

    override fun getLayout(): Int {
        return R.layout.message_to
    }

    override fun initializeViewBinding(p0: View): MessageToBinding {
        return MessageToBinding.bind(p0)
    }
}
