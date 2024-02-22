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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.tshahakurov.disneyapi.R
import com.tshahakurov.disneyapi.ui.screen.util.getAppTextStyle
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
                style = getAppTextStyle(fontSize = R.dimen.header_text_size)
            )
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Onboarding Image",
                modifier = Modifier.size(dimensionResource(id = R.dimen.image_onboarding))
            )
            Button(onClick = { onButtonClick() }) {
                Text(
                    text = stringResource(id = R.string.get_started).uppercase(),
                    style = getAppTextStyle(fontSize = R.dimen.medium_text_size)
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