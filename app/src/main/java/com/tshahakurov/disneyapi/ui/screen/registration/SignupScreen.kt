package com.tshahakurov.disneyapi.ui.screen.registration

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.tshahakurov.disneyapi.R
import com.tshahakurov.disneyapi.ui.screen.util.LoadingProcessBar
import com.tshahakurov.disneyapi.ui.screen.util.SpaceBox
import com.tshahakurov.disneyapi.ui.screen.util.getAppTextStyle
import com.tshahakurov.disneyapi.ui.theme.ScreenBackground

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupScreen(
    isLoading: Boolean = false,
    onSignupClick: (String, String, String) -> Unit = { _, _, _ -> },
    onLoginClick: () -> Unit = {},
    onGoogleSignupClick: () -> Unit = {},
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ScreenBackground),
        contentAlignment = Alignment.Center
    ) {
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var confirmPassword by remember { mutableStateOf("") }
        val spaceSize = 32

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.signup),
                style = getAppTextStyle(fontSize = R.dimen.header_text_size)
            )
            SpaceBox(size = spaceSize)
            TextField(
                value = email,
                label = { Text(stringResource(id = R.string.email)) },
                singleLine = true,
                onValueChange = { email = it.trim() }
            )
            SpaceBox(size = spaceSize)
            TextField(
                value = password,
                label = { Text(stringResource(id = R.string.password)) },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                onValueChange = { password = it.trim() }
            )
            SpaceBox(size = spaceSize)
            TextField(
                value = confirmPassword,
                label = { Text(stringResource(id = R.string.confirm_password)) },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                onValueChange = { confirmPassword = it.trim() }
            )
            SpaceBox(size = spaceSize)
            Button(onClick = {
                onSignupClick(email, password, confirmPassword)
            }) {
                Text(
                    text = stringResource(id = R.string.signup),
                    style = getAppTextStyle(fontSize = R.dimen.medium_text_size)
                )
            }
            SpaceBox(size = spaceSize)
            Text(
                text = stringResource(id = R.string.login_option),
                style = getAppTextStyle(fontSize = R.dimen.small_text_size),
                modifier = Modifier
                    .alpha(0.7f)
                    .clickable {
                        onLoginClick()
                    }
            )
            SpaceBox(size = spaceSize)
            Text(
                text = stringResource(id = R.string.or),
                style = getAppTextStyle(fontSize = R.dimen.small_text_size)
            )
            SpaceBox(size = spaceSize)
            Button(onClick = {
                onGoogleSignupClick()
            }) {
                Text(
                    text = stringResource(id = R.string.google_signup),
                    style = getAppTextStyle(fontSize = R.dimen.medium_text_size)
                )

            }
        }
        if (isLoading) {
            LoadingProcessBar(0.5f, ScreenBackground)
        }
    }
}

@Preview(showBackground = true, widthDp = 450)
@Composable
fun SignupScreenPreview() {
    SignupScreen()
}