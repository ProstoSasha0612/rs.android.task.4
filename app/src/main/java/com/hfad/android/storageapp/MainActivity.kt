package com.hfad.android.storageapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.hfad.android.storageapp.databinding.ActivityMainBinding
import com.hfad.android.storageapp.fragments.HumanListFragment
import com.hfad.android.storageapp.fragments.SettingsFragment


private var _binding: ActivityMainBinding? = null
private val binding get() = requireNotNull(_binding)


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)


        binding.toolbar.setNavigationOnClickListener {
            if(supportFragmentManager.backStackEntryCount > 0){
                binding.toolbar.setNavigationIcon(null)
            }
            onBackPressed()
        }


        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, HumanListFragment())
                .commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.toolbar_btn_settings) {
            supportFragmentManager.popBackStack()
            supportFragmentManager.beginTransaction()
                .addToBackStack("Settings fragment")
                .replace(R.id.container, SettingsFragment())
                .commit()
        }
        return true
    }

}