package com.tshahakurov.disneyapi.ui.naviagation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.tasks.Task
import com.google.firebase.Firebase
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.auth
import com.tshahakurov.disneyapi.model.Hero
import com.tshahakurov.disneyapi.repository.HeroRepository
import com.tshahakurov.disneyapi.util.isValidEmail
import com.tshahakurov.disneyapi.util.toHero
import com.tshahakurov.disneyapi.util.toHeroList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScreenViewModel @Inject constructor(
    private val repository: HeroRepository
) : ViewModel() {

    var currentHero = MutableLiveData<Hero?>()
    var isLoading = MutableLiveData(false)
    val heroList = MutableLiveData<ArrayList<Hero>>()

    fun getHeroList() {
        viewModelScope.launch(Dispatchers.IO) {
            isLoading.postValue(true)
            val response = repository.getHeroList()
            if (response.isSuccessful)
                heroList.postValue(response.body()?.toHeroList())
            isLoading.postValue(false)
        }
    }

    fun setCurrentHero(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            isLoading.postValue(true)
            val response = repository.findHeroById(id)
            if (response.isSuccessful)
                currentHero.postValue(response.body()?.toHero())
            isLoading.postValue(true)
        }
    }

    fun removeCurrentHero() {
        currentHero.value = null
    }

    //----------------------- Registration Logic -----------------------//
    fun validateLogin(
        email: String,
        password: String,
        onResponseListener: (Task<AuthResult>) -> Unit,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
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

    }

    fun validateSignup(
        email: String,
        password: String,
        confPassword: String,
        onResponseListener: (Task<AuthResult>) -> Unit,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
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
    fun validateGoogleLogin() {}
    fun validateGoogleSignup() {}
}