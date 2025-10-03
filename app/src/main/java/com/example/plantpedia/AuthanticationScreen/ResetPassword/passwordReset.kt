package com.example.plantpedia.AuthanticationScreen.ResetPassword

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.plantpedia.AuthanticationScreen.LoginActivity.LoginActivity
import com.example.plantpedia.databinding.ActivityPasswordResetBinding

class passwordReset : AppCompatActivity() {

    private lateinit var binding: ActivityPasswordResetBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPasswordResetBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.tvGoToLogin.setOnClickListener {


            val gotoLogin = Intent(this, LoginActivity::class.java)
            gotoLogin.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(gotoLogin)
        }

    }
}