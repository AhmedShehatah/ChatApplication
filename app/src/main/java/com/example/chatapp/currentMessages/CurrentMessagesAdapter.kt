package com.example.chatapp.currentMessages

import android.view.View
import com.bumptech.glide.Glide
import com.example.chatapp.R
import com.example.chatapp.chatLog.MessageLog
import com.example.chatapp.databinding.RecentChatModelBinding
import com.xwray.groupie.viewbinding.BindableItem

class CurrentMessagesAdapter(private val message: MessageLog) :
    BindableItem<RecentChatModelBinding>() {
    override fun bind(holder: RecentChatModelBinding, position: Int) {
        holder.tvUserName.text = message.userName
        holder.tvLastMessage.text = message.message
        Glide.with(holder.root.context).load(message.photo).circleCrop().into(holder.ivPhoto)
    }

    override fun getLayout(): Int = R.layout.recent_chat_model

    override fun initializeViewBinding(p0: View): RecentChatModelBinding =
        RecentChatModelBinding.bind(p0)
}