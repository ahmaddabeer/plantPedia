package com.example.plantpedia.AuthanticationScreen.LoginActivity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.plantpedia.AuthanticationScreen.ResetPassword.passwordReset
import com.example.plantpedia.AuthanticationScreen.SingUpActivity.SignUpActivity
import com.example.plantpedia.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private  lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.tvSignupRedirect.setOnClickListener {


            val gotoSignup = Intent(this, SignUpActivity::class.java)
            gotoSignup.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(gotoSignup)
        }

        binding.tvForgotPassword.setOnClickListener {

            val gotoforgentPasswordActivity = Intent(this, passwordReset::class.java)

            gotoforgentPasswordActivity.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(gotoforgentPasswordActivity)
        }

    }
}