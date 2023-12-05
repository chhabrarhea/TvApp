package com.rhea.tvapp

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.lifecycleScope
import com.rhea.tvapp.databinding.ActivityMainBinding
import com.rhea.tvapp.util.Util
import com.rhea.tvapp.view.HomeFragment
import com.rhea.tvapp.view.MainViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity: FragmentActivity(), View.OnKeyListener {

    private val viewModel: MainViewModel by viewModels()

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private var lastSelectedMenu: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        addHomeFragment()
        setLiveDataObserver()
        setKeyListeners()
        switchLastSelected(binding.btnHome)
    }

    private fun setKeyListeners() {
        binding.apply {
            btnGenre.setOnKeyListener(this@MainActivity)
            btnHome.setOnKeyListener(this@MainActivity)
            btnLanguage.setOnKeyListener(this@MainActivity)
            btnSearch.setOnKeyListener(this@MainActivity)
            btnSettings.setOnKeyListener(this@MainActivity)
            btnSports.setOnKeyListener(this@MainActivity)
            btnTv.setOnKeyListener(this@MainActivity)
            btnMovies.setOnKeyListener(this@MainActivity)
        }
    }

    private fun setLiveDataObserver() {
        lifecycleScope.launch {
            viewModel.isSideMenuOpened.collectLatest {
                toggleSideMenu(it)
            }
        }
        viewModel.clickedMovie.distinctUntilChanged().observe(this) {
            startActivity(DetailActivity.create(this, it))
        }
    }

    private fun toggleSideMenu(open: Boolean) {
        binding.apply {
            val width = if (open) 16 else 5
            blfNavBar.requestLayout()
            blfNavBar.layoutParams.width = Util.getWidthInPercent(this@MainActivity, width)
            lastSelectedMenu?.isActivated = !open
            if (!open)
                container.requestFocus()
            else lastSelectedMenu?.requestFocus()
        }
    }

    private fun switchLastSelected(newSelected: View) {
        lastSelectedMenu?.isActivated = false
        lastSelectedMenu = newSelected
        newSelected.requestFocus()
        newSelected.isActivated = true
    }

    private fun addHomeFragment() {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, HomeFragment())
            commit()
        }
    }
    override fun onKey(view: View?, eventId: Int, event: KeyEvent?): Boolean {
        when(eventId) {
            KeyEvent.KEYCODE_DPAD_LEFT -> viewModel.isSideMenuOpened.value = true
            KeyEvent.KEYCODE_DPAD_CENTER -> {
                if (view == null ) return false
                switchLastSelected(view)
            }
        }
        return false
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
           viewModel.isSideMenuOpened.value = false
        }
        return super.onKeyDown(keyCode, event)
    }
}