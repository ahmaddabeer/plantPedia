package com.example.plantpedia.mainActivity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.plantpedia.Catogray.Catogary
import com.example.plantpedia.R
import com.example.plantpedia.databinding.ActivityMainBinding
import com.example.plantpedia.home.Home
import com.example.plantpedia.profile.Profile
import com.example.plantpedia.wishList.WishList


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadFrag(Home(),0)



        //

        binding.bottomNavigation.setOnItemSelectedListener { menuItem ->

            when (menuItem.itemId) {

                R.id.nav_home -> {

                    loadFrag(Home(), 1)


                }

                R.id.nav_profile -> {

                    loadFrag(Profile(), 0)





                }

                R.id.nav_categories -> {
                    loadFrag(Catogary(),1)


                }

                R.id.nav_wishlist -> {

                    loadFrag(WishList(),1)


                }


            }

            true
        }


        // Handle system bars padding


    }

    // method to load fragment


    private fun loadFrag(fragment: Fragment, flag: Int) {

        val fm = supportFragmentManager

        val ft = fm.beginTransaction()

        if (flag == 0) {

            ft.add(R.id.fragment_container, fragment)

        } else {

            ft.replace(R.id.fragment_container, fragment)
        }
        ft.commit()

    }

}

