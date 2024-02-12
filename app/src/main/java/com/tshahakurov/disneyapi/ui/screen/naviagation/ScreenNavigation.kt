package com.tshahakurov.disneyapi.ui.screen.naviagation

import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tshahakurov.disneyapi.R
import com.tshahakurov.disneyapi.model.Hero
import com.tshahakurov.disneyapi.ui.screen.hero.HeroDetailScreen
import com.tshahakurov.disneyapi.ui.screen.list.ListOfHeroesScreen
import com.tshahakurov.disneyapi.ui.screen.onboarding.OnboardingScreen

enum class DisneyScreen(@StringRes val route: Int) {
    Onboarding(route = R.string.onboarding_screen),
    HeroesList(route = R.string.hero_list_screen),
    HeroDetail(route = R.string.hero_detail_screen),
}

@Composable
fun DisneyScreen(
    viewModel: ScreenViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = DisneyScreen.Onboarding.name
    ) {
        //   ( -_•)▄︻デ══━一  Screen 1      (ó﹏ò｡)
        composable(
            route = DisneyScreen.Onboarding.name
        ) {
            OnboardingScreen(
                onButtonClick = {
                    navController.navigate(DisneyScreen.HeroesList.name)
                }
            )
        }
        //   ( -_•)▄︻デ══━一  Screen 2       (ó﹏ò｡)
        composable(
            route = DisneyScreen.HeroesList.name
        ) {
            viewModel.getHeroList()
            val heroList by viewModel.heroList.observeAsState(initial = arrayListOf())
            val isLoading by viewModel.isLoading.observeAsState(true)

            ListOfHeroesScreen(
                list = heroList,
                isLoading = isLoading,
                onItemClickAction = { id ->
                    viewModel.setCurrentHero(id)
                    navController.navigate(DisneyScreen.HeroDetail.name)
                },
            )
        }
        //   ( -_•)▄︻デ══━一  Screen 3       (ó﹏ò｡)
        composable(
            route = DisneyScreen.HeroDetail.name
        ) {
            val isLoading by viewModel.isLoading.observeAsState(true)

            HeroDetailScreen(
                hero = viewModel.currentHero.value,
                isLoading = isLoading,
                onBackPressedAction = {
                    navController.popBackStack()
                    viewModel.removeCurrentHero()
                },
                onFavoritePressedAction = {},
            )
        }
    }
}