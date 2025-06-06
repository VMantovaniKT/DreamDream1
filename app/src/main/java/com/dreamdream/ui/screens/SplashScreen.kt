package com.dreamdream.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dreamdream.R
import com.dreamdream.ui.theme.DreamDreamTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay
import androidx.navigation.compose.rememberNavController

@Composable
fun SplashScreenWithDelay(
    delayMillis: Long = 3000L, // 3 segundos
    onTimeout: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0D47A1)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_dream),
            contentDescription = "Dream Dream Logo",
            modifier = Modifier
                .size(700.dp)
                .offset(y = (-70).dp)
        )
    }

    LaunchedEffect(Unit) {
        delay(delayMillis)
        Log.d("SplashScreen", "Tempo esgotado! Pode navegar agora.")
        onTimeout()
    }
}

@Composable
fun SplashScreen(navController: NavHostController) {
    SplashScreenWithDelay(
        delayMillis = 3000L,
        onTimeout = {
            Log.d("SplashScreen", "Navegando para welcome_screen")
            navController.navigate("welcome_screen"){
                //limpa a splash_screen da pilha para não voltar para ela
                popUpTo("splash_screen") { inclusive = true}
            }
        }
    )
 Box(
     modifier = Modifier
         .fillMaxSize()
         .background(Color(0xFF0D47A1)),
     contentAlignment = Alignment.Center
 ){
     Image(
         painter = painterResource(id = R.drawable.logo_dream),
         contentDescription = "Dream Dream Logo",
         modifier = Modifier
             .size(400.dp)
             .offset(y = (-70).dp)
     )
 }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SplashScreenPreview(){
    DreamDreamTheme {
        SplashScreen(navController = rememberNavController())
    }
}