package com.tshahakurov.disneyapi.ui.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.tshahakurov.disneyapi.R
import com.tshahakurov.disneyapi.ui.screen.util.SpaceBox
import com.tshahakurov.disneyapi.ui.screen.util.getAppTextStyle
import com.tshahakurov.disneyapi.ui.theme.ScreenBackground

@Composable
fun ProfileScreen(
    onBackClick: () -> Unit = {},
    onLogoutClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ScreenBackground)
            .padding(dimensionResource(id = R.dimen.padding_large)),
        contentAlignment = Alignment.TopCenter
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            IconButton(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color.White)
                    .alpha(0.8f),
                onClick = { onBackClick() }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    tint = Color.Black,
                    contentDescription = "Button Back"
                )
            }
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.def_icon),
                contentDescription = "Profile Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.image_onboarding))
                    .clip(CircleShape)
                    .background(Color.LightGray)
                    .padding(4.dp)
                    .clip(CircleShape)
            )

            SpaceBox(24)
            Text(
                text = Firebase.auth.currentUser?.email ?: "suita",
                style = getAppTextStyle(R.dimen.header_text_size))
            SpaceBox(24)
            Button(onClick = { onLogoutClick() }) {
                Text(
                    text = stringResource(id = R.string.log_out_delete),
                    style = getAppTextStyle(fontSize = R.dimen.medium_text_size)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen()
}