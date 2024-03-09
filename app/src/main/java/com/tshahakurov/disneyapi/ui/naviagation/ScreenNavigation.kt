package com.tshahakurov.disneyapi.ui.naviagation

import android.util.Log
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.tshahakurov.disneyapi.R
import com.tshahakurov.disneyapi.ui.screen.hero.HeroDetailScreen
import com.tshahakurov.disneyapi.ui.screen.list.ListOfHeroesScreen
import com.tshahakurov.disneyapi.ui.screen.onboarding.OnboardingScreen
import com.tshahakurov.disneyapi.ui.screen.profile.ProfileScreen
import com.tshahakurov.disneyapi.ui.screen.registration.LoginScreen
import com.tshahakurov.disneyapi.ui.screen.registration.SignupScreen

enum class DisneyScreen(@StringRes val route: Int) {
    Onboarding(route = R.string.onboarding_screen),
    HeroesList(route = R.string.hero_list_screen),
    HeroDetail(route = R.string.hero_detail_screen),
    LogIn(route = R.string.login_screen),
    SignUp(route = R.string.signup_screen),
    Profile(route = R.string.profile)
}

@Composable
fun DisneyScreen(
    viewModel: ScreenViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val context = LocalContext.current


    NavHost(
        navController = navController,
        startDestination =
            if (Firebase.auth.currentUser == null) DisneyScreen.Onboarding.name
            else DisneyScreen.HeroesList.name,
        enterTransition = { fadeIn() + slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left) },
        exitTransition = { fadeOut(tween(500)) },
        popEnterTransition = { fadeIn() },
        popExitTransition = { fadeOut() + slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right) }
    ) {

        //   ( -_•)▄︻デ══━一 - - - - - - - - - - - > (Onboarding) - - - > (ó﹏ò｡)
        composable(route = DisneyScreen.Onboarding.name) {
            OnboardingScreen(onButtonClick = { navController.navigate(DisneyScreen.LogIn.name) })
        }

        //   ( -_•)▄︻デ══━一 - - - - - - - - - - - > (Hero List) - - - > (ó﹏ò｡)
        composable(route = DisneyScreen.HeroesList.name) {
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
                onAllClickedAction = {},
                onFavoriteClickedAction = { throw RuntimeException("Test Crash") },
                onProfileClickedAction = { navController.navigate(DisneyScreen.Profile.name) }
            )
        }

        //   ( -_•)▄︻デ══━一 - - - - - - - - - - - > (Hero Detail) - - - > (ó﹏ò｡)
        composable(route = DisneyScreen.HeroDetail.name) {
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

        //   ( -_•)▄︻デ══━一 - - - - - - - - - - - > (Login) - - - > (ó﹏ò｡)
        composable(route = DisneyScreen.LogIn.name) {
            val isLoading by viewModel.isLoading.observeAsState(false)
            LoginScreen(
                isLoading = isLoading,
                onLoginButtonClicked = { email, password ->
                    viewModel.validateLogin(email, password) { task ->
                        if (task.isSuccessful) {
                            navController.popBackStack(DisneyScreen.Onboarding.name, true)
                            navController.navigate(DisneyScreen.HeroesList.name)
                            Toast.makeText(context, "Logged In Successfully", Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }

                },
                onSignupButtonClicked = { navController.navigate(DisneyScreen.SignUp.name) },
                onGoogleLoginButtonClicked = {
                    // TODO ( GOOGLE EJI EJI )
                }
            )
        }

        //   ( -_•)▄︻デ══━一 - - - - - - - - - - - > (Signup) - - - > (ó﹏ò｡)
        composable(route = DisneyScreen.SignUp.name) {
            val isLoading by viewModel.isLoading.observeAsState(false)

            SignupScreen(
                isLoading = isLoading,
                onSignupClick = { email, password, confPassword ->
                    viewModel.validateSignup(
                        email = email,
                        password = password,
                        confPassword = confPassword
                    ) { task ->
                        if (task.isSuccessful) {
                            navController.popBackStack(DisneyScreen.Onboarding.name, true)
                            navController.navigate(DisneyScreen.HeroesList.name)
                            Toast.makeText(context, "Signed up successfully", Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            Log.d("suita", "${task.exception?.message}")
                            Toast.makeText(context, "Something went wrong!", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                },
                onLoginClick = { navController.popBackStack() },
                onGoogleSignupClick = {}
            )
        }

        //   ( -_•)▄︻デ══━一 - - - - - - - - - - - > (Profile) - - - > (ó﹏ò｡)
        composable(route = DisneyScreen.Profile.name) {
            ProfileScreen(
                onBackClick = { navController.popBackStack() },
                onLogoutClick = {
                    Firebase.auth.signOut()
                    navController.popBackStack(DisneyScreen.HeroesList.name, true)
                    navController.navigate(DisneyScreen.Onboarding.name)
                }
            )
        }
    }
}