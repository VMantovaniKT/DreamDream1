package com.dreamdream.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dreamdream.R
import com.dreamdream.ui.components.NuvemDesenhadaComCodigo
import com.dreamdream.ui.components.TwinklingStar
import com.dreamdream.ui.components.NotoSansFamily
import com.dreamdream.ui.theme.DreamDreamTheme

@Composable
fun TimerRuidoScreen(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0D47A1))
    ) {
        // Botão de voltar
        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp)
                .size(36.dp)
                .offset(y = 50.dp)


        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_voltar),
                contentDescription = "Voltar"
            )
        }

        // Estrelas fixas
        val estrelas = listOf(
            Offset(30f, 60f), Offset(175f, 80f), Offset(330f, 60f),
            Offset(90f, 220f), Offset(260f, 220f),
            Offset(30f, 360f), Offset(175f, 380f), Offset(330f, 360f),
            Offset(90f, 530f), Offset(260f, 530f),
            Offset(30f, 700f), Offset(175f, 720f), Offset(330f, 700f)
        )

        estrelas.forEach { offset ->
            TwinklingStar(
                modifier = Modifier
                    .offset(x = offset.x.dp, y = offset.y.dp)
                    .align(Alignment.TopStart),
                baseSize = 60.dp
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 80.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Nuvem com texto explicativo
            Box(contentAlignment = Alignment.Center) {
                NuvemDesenhadaComCodigo(
                    modifier = Modifier
                        .width(320.dp)
                        .height(150.dp)
                        .offset(y = 310.dp)
                    ,
                    corDaNuvem = Color(0xFFE8E8E8)
                )
                Text(
                    text = "Ruído Branco \n \n Abafa sons externos e acalma o bebê, imitando o ambiente do útero.",
                    color = Color(0xFF333333),
                    fontSize = 20.sp,
                    fontFamily = NotoSansFamily,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    lineHeight = 24.sp,
                    modifier = Modifier
                        .padding(horizontal = 40.dp)
                        .offset(y = 300.dp)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Quadrado com imagem
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .offset(y = (-165).dp)
                    .shadow(
                        elevation = 10.dp,
                        shape = RoundedCornerShape(8.dp),
                        ambientColor = Color.Black.copy(alpha = 3.0f),
                        spotColor = Color.Black.copy(alpha = 3.0f)
                    )
                    .background(Color(0xFFFFEEC2), shape = RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo_ruido_branco),
                    contentDescription = "Ícone Ruído Branco",
                    modifier = Modifier.size(150.dp),
                    contentScale = ContentScale.Fit
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Tempo",
                color = Color(0xFFFFFFFF),
                fontSize = 27.sp,
                fontFamily = NotoSansFamily,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                lineHeight = 24.sp,
                modifier = Modifier
                    .padding(horizontal = 40.dp)
                    .offset(y = 95.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(50.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 50.dp)
            ) {
                Button(
                    onClick = { navController.navigate("ruido_trinta_screen") },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5F78AE)),
                    modifier = Modifier
                        .weight(1f)
                        .height(60.dp)
                        .offset(y = 120.dp)
                        .shadow(elevation = 8.dp, shape = RoundedCornerShape(10.dp)),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        text = "30 min",
                        color = Color.White,
                        fontFamily = NotoSansFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp
                    )
                }

                Button(
                    onClick = { navController.navigate("ruido_sessenta_screen") },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5F78AE)),
                    modifier = Modifier
                        .weight(1f)
                        .height(60.dp)
                        .offset(y = 120.dp)
                        .shadow(elevation = 8.dp, shape = RoundedCornerShape(10.dp)),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        text = "60 min",
                        color = Color.White,
                        fontFamily = NotoSansFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TimerRuidoPreview() {
    DreamDreamTheme {
        TimerRuidoScreen(navController = rememberNavController())
    }
}