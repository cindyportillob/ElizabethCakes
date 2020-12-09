package com.example.elizabethcakes

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.WindowInsets
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_login.tv_registrar
import kotlinx.android.synthetic.main.activity_registro.*

class Registro : BaseActivity1() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        tv_login.setOnClickListener {


            startActivity(Intent(this@Registro, LoginActivity::class.java))
            finish()
        }

        button2.setOnClickListener{
            validateRegisterDetails()
        }
    }
    private fun validateRegisterDetails(): Boolean {
        return when {
            TextUtils.isEmpty(nombre_completo.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(
                    resources.getString(R.string.err_msg_enter_nombre_completo),
                    true
                )
                false
            }
            TextUtils.isEmpty(email.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(
                    resources.getString(R.string.err_msg_enter_email),
                    true
                )
                false
            }
            TextUtils.isEmpty(Password1.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(
                    resources.getString(R.string.err_msg_enter_Password1),
                    true
                )
                false
            }
            Password1.text.toString().trim { it <= ' ' } != Password2.text.toString()
                .trim { it <= ' ' } -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_Password2), true)
                false
            }
            else -> {
                showErrorSnackBar(resources.getString(R.string.registery_successfull), false)
                true
            }
        }
    }

}

