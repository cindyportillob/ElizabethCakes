package com.example.elizabethcakes

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.elizabethcakes.utils.Constants
import com.google.firebase.firestore.auth.User
import kotlinx.android.synthetic.main.activity_user_profile.*
import java.io.IOException
import java.util.jar.Manifest

class UserProfileActivity : BaseActivity1(), View.OnClickListener {
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

        iv_user_photo.setOnClickListener(this@UserProfileActivity)
    }

    override fun onClick(v: View?) {
        if(v != null){
            when(v.id){
                R.id.iv_user_photo ->{
                    if (ContextCompat.checkSelfPermission(this,
                            android.Manifest.permission.READ_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_GRANTED){
                        showErrorSnackBar("Selecciona tu imagen",false)
                        Constants.showImageChooser(this)
                    }else{

                        ActivityCompat.requestPermissions(
                            this, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                            Constants.READ_STORAGE_PERMISSION_CODE
                        )
                    }
                }
            }
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == Constants.READ_STORAGE_PERMISSION_CODE){
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //showErrorSnackBar("El permiso a los archivos fue permitido",false)
                Constants.showImageChooser(this)
            }else{
                Toast.makeText(this, resources.getString(R.string.read_storage_permission_denied),
                Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            if(requestCode == Constants.PICK_IMAGE_REQUEST_CODE){
                if(data != null){
                    try {
                        val selectedImageFileUri = data.data!!

                        iv_user_photo.setImageURI(selectedImageFileUri)
                    }catch (e: IOException){
                        e.printStackTrace()
                        Toast.makeText(
                            this@UserProfileActivity,
                            resources.getString(R.string.image_selection_failed),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }
}