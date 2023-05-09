package com.example.womensafetyapp.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.womensafetyapp.presentation.Screen
import com.example.womensafetyapp.presentation.screens.components.Template
import com.example.womensafetyapp.ui.theme.DarkBlue
import com.example.womensafetyapp.ui.theme.OrangishYellow
import com.example.womensafetyapp.ui.theme.Red


@Composable
fun HomeScreen(navController: NavController) {
    Template{
        Text(
            AnnotatedString(
                text = "Welcome to ",
                spanStyle = SpanStyle(
                    fontSize = LocalConfiguration.current.fontScale.times(20).sp,
                    fontWeight = FontWeight.Bold
                )
            )
                .plus(
                    AnnotatedString(
                        text = "Woman Safety",
                        spanStyle = SpanStyle(
                            color = Red,
                            fontSize = LocalConfiguration.current.fontScale.times(30).sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                )
        )
        
        Spacer(modifier = Modifier.padding(20.dp))
        Text(
            text = "Let's Get Started!",
            style = TextStyle(
                fontSize = LocalConfiguration.current.fontScale.times(20).sp,
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.padding(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(
                onClick = {navController.navigate(Screen.SignUpScreen.route)},
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = DarkBlue,
                    contentColor = OrangishYellow
                ),
                modifier = Modifier.width(LocalConfiguration.current.screenWidthDp.dp/2 - 30.dp)
            ) {
                Text(text = "SIGN UP", style = TextStyle(fontWeight = FontWeight.Bold))
            }
            Button(
                onClick = {navController.navigate(Screen.SignInScreen.route)},
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = DarkBlue,
                    contentColor = OrangishYellow
                ),
                modifier = Modifier.width(LocalConfiguration.current.screenWidthDp.dp/2 - 30.dp)
            ) {
                Text(text = "LOGIN")
            }
        }
    }
}