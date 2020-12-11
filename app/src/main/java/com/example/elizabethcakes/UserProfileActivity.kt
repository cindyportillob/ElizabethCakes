package com.example.elizabethcakes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.elizabethcakes.utils.Constants
import com.google.firebase.firestore.auth.User
import kotlinx.android.synthetic.main.activity_user_profile.*

class UserProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)


       var detalleUsuario: Users = Users()
        if (intent.hasExtra(Constants.EXTRA_USER_DETAILS)){
            detalleUsuario = intent.getParcelableExtra(Constants.EXTRA_USER_DETAILS)!!
        }




       //et_first_name.isEnabled = false
        //et_first_name.setText(detalleUsuario.Nombre)

        //et_email.isEnabled = false
        //et_email.setText(detalleUsuario.Email)


    }
}