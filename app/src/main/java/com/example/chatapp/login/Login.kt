package com.example.chatapp.login

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.chatapp.BaseFragment
import com.example.chatapp.main.MainActivity
import com.example.chatapp.R
import com.example.chatapp.databinding.FragmentLoginBinding
import com.example.chatapp.utlis.emailValidator
import com.example.chatapp.utlis.passwordValidator
import com.example.chatapp.utlis.toast


class Login : BaseFragment<FragmentLoginBinding>(), LoginContract.View {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLoginBinding
        get() = FragmentLoginBinding::inflate
    private lateinit var navController: NavController
    private lateinit var presenter: LoginPresenter
    override fun setupOnViewCreated(view: View) {
        val activity = activity as MainActivity
        activity.hideToolBar()
        navController = Navigation.findNavController(view)
        presenter = LoginPresenter(this)
        binding.btnLogin.setOnClickListener {
            val email = binding.etEmailLogin.text.toString()
            val pass = binding.etPassLogin.text.toString()
            if (emailValidator(email) && passwordValidator(pass))
                presenter.signIn(email, pass)
            else if (!emailValidator(email) && !passwordValidator(pass))
                requireContext().toast(getString(R.string.error))
            else if (!passwordValidator(pass))
                requireContext().toast(getString(R.string.pass_error))
            else
                requireContext().toast(getString(R.string.email_error))

        }


    }

    override fun onStarted() {
        showProgressDialog(requireContext())
    }

    override fun onSuccess() {
        hideProgressDialog()
        navController.navigate(R.id.action_login_to_currentMessages)
    }

    override fun onFailure(message: String) {
        requireContext().toast(message)
        hideProgressDialog()

    }
}