package com.b.carboncartk.ui.viewmodel

import android.app.Application
import android.content.Context
import android.text.TextUtils
import android.widget.Toast
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.b.carboncartk.R
import com.b.carboncartk.model.User
import com.b.carboncartk.network.BackEndApi
import com.b.carboncartk.network.WebServiceClient
import com.b.carboncartk.util.SingleLiveEvent
import com.b.carboncartk.util.Util
import com.b.carboncartk.util.Util.Companion.toast
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(application: Application) : AndroidViewModel(application), Callback<User> {


    var btnSelected: ObservableBoolean? = null
    var email: ObservableField<String>? = null
    var password: ObservableField<String>? = null
    var imageUrl: ObservableField<String>? = null
    var progressDialog: SingleLiveEvent<Boolean>? = null
    var userLogin: MutableLiveData<User>? = null
    var toastMsg: SingleLiveEvent<String>? =null




    init {
        btnSelected = ObservableBoolean(false)
        progressDialog = SingleLiveEvent<Boolean>()
        email = ObservableField("")
        imageUrl = ObservableField()
        password = ObservableField("")
        userLogin = MutableLiveData<User>()
        toastMsg=SingleLiveEvent<String>()

    }

    fun onEmailChanged(s: CharSequence, start: Int, befor: Int, count: Int) {
        btnSelected?.set(Util.isEmailValid(s.toString()) && password?.get()!!.length >= 2)
    }
    fun onPasswordChanged(s: CharSequence, start: Int, befor: Int, count: Int) {
        btnSelected?.set(Util.isEmailValid(email?.get()!!) && s.toString().length >= 2)
    }

    fun login() {
        if (TextUtils.isEmpty(email!!.get())){
            toastMsg?.value="Please Enter Email Id"
        }
        else if (TextUtils.isEmpty(password?.get())){
            toastMsg?.value="Please Enter Password"
        }
        else {

            progressDialog?.value = true
            WebServiceClient.client.create(BackEndApi::class.java).LOGIN(
                email = email?.get()!!
                , password = password?.get()!!
            )
                .enqueue(this)
        }

    }



    override fun onResponse(call: Call<User>?, response: Response<User>?) {
        progressDialog?.value = false
        userLogin?.value = response?.body()

    }

    override fun onFailure(call: Call<User>?, t: Throwable?) {
        progressDialog?.value = false

    }


}