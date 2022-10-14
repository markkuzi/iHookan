package com.example.ihookan.presentation.tabs.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.example.ihookan.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest

class RegisterActivity : AppCompatActivity() {

    private var binding: ActivityRegisterBinding? = null
    var auth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        auth = FirebaseAuth.getInstance()

        binding?.btnRegister?.setOnClickListener(View.OnClickListener {

            createUser()

        })

        binding?.tvLoginHere?.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
        })
    }

    private fun createUser() {
        val name: String = binding?.etRegisterName?.getText().toString()
        val email: String = binding?.etRegisterEmail?.getText().toString()
        val password: String = binding?.etRegisterPass?.getText().toString()
        if (TextUtils.isEmpty(name)) {
            binding?.etRegisterName?.setError("Имя не может быть пустым")
            binding?.etRegisterName?.requestFocus()
        } else if (TextUtils.isEmpty(email)) {
            binding?.etRegisterEmail?.setError("Email не может быть пустым")
            binding?.etRegisterEmail?.requestFocus()
        } else if (TextUtils.isEmpty(password)) {
            binding?.etRegisterPass?.setError("Пароль не может быть пустым")
            binding?.etRegisterPass?.requestFocus()
        } else {
            auth!!.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val displayName = UserProfileChangeRequest.Builder().setDisplayName(name).build()
                    auth!!.currentUser?.updateProfile(displayName)
                    Toast.makeText(
                        this@RegisterActivity,
                        "Пользователь зарегестрирован успешно",
                        Toast.LENGTH_SHORT
                    ).show()
                    startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                } else {
                    Toast.makeText(
                        this@RegisterActivity,
                        "Ошибка при регистрации: " + task.exception?.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }



}