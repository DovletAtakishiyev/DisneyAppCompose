package com.tshahakurov.disneyapi.ui.screen.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.tshahakurov.disneyapi.R
import com.tshahakurov.disneyapi.model.Hero
import com.tshahakurov.disneyapi.ui.screen.util.LoadingProcessBar
import com.tshahakurov.disneyapi.ui.theme.ScreenBackground

@Composable
fun ListOfHeroesScreen(
    list: ArrayList<Hero> = arrayListOf(),
    isLoading: Boolean = true,
    onItemClickAction: (id: Int) -> Unit = {},
    onAllClickedAction: () -> Unit = {},
    onFavoriteClickedAction: () -> Unit = {},
    onProfileClickedAction: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ScreenBackground)
            .padding(dimensionResource(R.dimen.padding_medium)),
    ) {
        Column {
            ListScreenButtonsBlock(
                onAllClickAction = onAllClickedAction,
                onFavoriteClickAction = onFavoriteClickedAction,
                onProfileClickAction = onProfileClickedAction,
            )
            Spacer(modifier = Modifier.size(dimensionResource(R.dimen.padding_medium)))
            if (isLoading) {
                LoadingProcessBar()
            } else {
                HeroesLazyGrid(
                    list = list,
                    onItemClickAction = onItemClickAction
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ListOfHeroesScreenPreview() {
    ListOfHeroesScreen()
}