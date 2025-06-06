package com.dreamdream.ui.screens

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
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


@Composable
fun WelcomeScreen(navController: NavHostController) {
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

        ContinueButton(
            onClick = {
                navController.navigate("home_screen") {
                    popUpTo("welcome_screen") {
                        inclusive = true
                    }
                }
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 170.dp)
        )
    }
}

@Composable
fun ContinueButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Button(
        onClick = onClick,
        modifier = modifier
            .width(170.dp)
            .height(60.dp)
            .shadow(elevation = 8.dp, shape = RoundedCornerShape(10.dp)),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF5F78AE),
            contentColor = Color.White
        )
    ) {
        Text(
            text= "Continuar",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun WelcomePreview(){
    DreamDreamTheme {
        WelcomeScreen(navController = rememberNavController())
    }
}