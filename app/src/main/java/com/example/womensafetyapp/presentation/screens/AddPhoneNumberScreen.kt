package com.example.womensafetyapp.presentation.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
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
import com.example.womensafetyapp.presentation.viewModel.LoginScreenViewModel
import com.example.womensafetyapp.ui.theme.DarkBlue
import com.example.womensafetyapp.ui.theme.OrangishYellow
import com.example.womensafetyapp.ui.theme.Red
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun AddPhoneNumberScreen(navController: NavController, viewModel: LoginScreenViewModel,onClick: () -> Unit) {

    val phoneNumbers by viewModel._phoneNumbers.observeAsState()

    val scope = rememberCoroutineScope()
    scope.launch {
        Log.d("Ok","IT works")
        phoneNumbers?.forEach {
            Log.d("Contact Name",it.name)
        }
    }

    Template {
        Spacer(modifier = Modifier.padding(40.dp))
        Text(
            AnnotatedString(
                text = "Let's add your",
                spanStyle = SpanStyle(
                    fontSize = LocalConfiguration.current.fontScale.times(25).sp,
                    fontWeight = FontWeight.Bold
                )
            )
                .plus(
                    AnnotatedString(
                        text = " Close Ones",
                        spanStyle = SpanStyle(
                            color = Red,
                            fontSize = LocalConfiguration.current.fontScale.times(30).sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                )
        )

        Spacer(modifier = Modifier.padding(20.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = DarkBlue,
                    contentColor = OrangishYellow
                ),
                modifier = Modifier.width(LocalConfiguration.current.screenWidthDp.dp/2 - 30.dp)
            ) {
                Text(text = "ADD NUMBER", style = TextStyle(fontWeight = FontWeight.Bold))
            }
            Button(
                onClick = {navController.navigate(Screen.SignInScreen.route)},
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = DarkBlue,
                    contentColor = OrangishYellow
                ),
                modifier = Modifier.width(LocalConfiguration.current.screenWidthDp.dp/2 - 30.dp)
            ) {
                Text(text = "DONE")
            }
        }

        Spacer(modifier = Modifier.padding(20.dp))
        Column(
            modifier = Modifier
                .height(LocalConfiguration.current.screenHeightDp.dp)
                .verticalScroll(rememberScrollState())
                .width(LocalConfiguration.current.screenWidthDp.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            phoneNumbers?.forEach {
                Column(
                    modifier = Modifier
                        .width(LocalConfiguration.current.screenWidthDp.dp - 60.dp)
                        .height(LocalConfiguration.current.screenHeightDp.dp / 10)
                        .border(width = 3.dp, color = DarkBlue, shape = RoundedCornerShape(20.dp)),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = it.name, fontSize = LocalConfiguration.current.fontScale.times(18).sp, color = DarkBlue, fontWeight = FontWeight.Bold)
                    Text(text = it.number, fontSize = LocalConfiguration.current.fontScale.times(18).sp, color = DarkBlue, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.padding(5.dp))
            }
        }
    }

}