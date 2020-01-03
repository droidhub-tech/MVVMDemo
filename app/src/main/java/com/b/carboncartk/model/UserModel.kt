package com.b.carboncartk.model

import android.text.TextUtils
import android.util.Patterns
import androidx.databinding.BaseObservable
import java.util.*
import java.util.regex.Pattern

class UserModel (private var email: String, private var password: String): BaseObservable() {
    val isDataValid: Boolean

    get() = (!TextUtils.isEmpty(getEmail()) && Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches()
            && password.length>5)
    fun getEmail(): String{

        return email
    }
    fun getPassword(): String{
        return password
    }
    fun setEmail(email: String){
        this.email=email
    }
    fun setPassword(password: String){
        this.password=password
    }
}