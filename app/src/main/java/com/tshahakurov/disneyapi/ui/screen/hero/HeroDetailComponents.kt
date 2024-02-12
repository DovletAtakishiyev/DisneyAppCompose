package com.tshahakurov.disneyapi.ui.screen.hero

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.tshahakurov.disneyapi.R
import com.tshahakurov.disneyapi.model.Hero
import com.tshahakurov.disneyapi.ui.theme.ButtonBackgroundUnselected
import com.tshahakurov.disneyapi.ui.theme.DarkViolet


@Composable
fun Header(
    hero: Hero?,
    onBackPressedAction: () -> Unit = {},
    onFavoritePressedAction: () -> Unit = {},
) {
    if (hero != null)
        Box(modifier = Modifier.fillMaxWidth()) {
            AsyncImage(
                model = if (hero.imageUrl.isNullOrBlank())
                    "https://pbs.twimg.com/media/FQS_sdJXwAQc5Vb.jpg"
                else
                    hero.imageUrl,
                contentDescription = "Hero Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(300.dp)
                    .clip(
                        RoundedCornerShape(
                            bottomEnd = dimensionResource(R.dimen.slight_round_corner),
                            bottomStart = dimensionResource(R.dimen.slight_round_corner)
                        )
                    )
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = dimensionResource(R.dimen.padding_large),
                        start = dimensionResource(R.dimen.padding_medium),
                        end = dimensionResource(R.dimen.padding_medium)
                    ),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(Color.White)
                        .alpha(0.8f),
                    onClick = { onBackPressedAction() }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        tint = Color.Black,
                        contentDescription = "Button Back"
                    )
                }
                IconButton(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(ButtonBackgroundUnselected)
                        .alpha(0.8f),
                    onClick = { onFavoritePressedAction() }
                ) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        tint = Color.White,
                        contentDescription = "Button Back"
                    )
                }
            }
        }
}

@Composable
fun Body(hero: Hero?) {
    if (hero != null)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = dimensionResource(R.dimen.padding_medium))
        ) {
            Column {
                Text(
                    text = hero.name,
                    style = TextStyle(
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace,
                        fontSize = with(LocalDensity.current) {
                            LocalContext.current.resources.getDimensionPixelSize(R.dimen.big_text_size)
                                .toSp()
                        })
                )
                Spacer(modifier = Modifier.size(dimensionResource(R.dimen.padding_medium)))
                Column {
                    hero.let {
                        for (characteristic in it.characteristics) {
                            CharacteristicElement(characteristic)
                        }
                    }
                }
            }

        }
}

@Composable
fun CharacteristicElement(
    element: Pair<String, ArrayList<String>>
) {
    if (element.second.isNotEmpty())
        Column(Modifier.fillMaxWidth()) {
            Text(
                text = element.first,
                style = TextStyle(
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace,
                    fontSize = with(LocalDensity.current) {
                        LocalContext.current.resources.getDimensionPixelSize(R.dimen.header_text_size)
                            .toSp()
                    }
                )
            )
            Column(
            ) {
                for (item in element.second) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = dimensionResource(R.dimen.padding_medium),
                                vertical = dimensionResource(R.dimen.padding_tiny),
                            )
                            .clip(RoundedCornerShape(dimensionResource(R.dimen.very_slight_round_corner)))
                            .background(DarkViolet)
                    ) {
                        Text(
                            text = item,
                            modifier = Modifier
                                .padding(
                                    horizontal = dimensionResource(R.dimen.padding_small),
                                    vertical = dimensionResource(R.dimen.padding_tiny),
                                ),
                            style = TextStyle(
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.Monospace,
                                fontSize = with(LocalDensity.current) {
                                    LocalContext.current.resources.getDimensionPixelSize(R.dimen.medium_text_size)
                                        .toSp()
                                }
                            )
                        )
                    }
                }
            }
        }
}