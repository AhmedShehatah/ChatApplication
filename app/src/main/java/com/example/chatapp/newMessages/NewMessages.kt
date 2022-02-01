package com.example.chatapp.newMessages

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.chatapp.BaseFragment
import com.example.chatapp.databinding.FragmentNewMessagesBinding
import com.example.chatapp.firebase.UserData
import com.example.chatapp.main.MainActivity
import com.example.chatapp.utlis.toast

class NewMessages : BaseFragment<FragmentNewMessagesBinding>(),
    NewMessagesContract.View {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentNewMessagesBinding
        get() = FragmentNewMessagesBinding::inflate
    private lateinit var adapter: MessagesAdapter
    private lateinit var navController: NavController
    private lateinit var presenter: NewMessagesPresenter

    override fun setupOnViewCreated(view: View) {
        navController = Navigation.findNavController(view)
        presenter = NewMessagesPresenter(this)
        presenter.fetchUsers()
        val activity = activity as MainActivity
        activity.supportActionBar?.title = "Select User"
        binding.rvChats.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )

    }

    override fun onStarted() {
        showProgressDialog(requireContext())
    }

    override fun onSuccess(list: ArrayList<UserData>) {
        hideProgressDialog()
        adapter = MessagesAdapter(list, requireContext())
        binding.rvChats.adapter = adapter

    }

    override fun onFailure(Message: String) {
        hideProgressDialog()
        requireContext().toast(Message)
    }
}