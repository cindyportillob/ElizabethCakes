package com.example.elizabethcakes

import android.app.Activity
import android.net.Uri
import android.util.Log
import com.example.elizabethcakes.utils.Constants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.auth.User
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference


class FireStore {

    private val mFirestore = FirebaseFirestore.getInstance()


    private val mFireStore = FirebaseFirestore.getInstance()

    // TODO Step 7: Create a function to access the Cloud Firestore and create a collection.
    // START
    /**
     * A function to make an entry of the registered user in the FireStore database.
     */
    fun registerUser(activity: Registro, userInfo: Users) {

        // The "users" is collection name. If the collection is already created then it will not create the same one again.
        mFireStore.collection(Constants.USERS)
            // Document ID for users fields. Here the document it is the User ID.
            .document(userInfo.id)
            // Here the userInfo are Field and the SetOption is set to merge. It is for if we wants to merge later on instead of replacing the fields.
            .set(userInfo, SetOptions.merge())
            .addOnSuccessListener {

                // Here call a function of base activity for transferring the result to it.
                activity.Registroexitoso()
            }
            .addOnFailureListener { e ->

                Log.e(
                    activity.javaClass.simpleName,
                    "Error no se pudo registrar el user.",
                    e
                )
            }
    }

    fun getCurrentUserID():String{
        //crear instancia de currentUser a traves de FirebaseAuth
        val currentUser= FirebaseAuth.getInstance().currentUser

        //creamos variable para asignar el currentUserId en caso que sea nulo, saldra en blanco
        var currentUserId= ""
        if(currentUser != null){
            currentUserId = currentUser.uid
        }
        return currentUserId
    }


    fun getUserDetails(activity: Activity){

        mFireStore.collection(Constants.USERS)
                //El documento id para llegar al campo de user
            .document(getCurrentUserID())
            .get()
            .addOnSuccessListener { document ->

                Log.i(activity.javaClass.simpleName, document.toString())

                val user = document.toObject(Users::class.java)!!

                //TODO Step 6: Pass the result to the login activity
                //Start
                when(activity){
                    is LoginActivity -> {
                        //llamar a una funcion de la actividad base para transferir el resultado
                        activity.userLoggedInSuccess(user)
                    }
                }
                //Fin
            }
            .addOnFailureListener{e ->
                when(activity){
                    is LoginActivity -> {
                        activity.hideProgressDialog()
                    }
                }

            }

    }


    fun updateUserProfileData(activity: Activity, userHashMap: HashMap<String, Any>){
        mFireStore.collection(Constants.USERS)
            .document(getCurrentUserID())
            .update(userHashMap)
            .addOnSuccessListener {
                when(activity){
                    is UserProfileActivity ->{
                        activity.userProfileUpdateSuccess()
                    }
                }


            }.addOnFailureListener{
                    e->
                when(activity){
                    is UserProfileActivity ->{
                        activity.hideProgressDialog()
                    }
                }
                Log.e(
                    activity.javaClass.simpleName,
                    "Error al actualizar el detalle del usuario",
                    e
                )

            }
    }


    fun uploadImageToCloudStore(activity: Activity, imageFileURI:Uri?){
        val sRef: StorageReference = FirebaseStorage.getInstance().reference.child(
            Constants.USER_PROFILE_IMAGE
                    + System.currentTimeMillis()+"."
        + Constants.getFileExtension(
                activity,
                imageFileURI
            )
        )

        sRef.putFile(imageFileURI!!).addOnSuccessListener { taskSnapshot ->
            Log.e(
                "Firebase Image URL",
                taskSnapshot.metadata!!.reference!!.downloadUrl.toString()
            )
            taskSnapshot.metadata!!.reference!!.downloadUrl
                .addOnSuccessListener { uri->
                    Log.e("URL de imagen Descargable",
                    uri.toString())
                    when(activity){
                        is UserProfileActivity ->{
                            activity.imageUploadSuccess(uri.toString())
                        }
                    }
                }
        }
            .addOnFailureListener{exception ->

                when(activity){
                    is UserProfileActivity->{
                        activity.hideProgressDialog()
                    }
                }
                Log.e(
                    activity.javaClass.simpleName,
                    exception.message,
                    exception
                )
            }
    }

}