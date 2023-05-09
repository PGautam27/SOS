package com.example.womensafetyapp.presentation.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.womensafetyapp.R
import com.example.womensafetyapp.presentation.screens.components.Template
import com.example.womensafetyapp.ui.theme.Orange
import com.example.womensafetyapp.ui.theme.Yellow
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class, ExperimentalFoundationApi::class)
@Composable
fun SingIn(navController: NavController) {
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    val userName = remember {
        mutableStateOf(String())
    }

    val passWord = remember {
        mutableStateOf(String())
    }

    val passVisualValue = remember {
        mutableStateOf(true)
    }

    val coroutineScope = rememberCoroutineScope()
    val bringIntoViewRequester = BringIntoViewRequester()

    Template {
        Text(
            AnnotatedString(
                text = "Now You ",
                spanStyle = SpanStyle(
                    fontSize = LocalConfiguration.current.fontScale.times(23).sp,
                    fontWeight = FontWeight.Bold
                )
            ).plus(
                AnnotatedString(
                    text = "SignIn",
                    spanStyle = SpanStyle(
                        color = Orange,
                        fontSize = LocalConfiguration.current.fontScale.times(30).sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            )
        )
        Spacer(modifier = Modifier.padding(10.dp))
        OutlinedTextField(value = userName.value,
            onValueChange = { userName.value = it },
            modifier = Modifier
                .clip(RoundedCornerShape(size = 5.dp))
                .width(
                    LocalConfiguration.current.screenWidthDp.dp - 80.dp
                ),
            label = { Text(text = "Mobile Number", fontWeight = FontWeight.Bold) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                    keyboardController?.hide()
                }
            ),colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Orange,
                unfocusedBorderColor = Orange,
                unfocusedLabelColor = Orange,
                focusedLabelColor = Orange
            ),
            maxLines = 1)

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = passWord.value,
            onValueChange = { passWord.value = it },
            modifier = Modifier
                .width(
                    LocalConfiguration.current.screenWidthDp.dp - 80.dp
                )
                .clip(RoundedCornerShape(size = 5.dp))
                .onFocusEvent { event ->
                    if (event.isFocused) {
                        coroutineScope.launch {
                            bringIntoViewRequester.bringIntoView()
                        }
                    }
                },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Orange,
                unfocusedBorderColor = Orange,
                unfocusedLabelColor = Orange,
                focusedLabelColor = Orange
            ),
            maxLines = 1,
            visualTransformation = if (passVisualValue.value) PasswordVisualTransformation() else VisualTransformation.None,
            trailingIcon = {
                Image(
                    painter = painterResource(id = if (passVisualValue.value) R.drawable.visibility else R.drawable.visibilityoff),
                    contentDescription = "password",
                    modifier = Modifier.clickable {
                        passVisualValue.value = !passVisualValue.value
                    })
            },
            label = { Text(text = "Password", fontWeight = FontWeight.Bold) },
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                    keyboardController?.hide()
                }
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
        )
        Spacer(modifier = Modifier.padding(20.dp))

        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Orange,
                contentColor = Yellow
            ),
            modifier = Modifier.width(LocalConfiguration.current.screenWidthDp.dp/2 - 30.dp)
        ) {
            Text(text = "SIGN IN", style = TextStyle(fontWeight = FontWeight.Bold))
        }
    }
}