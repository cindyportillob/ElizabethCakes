package com.example.elizabethcakes
import android.view.WindowInsets
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Build
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN
import com.example.elizabethcakes.utils.Recuperacion_Password
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

        txt_pass_recu.setOnClickListener {

            startActivity(Intent(this@LoginActivity, Recuperacion_Password::class.java))
            finish()
        }

    }

}






