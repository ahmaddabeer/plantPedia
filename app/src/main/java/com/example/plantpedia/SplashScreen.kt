package com.example.plantpedia

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.ProgressBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.plantpedia.mainActivity.MainActivity
import kotlinx.coroutines.Delay

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash_screen)

        val progressBar: ProgressBar = findViewById(R.id.progress)


        progressBar.postDelayed({


            val intent: Intent  =  Intent(this, MainActivity::class.java)
            startActivity(intent)



        },2000)










    }
}