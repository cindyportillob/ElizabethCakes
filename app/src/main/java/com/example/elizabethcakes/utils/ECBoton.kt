package com.example.elizabethcakes.utils

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton

class ECBoton(context: Context, attrs: AttributeSet):AppCompatButton(context,attrs) {
    init{
     applyFont()
    }
    private fun applyFont() {
        val typeface: Typeface =
            Typeface.createFromAsset(context.assets, "Envellope.ttf")
        setTypeface(typeface)
    }
}