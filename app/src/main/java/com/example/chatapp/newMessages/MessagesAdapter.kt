package com.example.chatapp.newMessages

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chatapp.R
import com.example.chatapp.databinding.ChatModelBinding


class MessagesAdapter(
    private val list: ArrayList<com.example.chatapp.firebase.UserData>,
    private val context: Context
) :
    RecyclerView.Adapter<MessagesAdapter.ViewHolder>() {
    inner class ViewHolder(binding: ChatModelBinding) : RecyclerView.ViewHolder(binding.root) {
        var name = binding.tvName
        var photo = binding.ivPhoto
        var item = binding.item
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ChatModelBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.name.text = item.userName
        Glide.with(context).load(item.url).circleCrop().into(holder.photo)
        holder.item.setOnClickListener {
            val bundle = bundleOf("user" to item)
            it.findNavController().navigate(R.id.action_newMessages_to_chatLogFragment, bundle)

        }

    }

    override fun getItemCount(): Int = list.size
}

