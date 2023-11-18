package com.smartmeds.smartmeds

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.smartmeds.smartmeds.fragments.HomeFragment
import com.smartmeds.smartmeds.fragments.SettingsFragment
import com.smartmeds.smartmeds.fragments.TimeFragment
import com.smartmeds.smartmeds.fragments.hostTimeFragment

class AppActivity : AppCompatActivity() {

    private val homeFragment = HomeFragment()
    private val timeFragment = hostTimeFragment()
    private val settingsFragment = SettingsFragment()

    lateinit var bottomNav : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app)
        replaceFragment(homeFragment)


        bottomNav = findViewById(R.id.bottom_navigation)

        bottomNav.setOnNavigationItemSelectedListener() {
            when (it.itemId) {
                R.id.ic_home -> replaceFragment((homeFragment))
                R.id.ic_time -> replaceFragment((timeFragment))
                R.id.ic_settings -> replaceFragment((settingsFragment))
            }
            true
        }
}



    private fun replaceFragment(fragment: Fragment) {
        if (fragment != null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.commit()
        }
    }
}
