package com.tshahakurov.disneyapi.ui.screen.naviagation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tshahakurov.disneyapi.db.DB
import com.tshahakurov.disneyapi.model.Hero
import com.tshahakurov.disneyapi.repository.HeroRepository
import com.tshahakurov.disneyapi.util.toHero
import com.tshahakurov.disneyapi.util.toHeroList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScreenViewModel @Inject constructor(
    private val repository: HeroRepository
) : ViewModel() {

    var currentHero = MutableLiveData<Hero?>()
    var isLoading = MutableLiveData(false)
    val heroList = MutableLiveData<ArrayList<Hero>>()

    fun getHeroList(){
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

    fun removeCurrentHero(){
        currentHero.value = null
    }
}