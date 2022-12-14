package com.example.cinematics.ui.splash_screen

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.cinematics.R
import com.example.cinematics.data.local.dao.TokenDao
import com.example.cinematics.data.local.dao.UserDao
import com.example.cinematics.databinding.ActivitySplashBinding
import com.example.cinematics.ui.screens.acitivity.MainActivity
import com.example.cinematics.ui.splash_screen.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private var _binding: ActivitySplashBinding? = null
    private val binding get() = _binding!!


    @Inject
    lateinit var tokenDao: TokenDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_MySplashScreen)
        _binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            val token = tokenDao.getToken()?.token
            if (token != null) {
                Intent(applicationContext,
                    MainActivity::class.java).apply { startActivity(this) }
            }
        }

        val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = resources.getString(R.string.login)
                1 -> tab.text = resources.getString(R.string.register)
            }
        }.attach()

    }

}