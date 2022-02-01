package com.example.chatapp.register

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.example.chatapp.BaseFragment
import com.example.chatapp.main.MainActivity
import com.example.chatapp.R
import com.example.chatapp.databinding.FragmentRegisterBinding
import com.example.chatapp.utlis.emailValidator
import com.example.chatapp.utlis.passwordValidator
import com.example.chatapp.utlis.toast
import com.google.firebase.auth.FirebaseAuth


class Register : BaseFragment<FragmentRegisterBinding>(), RegisterContract.View {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRegisterBinding
        get() = FragmentRegisterBinding::inflate
    private lateinit var navController: NavController
    private lateinit var presenter: RegisterPresenter
    private var imageUri: Uri? = null
    override fun setupOnViewCreated(view: View) {
        val activity = activity as MainActivity
        activity.hideToolBar()
        navController = Navigation.findNavController(view)
        checkUser()
        binding.textView.setOnClickListener {
            navController.navigate(R.id.action_register_to_login)
        }
        presenter = RegisterPresenter(this)
        binding.btnRegister.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val email = binding.etEmail.text.toString()
            val pass = binding.etPassword.text.toString()
            if (emailValidator(email) && passwordValidator(pass) && username.isNotEmpty())
                presenter.signUp(username, email, pass, imageUri)
            else if (!emailValidator(email) && !passwordValidator(pass))
                requireContext().toast(getString(R.string.error))
            else if (!passwordValidator(pass))
                requireContext().toast(getString(R.string.pass_error))
            else
                requireContext().toast(getString(R.string.email_error))

        }
        binding.userImage.setOnClickListener { uploadImage() }


    }

    private fun uploadImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, 0)

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {
            imageUri = data.data
            Glide.with(binding.root).load(imageUri).centerCrop().circleCrop().into(binding.photo)
            binding.tvSelectPhoto.text = ""
        }
    }

    override fun onStarted() {
        showProgressDialog(requireContext())
    }

    override fun onSuccess() {
        hideProgressDialog()
        navController.navigate(R.id.action_register_to_currentMessages)
    }

    override fun onFailure(message: String) {
        requireContext().toast(message)
        hideProgressDialog()
    }

    private fun checkUser() {
        val uid = FirebaseAuth.getInstance().uid
        if (uid != null) navController.navigate(R.id.action_register_to_currentMessages)
    }

}