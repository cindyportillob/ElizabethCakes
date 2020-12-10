package com.example.elizabethcakes

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

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
        mFireStore.collection("users")
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



}