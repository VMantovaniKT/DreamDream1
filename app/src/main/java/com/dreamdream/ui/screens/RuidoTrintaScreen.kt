package com.dreamdream.ui.screens

import android.media.MediaPlayer
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dreamdream.R
import com.dreamdream.ui.components.NotoSansFamily
import com.dreamdream.ui.components.TwinklingStar
import com.dreamdream.ui.theme.DreamDreamTheme
import kotlinx.coroutines.delay

fun formatTime(seconds: Int): String {
    val minutes = seconds / 60
    val secs = seconds % 60
    return String.format("%02d:%02d", minutes, secs)
}

@Composable
fun RuidoTrintaScreen(navController: NavController) {
    val context = LocalContext.current
    var isPlaying by remember { mutableStateOf(true) }
    var elapsedTime by remember { mutableStateOf(0) }
    val totalDuration = 30 * 60 // 30 minutos em segundos
    var isLooping by remember { mutableStateOf(false) }

    // Slider interativo
    var sliderPosition by remember { mutableStateOf(0f) }
    var isUserSeeking by remember { mutableStateOf(false) }

    // MediaPlayer controlado por rememberUpdatedState





    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0D47A1))
    ) {
        // Estrelas
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

        // Botão voltar
        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp)
                .offset(y = 50.dp)
                .size(36.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_voltar),
                contentDescription = "Voltar"
            )
        }

        // Quadrado com imagem central
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .size(200.dp)
                .offset(y = (-190).dp)
                .shadow(10.dp, RoundedCornerShape(8.dp))
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

        Text(
            text = "Tempo\nEscolhido",
            color = Color.White,
            fontSize = 27.sp,
            fontFamily = NotoSansFamily,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            lineHeight = 36.sp,
            modifier = Modifier.align(Alignment.Center).offset(y = 50.dp)
        )

        Text(
            text = "30 Min",
            color = Color.White,
            fontSize = 27.sp,
            fontFamily = NotoSansFamily,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.Center).offset(y = 140.dp)
        )

        // Slider de tempo e controle
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 40.dp)
                .offset(y = 190.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(formatTime(sliderPosition.toInt()), color = Color.White, fontSize = 15.sp, fontFamily = NotoSansFamily)
                Text(formatTime(totalDuration), color = Color.White, fontSize = 15.sp, fontFamily = NotoSansFamily)
            }

            Slider(
                value = sliderPosition,
                onValueChange = {
                    isUserSeeking = true
                    sliderPosition = it
                },
                onValueChangeFinished = {
                    isUserSeeking = false
                    elapsedTime = sliderPosition.toInt()

                },
                valueRange = 0f..totalDuration.toFloat(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(32.dp),
                colors = SliderDefaults.colors(
                    thumbColor = Color.White,
                    activeTrackColor = Color.White,
                    inactiveTrackColor = Color.White.copy(alpha = 0.3f)
                )
            )
        }

        // Botões: Reiniciar, Play/Pause, Loop
        Row(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
                .padding(horizontal = 60.dp)
                .offset(y = 260.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_voltar_player),
                contentDescription = "Reiniciar",
                modifier = Modifier
                    .size(40.dp)
            )

            Image(
                painter = painterResource(
                    id = if (isPlaying) R.drawable.logo_pause else R.drawable.logo_play
                ),
                contentDescription = if (isPlaying) "Pausar" else "Tocar",
                modifier = Modifier
                    .size(40.dp)
            )

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = R.drawable.logo_loop),
                    contentDescription = "Loop",
                    modifier = Modifier
                        .size(40.dp)
                        .clickable {
                            isLooping = !isLooping
                        }
                )
                if (isLooping) {
                    Box(
                        modifier = Modifier
                            .padding(top = 4.dp)
                            .size(8.dp)
                            .clip(CircleShape)
                            .background(Color.White)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RuidoTrintaPreview() {
    DreamDreamTheme {

        RuidoTrintaScreen(navController = rememberNavController())

    }
}