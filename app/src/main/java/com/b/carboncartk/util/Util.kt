package com.b.carboncartk.util

import android.content.Context
import android.util.Log
import android.widget.Toast
import java.util.regex.Pattern

class Util {

    companion object {
        fun isEmailValid(email: String): Boolean {
            val expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
            val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
            val matcher = pattern.matcher(email)
            return matcher.matches()
        }

        fun Context.toast(message: String) =
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }



}