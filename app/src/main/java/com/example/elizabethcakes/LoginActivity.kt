package com.example.elizabethcakes
import android.view.WindowInsets
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Build
import android.text.TextUtils
import android.view.View
import android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_registro.*

class LoginActivity : BaseActivity1(), View.OnClickListener {
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

        button.setOnClickListener{
            validateLoginDetails()
        }

        txt_pass_recu.setOnClickListener {
            val intent = Intent(this@LoginActivity, Recuperacion_Password::class.java)
            startActivity(intent)
        }

        tv_registrar.setOnClickListener {
            val intent = Intent(this@LoginActivity, Registro::class.java)
            startActivity(intent)
        }

    }
    override fun onClick(view: View?) {
        if(view != null){
            when(view.id){
                R.id.txt_pass_recu ->{
                    val intent = Intent(this@LoginActivity, Recuperacion_Password::class.java)
                    startActivity(intent)

                }
                R.id.button->{
                    validateLoginDetails()
                }

                R.id.tv_registrar->{
                    val intent = Intent(this@LoginActivity, Registro::class.java)
                    startActivity(intent)
                }
            }
        }
    }

    private fun validateLoginDetails(): Boolean {
        return when {
            TextUtils.isEmpty(editTextTextPersonName.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_email), true)
                false
            }
            TextUtils.isEmpty(editTextTextPassword.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_Password1), true)
                false
            }
            else -> {
                showErrorSnackBar("Datos correctos.", false)
                true
            }
        }
    }


    private fun logInRegisteredUser(){
        if(validateLoginDetails()){

        }
    }

}






