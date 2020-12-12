package com.example.elizabethcakes.utils

import android.app.Activity
import android.content.Intent
import android.provider.MediaStore
import com.example.elizabethcakes.UserProfileActivity

object Constants {

    const val USERS: String = "users"
    const val ELIZABETHCAKES_PREFERENCES: String = "ElizabethCakesPrefs"
    const val LOGGED_IN_USERNAME: String = "logged_in_username"
    const val EXTRA_USER_DETAILS: String = "extra_user_details"


    const val READ_STORAGE_PERMISSION_CODE = 2
    const val PICK_IMAGE_REQUEST_CODE = 1


    const val MALE : String = "male"
    const val FEMALE: String = "female"

    const val MOBILE: String = "mobile"
    const val GENDER: String = "Gender"

    fun showImageChooser(activity: Activity ){
        var galleryIntent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        activity.startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST_CODE)
        }
    }