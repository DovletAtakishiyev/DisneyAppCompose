package com.tshahakurov.disneyapi.logic.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.Task
import com.google.firebase.Firebase
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.auth
import com.tshahakurov.disneyapi.util.isValidEmail
import javax.inject.Inject

class AuthRepository @Inject constructor() {

    fun signUp(
        email: String,
        password: String,
        onResponseListener: (Task<AuthResult>) -> Unit,
        isLoading: MutableLiveData<Boolean>
    ) {
        isLoading.postValue(true)
        if (isValidEmail(email) && password.length > 5) {
            Firebase.auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    onResponseListener(it)
                    isLoading.postValue(false)
                }
        } else {
            isLoading.postValue(false)
        }
    }

    fun logIn(
        email: String,
        password: String,
        confPassword: String,
        onResponseListener: (Task<AuthResult>) -> Unit,
        isLoading: MutableLiveData<Boolean>
    ) {
        Log.d("suita", "click")
        isLoading.postValue(true)
        if (isValidEmail(email) && password.length > 5 && password == confPassword) {
            Firebase.auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    onResponseListener(it)
                    isLoading.postValue(false)
                }
        } else {
            isLoading.postValue(false)
        }
    }

}