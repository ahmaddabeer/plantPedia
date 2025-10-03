package com.example.plantpedia.AuthanticationScreen.SingUpActivity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.plantpedia.AuthanticationScreen.LoginActivity.LoginActivity
import com.example.plantpedia.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)

        setContentView(binding.root)


        binding.tvLoginRedirect.setOnClickListener {


            val gotoLogin= Intent(this, LoginActivity::class.java)
            gotoLogin.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(gotoLogin)
        }








    }
}