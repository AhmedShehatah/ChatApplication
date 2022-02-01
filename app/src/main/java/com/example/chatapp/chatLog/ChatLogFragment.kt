package com.example.chatapp.chatLog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.chatapp.BaseFragment
import com.example.chatapp.databinding.FragmentChatLogBinding
import com.example.chatapp.firebase.UserData
import com.example.chatapp.main.MainActivity
import com.example.chatapp.utlis.getUID
import com.xwray.groupie.GroupieAdapter


class ChatLogFragment : BaseFragment<FragmentChatLogBinding>(), ChatLogContract.View {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentChatLogBinding
        get() = FragmentChatLogBinding::inflate
    private lateinit var navController: NavController
    private lateinit var userData: UserData
    private lateinit var presenter: ChatLogPresenter
    private lateinit var currentUser: UserData
    private val adapter = GroupieAdapter()
    override fun setupOnViewCreated(view: View) {
        navController = Navigation.findNavController(view)
        val activity = activity as MainActivity
        presenter = ChatLogPresenter(this)
        userData = arguments?.getParcelable("user")!!
        currentUser = activity.userData()
        activity.supportActionBar?.title = userData.userName
        presenter.listenToMessages(userData)
        binding.rvChats.adapter = adapter
        binding.rvChats.scrollToPosition(adapter.itemCount - 1)
        binding.btnSend.setOnClickListener {
            val message = binding.message.text.toString().trim()
            presenter.sendMessages(message, userData)
            binding.message.text.clear()
            binding.rvChats.scrollToPosition(adapter.itemCount)

        }

    }

    override fun onStarted() {

    }

    override fun onSuccess(message: MessageLog) {
        if (message.fromId == getUID())
            adapter.add(ChatFromItem(message, currentUser))
        else adapter.add(ChatToItem(message, userData))
    }

    override fun onFailure() {
        TODO("Not yet implemented")
    }
}