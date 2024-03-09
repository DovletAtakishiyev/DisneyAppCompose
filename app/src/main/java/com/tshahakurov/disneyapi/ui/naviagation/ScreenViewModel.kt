package com.tshahakurov.disneyapi.ui.naviagation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.tshahakurov.disneyapi.logic.model.Hero
import com.tshahakurov.disneyapi.logic.repository.AuthRepository
import com.tshahakurov.disneyapi.logic.repository.HeroRepository
import com.tshahakurov.disneyapi.util.toHero
import com.tshahakurov.disneyapi.util.toHeroList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScreenViewModel @Inject constructor(
    private val heroRepo: HeroRepository,
    private val authRepo: AuthRepository
) : ViewModel() {

    var currentHero = MutableLiveData<Hero?>()
    var isLoading = MutableLiveData(false)
    val heroList = MutableLiveData<ArrayList<Hero>>()

    fun getHeroList() {
        viewModelScope.launch(Dispatchers.IO) {
            isLoading.postValue(true)
            val response = heroRepo.getHeroList()
            if (response.isSuccessful)
                heroList.postValue(response.body()?.toHeroList())
            isLoading.postValue(false)
        }
    }

    fun setCurrentHero(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            isLoading.postValue(true)
            val response = heroRepo.findHeroById(id)
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
        authRepo.signUp(email, password, onResponseListener, isLoading)
    }

    fun validateSignup(
        email: String,
        password: String,
        confPassword: String,
        onResponseListener: (Task<AuthResult>) -> Unit,
    ) {
        authRepo.logIn(email, password, confPassword, onResponseListener, isLoading)
    }
    fun validateGoogleLogin() {}
    fun validateGoogleSignup() {}
}