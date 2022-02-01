package com.example.chatapp.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.chatapp.R
import com.example.chatapp.databinding.ActivityMainBinding
import com.example.chatapp.firebase.UserData
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.menu_new_messages -> {
                navController.navigate(R.id.newMessages)
            }
            R.id.menu_sign_out -> {
                FirebaseAuth.getInstance().signOut()
                navController.popBackStack()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun showToolBar() {
        supportActionBar?.show()
    }

    fun hideToolBar() {
        supportActionBar?.hide()
    }


    override fun userData(): UserData = MainPresenter().getUserData()
}