package com.tshahakurov.disneyapi.ui.screen.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.tshahakurov.disneyapi.R
import com.tshahakurov.disneyapi.ui.theme.ScreenBackground

@Composable
fun OnboardingScreen(
    onButtonClick: ()->Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ScreenBackground),
        contentAlignment = Alignment.Center
    ){
        Column (
            modifier = Modifier.fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ){
            Text(
                text = stringResource(id = R.string.welcome_banner),
                style = getOnboardingBannerStyle()
            )
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Onboarding Image",
                modifier = Modifier.size(dimensionResource(id = R.dimen.image_onboarding))
            )
            Button(onClick = { onButtonClick() }) {
                Text(
                    text = stringResource(id = R.string.get_started).uppercase(),
                    style = getOnboardingBannerStyle()
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingScreenPreview() {
    OnboardingScreen()
}

@Composable
private fun getOnboardingBannerStyle(): TextStyle =
    TextStyle(
        color = Color.White,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.Monospace,
        fontSize = with(LocalDensity.current) {
            LocalContext.current.resources.getDimensionPixelSize(R.dimen.header_text_size).toSp()
        }
    )