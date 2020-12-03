package com.example.elizabethcakes

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.inject.Provider
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.editTextEmailL
import kotlinx.android.synthetic.main.activity_login.editTextpassL
import kotlinx.android.synthetic.main.activity_registro.*


class RegistroActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        setup()

    }

    private fun setup(){
        btnRegistrar.setOnClickListener {
            if (editTextEmail.text.isNotEmpty() && editTextpass.text.isNotEmpty() ){
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(editTextEmail.text.toString(),
                    editTextpass.text.toString()).addOnCompleteListener {
                    if(it.isSuccessful){
                        goHome(it.result?.user?.email ?: "",ProviderType.BASIC)
                    }else{
                        alert()

                    }
                }





            }

        }



    }

    private fun alert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("error")
        builder.setMessage("Se ha producido un error autenticando")
        builder.setPositiveButton("aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()

    }

    private fun goHome(email:String, provider: ProviderType){
        val HomeIntent= Intent(this,MainActivity::class.java).apply {
            putExtra("email",email)
            putExtra("provider",provider.name)
        }
        startActivity(HomeIntent)
    }
}





