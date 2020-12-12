package com.example.elizabethcakes

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
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
   private lateinit var detalleUsuario: Users
    private var selectedImageFileUri: Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        if (intent.hasExtra(Constants.EXTRA_USER_DETAILS)){
            detalleUsuario = intent.getParcelableExtra(Constants.EXTRA_USER_DETAILS)!!
        }




       et_first_name.isEnabled = false
        et_first_name.setText(detalleUsuario.Nombre)

        et_email.isEnabled = false
        et_email.setText(detalleUsuario.Email)

        iv_user_photo.setOnClickListener(this@UserProfileActivity)
        btn_submit.setOnClickListener(this@UserProfileActivity)
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

                R.id.btn_submit ->{
                    showProgressDialog("Por favor Espera")
                    FireStore().uploadImageToCloudStore(this, selectedImageFileUri)

                    if (validateUserProfileDetails()){

                        val userHashMap = HashMap<String, Any>()
                        val numeroTelefono = et_mobile_number.text.toString().trim { it <= ' '}
                        val gender = if(rb_male.isChecked){
                            Constants.MALE
                        }else{
                            Constants.FEMALE
                        }

                        if(numeroTelefono.isNotEmpty()){
                            userHashMap[Constants.MOBILE] = numeroTelefono.toLong()
                        }

                        userHashMap[Constants.GENDER] = gender

                        showProgressDialog("Espera por favor")


                        FireStore().updateUserProfileData(this, userHashMap)

                        //showErrorSnackBar("Su detalle esta completo, puede actualizarlo",false)
                    }
                }
            }
        }
    }

    fun userProfileUpdateSuccess(){
        hideProgressDialog()
        Toast.makeText(
            this@UserProfileActivity,
            "El Perfil ha sido actualizado exitosamente"
            ,Toast.LENGTH_SHORT
        ).show()
        startActivity(Intent(this@UserProfileActivity,MainActivity::class.java))
        finish()
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
                         selectedImageFileUri = data.data!!

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
    private fun validateUserProfileDetails():Boolean{
        return when{
            TextUtils.isEmpty(et_mobile_number.text.toString().trim(){it <= ' '}) -> {
                showErrorSnackBar("el campo de numero telefonico esta vacio",true)
                false
            }else->{
                true
            }

        }
    }

    fun imageUploadSuccess(imageURL: String){
        hideProgressDialog()
        Toast.makeText(this@UserProfileActivity,
        "Tu imagen se ha cargado exitosamente",
            Toast.LENGTH_SHORT
        ).show()
    }
}