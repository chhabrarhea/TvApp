package com.rhea.tvapp

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import com.rhea.tvapp.databinding.ActivityMainBinding
import com.rhea.tvapp.view.HomeFragment
import com.rhea.tvapp.view.MainViewModel

class MainActivity: FragmentActivity() {

    private val viewModel: MainViewModel by viewModels()

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        addHomeFragment()
    }

    private fun addHomeFragment() {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.home_fragment, HomeFragment())
            commit()
        }
    }
}