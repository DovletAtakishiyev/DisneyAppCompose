package com.tshahakurov.disneyapi.ui.screen.hero

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.tshahakurov.disneyapi.R
import com.tshahakurov.disneyapi.db.DB
import com.tshahakurov.disneyapi.model.Hero
import com.tshahakurov.disneyapi.ui.screen.util.LoadingProcessBar
import com.tshahakurov.disneyapi.ui.theme.ScreenBackground

@Composable
fun HeroDetailScreen(
    hero: Hero? = DB.defHero,
    isLoading: Boolean = false,
    onBackPressedAction: () -> Unit = {},
    onFavoritePressedAction: () -> Unit = {}
) {
    if (isLoading) {
        LoadingProcessBar()
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(ScreenBackground)
        ) {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {
                Header(
                    hero = hero,
                    onBackPressedAction = onBackPressedAction,
                    onFavoritePressedAction = onFavoritePressedAction
                )
                Spacer(modifier = Modifier.size(dimensionResource(R.dimen.padding_medium)))
                Body(hero = hero)
            }
        }
    }


    BackHandler {
        onBackPressedAction()
    }
}


@Preview(showBackground = true)
@Composable
fun HeroDetailScreenPreview() {
    HeroDetailScreen()
}
