package com.example.womensafetyapp.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.womensafetyapp.presentation.Screen
import com.example.womensafetyapp.presentation.screens.components.Template
import com.example.womensafetyapp.presentation.ui.theme.DarkBlue
import com.example.womensafetyapp.presentation.ui.theme.OrangishYellow
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DashBoardScreen(navController: NavController) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
        Scaffold(
            scaffoldState = rememberScaffoldState(),
            topBar = {
                TopAppBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(LocalConfiguration.current.screenHeightDp.dp / 18),
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu", modifier = Modifier
                            .size(40.dp)
                            .clickable {
                                scope.launch {
                                    scaffoldState.drawerState.open()
                                }
                            })

                    }
                }
            },
            drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
            drawerContent = {
                DrawerBody(navController)
            }){
            Template {
                Button(
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = DarkBlue,
                        contentColor = OrangishYellow
                    ),
                    modifier = Modifier
                        .width(LocalConfiguration.current.screenWidthDp.dp / 2)
                        .height(
                            LocalConfiguration.current.screenHeightDp.dp / 8
                        ).clip(RoundedCornerShape(20.dp))
                ) {
                    Text(text = "SAVE ME", style = TextStyle(fontWeight = FontWeight.Bold))
                }
            }
        }
}

@Composable
fun DrawerBody(navController: NavController) {
    val state = remember {
        mutableStateOf(false)
    }
    if (state.value){
        LogOut(navController = navController)
    }
    Column(modifier = Modifier
        .fillMaxSize()
        .background(DarkBlue),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.padding(vertical = 80.dp))
        Button(onClick = {
            state.value = true
        }) {
            Text(text = "Log Out")
        }
    }
}

@Composable
fun LogOut(navController: NavController){
    AlertDialog(onDismissRequest = { /*TODO*/ },
        title = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "LOG OUT", fontSize = 30.sp)
            }
        },
        text = {
            Text(text = "Do you wanna Log Out")
        },
        dismissButton = {
            Button(onClick = { }) {
                Text(text = "Log Out")
            }
        }, confirmButton = {
            Button(onClick = {  }) {
                Text(text = " Cancel")
            }
        })
}