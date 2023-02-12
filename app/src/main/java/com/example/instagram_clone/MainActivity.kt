package com.example.instagram_clone

import android.os.Bundle
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.instagram_clone.databinding.ActivityMainBinding
import com.example.instagram_clone.databinding.FragmentNotificationBinding
import com.example.instagram_clone.fragments.HomeFragment
import com.example.instagram_clone.fragments.NotificationFragment
import com.example.instagram_clone.fragments.ProfileFragment
import com.example.instagram_clone.fragments.SearchFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private val onNavItemSecetedListener=BottomNavigationView
        .OnNavigationItemSelectedListener{item->
          when(item.itemId)
          {
              R.id.nav_home->{
                  moveToFragment(HomeFragment())
                  return@OnNavigationItemSelectedListener true
              }

              R.id.nav_search->{
                  moveToFragment(SearchFragment())
                  return@OnNavigationItemSelectedListener true
              }

              R.id.nav_add_post->{

                  return@OnNavigationItemSelectedListener true
              }



              R.id.nav_notifications->{
                  moveToFragment(NotificationFragment())
                  return@OnNavigationItemSelectedListener true

              }

              R.id.nav_profile->{
                  moveToFragment(ProfileFragment())
                  return@OnNavigationItemSelectedListener true

              }
          }

false
    }






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

nav_view.setOnNavigationItemSelectedListener(onNavItemSecetedListener)

        moveToFragment(HomeFragment())


    }

    private fun moveToFragment(fragment:Fragment){
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.fragments_container, fragment!!)
        fragmentTransaction.addToBackStack(null)

        fragmentTransaction.commit()
    }
}