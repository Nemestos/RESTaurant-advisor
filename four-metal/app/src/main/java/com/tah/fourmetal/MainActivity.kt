package com.tah.fourmetal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

const val EXTRA_MESSAGE = "com.tah.fourmetal.MESSAGE"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView);
        val restaurantsFragment = RestaurantsFragment();
        val loginFragment = LoginFragment();
        val registerFragment = RegisterFragment();
        setCurrentFragment(restaurantsFragment)
        bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.restaurant->setCurrentFragment(restaurantsFragment)
                R.id.login->setCurrentFragment(loginFragment)
                R.id.register->setCurrentFragment(registerFragment)
            }
            true
        }
    }
    private fun setCurrentFragment(fragment:Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,fragment)
            commit()
        }

}