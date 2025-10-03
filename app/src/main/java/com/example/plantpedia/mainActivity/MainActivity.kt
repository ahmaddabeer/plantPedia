package com.example.plantpedia.mainActivity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.plantpedia.Catogray.Catogary
import com.example.plantpedia.R
import com.example.plantpedia.databinding.ActivityMainBinding
import com.example.plantpedia.home.Home
import com.example.plantpedia.profile.Profile
import com.example.plantpedia.wishList.WishList
import java.util.Locale


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)



        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView3, Home())
                .commit()
        }


        binding.bottomnavigatinView.setOnItemSelectedListener { menuItem ->


            when (menuItem.itemId) {

                R.id.nav_home -> {

                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView3, Home()).commit()


                }

                R.id.nav_profile -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fragmentContainerView3, Profile()
                    ).commit()


                }

                R.id.nav_categories -> {

                    supportFragmentManager.beginTransaction().replace(
                        R.id.fragmentContainerView3, Catogary()
                    ).commit()


                }

                R.id.nav_wishlist -> {

                    supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView3,
                        WishList()).commit()


                }


            }

            true


        }

    }
}

