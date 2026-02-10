package com.neo.parking.feature.Register.ui

import androidx.compose.foundation.background
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.neo.core.utils.containsCapLetterAndNonCap
import com.neo.core.utils.containsNumber
import com.neo.parking.R
import com.neo.parking.R.color
import com.neo.parking.feature.Register.vm.Event
import com.neo.parking.feature.Register.vm.RegisterViewModel
import com.neo.parking.feature.Register.vm.UIIntent
import com.neo.parking.feature.Register.vm.UIState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterRouteScreen(
    registerViewModel: RegisterViewModel = hiltViewModel(),
    onBack: () -> Unit
) {
    val state by registerViewModel.state.collectAsStateWithLifecycle()
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showBottomSheet by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        registerViewModel.sideEffect.collect { event ->
            when (event) {
                is Event.UserCreated -> {
                    showBottomSheet = true
                }

            }
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        RegisterScreen(
            state,
            { registerViewModel.sendIntent(UIIntent.CreateAccountIntent) },
            { value: String ->
                registerViewModel.sendIntent(
                    UIIntent.UpdateMailIntent(value)
                )
            },

            { value: String ->
                registerViewModel.sendIntent(
                    UIIntent.UpdateNameIntent(value)
                )
            }, { value: String ->
                registerViewModel.sendIntent(
                    UIIntent.UpdateMailConfirmationIntent(value)
                )
            },
            { value: String ->
                registerViewModel.sendIntent(
                    UIIntent.UpdatePasswordIntent(value)
                )
            }
        )
    }
    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { onBack() },
            sheetState = sheetState, containerColor = colorResource(R.color.blue_theme)
        ) {

            UserCreatedBottomDialog(onBack, {showBottomSheet = false})
        }
    }

}

@Composable
fun UserCreatedBottomDialog(onBack: () -> Unit, hideBottomSheet: () -> Unit) {
    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {

        Spacer(Modifier.height(20.dp))
        Box(
            modifier = Modifier
                .size(70.dp)
                .clip(CircleShape)
                .background(Color.White),
            contentAlignment = Alignment.Center
        )
        {
            Icon(
                painterResource(R.drawable.created_user_icon),
                contentDescription = "Creater_user_dialog_icon",
                tint = colorResource(R.color.blue_theme),
                modifier = Modifier.size(50.dp),
            )
        }
        Spacer(Modifier.height(10.dp))

        Text(
            "Usuario creado con exito! Bienvenido",
            style = MaterialTheme.typography.titleLarge,
            color = Color.White
        )
        Spacer(Modifier.height(10.dp))


        Button(
            modifier = Modifier.padding(horizontal = 12.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = colorResource(R.color.blue_theme),
                containerColor = Color.White
            ),
            onClick = { hideBottomSheet()
                onBack () }
        ) {
            Text(stringResource(R.string.register_dialog_boton))
        }

        Spacer(Modifier.height(10.dp))

    }

}


@Composable
fun RegisterScreen(
    value: UIState,
    createAccount: () -> Unit,
    updateEmail: (String) -> Unit,
    updateName: (String) -> Unit,
    updateEmailConfirmation: (String) -> Unit,
    updatePassWord: (String) -> Unit,
) {

    Column(Modifier.fillMaxSize()) {
        RegisterHeader()
        RegisterBody(
            value.mail,
            value.mailConfirmation,
            value.name,
            value.pass,
            createAccount,
            updateEmail,
            updateName,
            updateEmailConfirmation,
            updatePassWord
        )

    }
}

@Composable
fun RegisterHeader() {
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
                    painter = painterResource(com.neo.parking.R.drawable.baseline_electric_bolt_24),
                    contentDescription = "CreateLoggin",
                    tint = colorResource(id = color.blue_theme),
                    modifier = Modifier.size(50.dp)
                )
            }

            Spacer(Modifier.height(20.dp))

            Text(
                text = stringResource(R.string.register_title),
                color = colorResource(color.white),
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(Modifier.height(10.dp))
            Text(
                text = stringResource(R.string.register_subtitle),
                color = colorResource(color.white),
                style = MaterialTheme.typography.titleSmall
            )
            Spacer(Modifier.height(20.dp))
        }
    }
}

