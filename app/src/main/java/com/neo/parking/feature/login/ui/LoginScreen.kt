package com.neo.parking.feature.login.ui

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.neo.parking.R
import com.neo.parking.R.color
import com.neo.parking.feature.login.vm.Event
import com.neo.parking.feature.login.vm.LoginViewModel
import com.neo.parking.feature.login.vm.UiIntent
import com.neo.parking.feature.parking.ui.ParkingActivity
import kotlin.math.log


@Composable
fun loginScreen(
    loginViewModel: LoginViewModel = hiltViewModel(),
    navigateRegister: () -> Unit,
    navigateForgotPassword: (String?) -> Unit,
) {
    val state by loginViewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(true) {
        loginViewModel.sideEffect.collect {
            when (it) {
                is Event.ONLOGGED -> {
                    context.startActivity(
                        Intent(context, ParkingActivity::class.java).addFlags(
                            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        )
                    )

                }
            }
        }
    }
    LaunchedEffect(true) {
        loginViewModel.sendIntent(UiIntent.CheckLoggedIntent)
    }

    Box(Modifier.fillMaxSize()) {
        Column(Modifier.fillMaxSize()) {
            loginHeader()

            Spacer(Modifier.height(20.dp))

            bodyLoginFields(
                state.email,
                { loginViewModel.sendIntent(UiIntent.EmailUpdateIntent(it)) },
                state.password,
                { loginViewModel.sendIntent(UiIntent.PasswordUpdateIntent(it)) },
                state.rememberLogin,
                { loginViewModel.sendIntent(UiIntent.UpdateRememberIntent) },
                { navigateForgotPassword(state.email) },
                { loginViewModel.sendIntent(UiIntent.DoLoginIntent) }
            )
            Spacer(modifier = Modifier.weight(1f))
            footerLogin({ navigateRegister() }
                //todo navear a crar cuenta
            )
        }
    }

}

@Composable
fun footerLogin(createAccount: () -> Unit) {
    Card(
        Modifier
            .fillMaxWidth()
            .height(60.dp),
        shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(color.grey)
        ),
    ) {
        Row(
            Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.footer_text),
                color = colorResource(color.black),
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = stringResource(R.string.create_account_text),
                color = colorResource(color.blue_theme),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.clickable {
                    createAccount()
                }
            )
        }
    }
}

@Composable
fun bodyLoginFields(
    mail: String,
    changeValue: (String) -> Unit,
    password: String,
    changeValuePassword: (String) -> Unit,
    rememberMe: Boolean,
    changeRemember: () -> Unit,
    forgetPassClick: () -> Unit,
    initSession: () -> Unit
) {
    var passwordVisible by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
    ) {

        Text(
            text = stringResource(com.neo.parking.R.string.login_address_text_field),
            color = colorResource(color.black),
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = mail,
            onValueChange = { changeValue(it) },
            placeholder = { Text("Enter your mail") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = Color.LightGray,
                focusedContainerColor = Color(0xFFF6F7FB),
                unfocusedContainerColor = Color(0xFFF6F7FB),
                cursorColor = MaterialTheme.colorScheme.primary
            ),
            leadingIcon = {
                Icon(
                    painterResource(R.drawable.mail_icon),
                    contentDescription = "mailFieldIcon"
                )
            }
        )
        Spacer(Modifier.height(20.dp))
        Text(
            text = stringResource(R.string.login_password_field),
            color = colorResource(color.black),
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { changeValuePassword(it) },
            placeholder = { Text(stringResource(R.string.password_field_text)) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            visualTransformation = if (passwordVisible) {
                VisualTransformation.None
            } else PasswordVisualTransformation(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = Color.LightGray,
                focusedContainerColor = Color(0xFFF6F7FB),
                unfocusedContainerColor = Color(0xFFF6F7FB),
                cursorColor = MaterialTheme.colorScheme.primary
            ),
            leadingIcon = {
                Icon(
                    painterResource(R.drawable.baseline_lock_outline_24),
                    contentDescription = "mailFieldIcon"
                )
            }, trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        painter = painterResource(R.drawable.password_visible),
                        contentDescription = "Toggle password visibility"
                    )
                }
            }
        )
        Spacer(Modifier.height(20.dp))

        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Checkbox(rememberMe, onCheckedChange = {
                changeRemember()
            })
            Text(
                text = stringResource(R.string.remember_me_text),
                color = colorResource(color.black),
                style = MaterialTheme.typography.labelMedium
            )
            Spacer(Modifier.weight(1f))

            Text(
                text = stringResource(R.string.forgot_password_text),
                color = colorResource(color.blue_theme),
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier
                    .padding(start = 4.dp)
                    .clickable {
                        forgetPassClick()
                    }
            )
        }
        Spacer(Modifier.height(20.dp))

        Button(
            onClick = { initSession() }, modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.blue_theme),
                contentColor = Color.White
            )
        ) {
            Text(text = stringResource(R.string.sign_in_text))
        }

    }
}

@Composable
fun loginHeader() {
    Card(
        Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(color.blue_theme)
        )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(Modifier.height(20.dp))
            Box(
                modifier = Modifier
                    .size(70.dp)
                    .clip(CircleShape)
                    .background(colorResource(id = color.white)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(com.neo.parking.R.drawable.ic_phone_android),
                    contentDescription = "PhoneLogging",
                    tint = colorResource(id = color.blue_theme),
                    modifier = Modifier.size(50.dp)
                )
            }

            Spacer(Modifier.height(20.dp))

            Text(
                text = stringResource(com.neo.parking.R.string.login_title),
                color = colorResource(color.white),
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(Modifier.height(10.dp))
            Text(
                text = stringResource(com.neo.parking.R.string.login_subtitle),
                color = colorResource(color.white),
                style = MaterialTheme.typography.titleSmall
            )
            Spacer(Modifier.height(20.dp))
        }
    }
}

@Composable
@Preview(showBackground = true)
fun loginPreview() {

}