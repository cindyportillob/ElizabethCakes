package com.example.elizabethcakes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import com.example.elizabethcakes.utils.Recuperacion_Password
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        txt_pass_recu.setOnClickListener {

            startActivity(Intent(this, Recuperacion_Password::class.java))
            finish()
        }

    }
}