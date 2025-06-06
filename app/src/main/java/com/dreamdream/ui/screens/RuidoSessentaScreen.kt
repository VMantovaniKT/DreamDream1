package com.dreamdream.ui.screens

import android.net.Uri
import android.widget.VideoView
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
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dreamdream.R
import com.dreamdream.ui.components.NotoSansFamily
import com.dreamdream.ui.components.TwinklingStar
import com.dreamdream.ui.theme.DreamDreamTheme
import kotlinx.coroutines.delay

fun formatTime60(seconds: Int): String {
    val minutes = seconds / 60
    val secs = seconds % 60
    return String.format("%02d:%02d", minutes, secs)
}

@Composable
fun RuidoSessentaScreen(navController: NavController) {
    val context = LocalContext.current
    var isPlaying by remember { mutableStateOf(true) }
    var progress by remember { mutableStateOf(0f) }
    val totalDuration = 60 * 60 // 60 minutos
    var elapsedTime by remember { mutableStateOf(0) }
    var isLooping by remember { mutableStateOf(false) }

    // Referência ao VideoView
    var videoView: VideoView? by remember { mutableStateOf(null) }

    // Timer
    LaunchedEffect(isPlaying, elapsedTime, isLooping) {
        while (isPlaying) {
            delay(1000L)
            elapsedTime += 1
            progress = elapsedTime / totalDuration.toFloat()

            if (elapsedTime >= totalDuration) {
                if (isLooping) {
                    elapsedTime = 0
                    progress = 0f
                    videoView?.seekTo(0)
                    videoView?.start()
                } else {
                    isPlaying = false
                    videoView?.pause()
                }
            }
        }
    }

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

        // Botão de voltar
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
        // Conteúdo visual
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
            text = "60 Min",
            color = Color.White,
            fontSize = 27.sp,
            fontFamily = NotoSansFamily,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.Center).offset(y = 140.dp)
        )

        Row(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
                .offset(y = 190.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(formatTime60(elapsedTime), color = Color.White, fontSize = 15.sp, fontFamily = NotoSansFamily)
            Text(formatTime60(totalDuration), color = Color.White, fontSize = 15.sp, fontFamily = NotoSansFamily)
        }

        LinearProgressIndicator(
            progress = progress,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 40.dp)
                .offset(y = 210.dp)
                .fillMaxWidth()
                .height(8.dp)
                .clip(RoundedCornerShape(4.dp)),
            color = Color.White,
            trackColor = Color.White.copy(alpha = 0.3f)
        )

        Row(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
                .padding(horizontal = 60.dp)
                .offset(y = 260.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Reiniciar
            Image(
                painter = painterResource(id = R.drawable.logo_voltar_player),
                contentDescription = "Reiniciar",
                modifier = Modifier
                    .size(40.dp)
                    .clickable {
                        elapsedTime = 0
                        progress = 0f
                        videoView?.seekTo(0)
                        if (isPlaying) videoView?.start()
                    }
            )

            // Play / Pause
            Image(
                painter = painterResource(
                    id = if (isPlaying) R.drawable.logo_pause else R.drawable.logo_play
                ),
                contentDescription = if (isPlaying) "Pausar" else "Tocar",
                modifier = Modifier
                    .size(40.dp)
                    .clickable {
                        isPlaying = !isPlaying
                        if (isPlaying) videoView?.start() else videoView?.pause()
                    }
            )

            // Loop
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
fun RuidoSesentaPreview() {
    DreamDreamTheme {
        RuidoSessentaScreen(navController = rememberNavController())
    }
}