@Composable
fun RegisterBody(
    email: String,
    emailConfirmation: String,
    name: String,
    password: String,
    createAccount: () -> Unit,
    updateEmail: (String) -> Unit,
    updateName: (String) -> Unit,
    updateEmailConfirmation: (String) -> Unit,
    updatePassWord: (String) -> Unit,
) {

    var hidePass by remember { mutableStateOf(true) }
    val passContainsCapAndNonCap = password.containsCapLetterAndNonCap()
    val passContainsNumber = password.containsNumber()
    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
    ) {
        Spacer(Modifier.height(20.dp))
        Text(
            stringResource(R.string.name_register_title),
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(Modifier.height(4.dp))
        OutlinedTextField(
            name,
            onValueChange = { updateName(it) },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .testTag("name_register"),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = Color.LightGray,
                focusedContainerColor = Color(0xFFF6F7FB),
                unfocusedContainerColor = Color(0xFFF6F7FB),
                cursorColor = MaterialTheme.colorScheme.primary
            ),
            singleLine = true,
            placeholder = { Text(text = "Introduce tu nombre") },
            leadingIcon = {
                Icon(
                    painterResource(R.drawable.name_icon),
                    contentDescription = "mailFieldIcon"
                )
            }
        )
        Spacer(Modifier.height(20.dp))
        Text(
            stringResource(R.string.title_register_mail_title),
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(Modifier.height(4.dp))
        OutlinedTextField(
            email,
            onValueChange = { updateEmail(it) },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .testTag("mail_register"),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = Color.LightGray,
                focusedContainerColor = Color(0xFFF6F7FB),
                unfocusedContainerColor = Color(0xFFF6F7FB),
                cursorColor = MaterialTheme.colorScheme.primary
            ),
            singleLine = true,
            placeholder = { Text(text = stringResource(R.string.Register_email_placeholder)) },
            leadingIcon = {
                Icon(
                    painterResource(R.drawable.mail_icon),
                    contentDescription = "mailFieldIcon"
                )
            }
        )
        Spacer(Modifier.height(20.dp))
        Text(
            stringResource(R.string.register_confirm_mail_title),
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(Modifier.height(4.dp))
        OutlinedTextField(
            emailConfirmation,
            onValueChange = { updateEmailConfirmation(it) },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .testTag("confirm_mail_register"),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = Color.LightGray,
                focusedContainerColor = Color(0xFFF6F7FB),
                unfocusedContainerColor = Color(0xFFF6F7FB),
                cursorColor = MaterialTheme.colorScheme.primary
            ),
            singleLine = true,
            placeholder = { Text(text = "Confirma tu correo") },
            leadingIcon = {
                Icon(
                    painterResource(R.drawable.mail_icon),
                    contentDescription = "ConfirmMailFieldIcon"
                )
            }
        )

        Spacer(Modifier.height(20.dp))
        Text(
            stringResource(R.string.Register_password_title),
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(Modifier.height(4.dp))
        OutlinedTextField(
            password,
            onValueChange = { updatePassWord(it) },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .testTag("password_register"),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = Color.LightGray,
                focusedContainerColor = Color(0xFFF6F7FB),
                unfocusedContainerColor = Color(0xFFF6F7FB),
                cursorColor = MaterialTheme.colorScheme.primary
            ),

            singleLine = true,
            visualTransformation = if (hidePass) PasswordVisualTransformation() else VisualTransformation.None,
            placeholder = { Text(text = "Contraseña") },
            leadingIcon = {
                Icon(
                    painterResource(R.drawable.baseline_lock_outline_24),
                    contentDescription = "passIcon"
                )
            }, trailingIcon = {
                IconButton(onClick = { hidePass = !hidePass }) {
                    Icon(
                        painter = painterResource(R.drawable.password_visible),
                        contentDescription = "Toggle password visibility"
                    )
                }
            }
        )
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Checkbox(password.length >= 8, enabled = false, onCheckedChange = {})
            Text(
                text = stringResource(R.string.mail_8_characters_check),
                color = colorResource(color.black),
                style = MaterialTheme.typography.labelMedium
            )
        }
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Checkbox(passContainsCapAndNonCap, enabled = false, onCheckedChange = {})

            Text(
                text = stringResource(R.string.email_capnoncap_check),
                color = colorResource(color.black),
                style = MaterialTheme.typography.labelMedium
            )
        }
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Checkbox(passContainsNumber, enabled = false, onCheckedChange = {})

            Text(
                text = stringResource(R.string.email_number_checkç),
                color = colorResource(color.black),
                style = MaterialTheme.typography.labelMedium
            )

        }

        Spacer(Modifier.height(20.dp))

        Button(
            onClick = { createAccount() }, modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            shape = RoundedCornerShape(14.dp),
            enabled = password.length >= 8 && passContainsNumber
                    && passContainsCapAndNonCap
                    && emailConfirmation == email && name.isNotBlank() && name.isNotEmpty(),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.blue_theme),
                contentColor = Color.White
            )
        ) {
            Text(text = stringResource(R.string.create_account_button))
        }

    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(UIState(), {}, {}, {}, {}, {}
    )
}

@Preview(showBackground = true)
@Composable
fun BottomDialogPreview() {
    UserCreatedBottomDialog({ }, {  })
}