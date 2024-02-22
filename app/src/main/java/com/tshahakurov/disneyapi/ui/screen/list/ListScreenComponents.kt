package com.tshahakurov.disneyapi.ui.screen.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import coil.compose.AsyncImage
import com.tshahakurov.disneyapi.R
import com.tshahakurov.disneyapi.model.Hero
import com.tshahakurov.disneyapi.ui.theme.ButtonBackgroundSelected
import com.tshahakurov.disneyapi.ui.theme.ButtonBackgroundUnselected

@Composable
fun ListScreenButtonsBlock(
    onAllClickAction: () -> Unit = {},
    onFavoriteClickAction: () -> Unit = {},
    onProfileClickAction: () -> Unit = {},
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row{
            UserCustomButton(
                Icons.Default.Home,
                text = R.string.all,
                selected = true,
                onClickAction = onAllClickAction
            )
            Spacer(Modifier.size(dimensionResource(R.dimen.padding_medium)))
            UserCustomButton(
                Icons.Default.FavoriteBorder,
                text = R.string.favorite,
                selected = false,
                onClickAction = onFavoriteClickAction
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painterResource(id = R.drawable.def_icon),
                contentDescription = null,
                contentScale = ContentScale.Crop ,
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.button_small))
                    .clip(CircleShape)
                    .clickable { onProfileClickAction() }
            )
            Spacer(modifier = Modifier.size(dimensionResource(R.dimen.padding_small)))
            Text(
                text = stringResource(R.string.profile),
                color = Color.White,
                fontSize = with(LocalDensity.current) {
                    LocalContext.current.resources.getDimensionPixelSize(R.dimen.small_text_size).toSp()
                },
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace
            )
        }
    }
}

@Composable
fun UserCustomButton(
    icon: ImageVector,
    text: Int,
    selected: Boolean = false,
    onClickAction: () -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .size(dimensionResource(R.dimen.button_small))
                .clip(RoundedCornerShape(dimensionResource(R.dimen.slight_round_corner)))
                .background(
                    if (selected) ButtonBackgroundSelected
                    else ButtonBackgroundUnselected
                )
                .clickable { onClickAction.invoke() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = stringResource(R.string.all_heroes_list_button),
                tint = Color.White,
                modifier = Modifier.size(dimensionResource(R.dimen.button_icon_size))
            )
        }

        Spacer(modifier = Modifier.size(dimensionResource(R.dimen.padding_small)))

        Text(
            text = stringResource(text),
            color = Color.White,
            fontSize = with(LocalDensity.current) {
                LocalContext.current.resources.getDimensionPixelSize(R.dimen.small_text_size).toSp()
            },
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace
        )
    }
}


@Composable
fun HeroesLazyGrid(
    list: ArrayList<Hero>,
    onItemClickAction: (id:Int) -> Unit = {}
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(
            minSize = dimensionResource(R.dimen.image_grid_2)
//            count = 2
        )
    ) {
        items(list) {
            GridElement(item = it, onItemClickAction = onItemClickAction)
        }
    }

}

@Composable
fun GridElement(
    item: Hero,
    onItemClickAction: (id: Int) -> Unit = {}
) {
    Box(contentAlignment = Alignment.Center) {
        Column {
            AsyncImage(
                model = if (item.imageUrl.isNullOrBlank())
                    "https://pbs.twimg.com/media/FQS_sdJXwAQc5Vb.jpg"
                else
                "${item.imageUrl}",
                contentDescription = "Hero Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(dimensionResource(R.dimen.image_grid_2))
                    .clip(RoundedCornerShape(dimensionResource(R.dimen.very_slight_round_corner)))
                    .clickable { onItemClickAction(item.id) }
            )

            Spacer(modifier = Modifier.size(dimensionResource(R.dimen.padding_small)))
            Text(
                text = item.name,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace,
                fontSize = with(LocalDensity.current) {
                    LocalContext.current.resources.getDimensionPixelSize(R.dimen.medium_text_size)
                        .toSp()
                },
                maxLines = 1,
                modifier = Modifier
                    .clickable { onItemClickAction(item.id) }
                    .requiredWidth(dimensionResource(R.dimen.image_grid_2))
            )
            Spacer(modifier = Modifier.size(dimensionResource(R.dimen.padding_medium)))
        }
    }
}


