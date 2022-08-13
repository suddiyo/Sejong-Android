package com.example.a22_2_sejong_project.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.a22_2_sejong_project.databinding.ActivityAppLoginBinding

class AppLoginActivity : AppCompatActivity() {
    private var _Binding: ActivityAppLoginBinding? = null
    private val binding get() = _Binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _Binding = ActivityAppLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginSignupBtn.setOnClickListener {
            startActivity(Intent(this,SignupActivity::class.java))
        }
    }
    override fun onDestroy() {
        _Binding = null
        super.onDestroy()
    }
}