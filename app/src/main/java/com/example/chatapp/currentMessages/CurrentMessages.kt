package com.example.chatapp.currentMessages

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.chatapp.BaseFragment
import com.example.chatapp.chatLog.MessageLog
import com.example.chatapp.databinding.FragmentCurrentMessagesBinding
import com.example.chatapp.main.MainActivity
import com.xwray.groupie.GroupieAdapter

class CurrentMessages : BaseFragment<FragmentCurrentMessagesBinding>(),
    CurrentMessagesContract.View {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCurrentMessagesBinding
        get() = FragmentCurrentMessagesBinding::inflate
    private lateinit var navController: NavController
    private val adapter = GroupieAdapter()
    private lateinit var presenter: CurrentMessagesPresenter
    private val messagesMap = HashMap<String, MessageLog>()
    override fun setupOnViewCreated(view: View) {
        navController = Navigation.findNavController(view)
        val activity = activity as MainActivity
        activity.showToolBar()

        presenter = CurrentMessagesPresenter(this)
        presenter.listenLatestMessages()
        binding.rvRecentChats.adapter = adapter
        binding.rvRecentChats.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )

    }

    override fun onAddedMessage(message: MessageLog) {
        messagesMap[message.fromId] = message
        adapter.clear()
        messagesMap.values.forEach {
            adapter.add(CurrentMessagesAdapter(it))
        }
    }
}