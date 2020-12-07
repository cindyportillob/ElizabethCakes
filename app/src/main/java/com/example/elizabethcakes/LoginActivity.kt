package com.example.elizabethcakes

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowInsets
import android.view.WindowManager
import android.view.WindowManager.LayoutParams.*
import com.google.firebase.firestore.core.View
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

                @Suppress("DEPRECATION")
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    window.insetsController?.hide(WindowInsets.Type.statusBars())
                }else{
                    window.setFlags(
                        FLAG_FULLSCREEN,
                        FLAG_FULLSCREEN
                    )
                    }
        tv_registrar.setOnClickListener {


            startActivity(Intent(this@LoginActivity,Registro::class.java))
            finish()
                }





    }
}

