
package com.dreamdream.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dreamdream.R
import com.dreamdream.ui.theme.DreamDreamTheme
import com.dreamdream.ui.components.NuvemDesenhadaComCodigo
import com.dreamdream.ui.components.TwinklingStar
import com.dreamdream.ui.components.NotoSansFamily



@Composable
fun TwinklingStar(
    modifier: Modifier = Modifier,
    baseSize: Dp = 60.dp
) {
    Image(
        painter = painterResource(id = R.drawable.estrelacadente),
        contentDescription = "Estrela",
        modifier = modifier
            .size(baseSize)
            .alpha(0.8f)
    )
}

@Composable
fun NuvemDesenhadaComCodigo(
    modifier: Modifier = Modifier,
    corDaNuvem: Color = Color(0xFFF0F0F0)
) {
    Canvas(modifier = modifier) {
        val larguraTotal = size.width
        val alturaTotal = size.height

        drawCircle(
            color = corDaNuvem,
            radius = larguraTotal * 0.33f,
            center = Offset(x = larguraTotal * 0.5f, y = alturaTotal * 0.40f)
        )
        drawCircle(
            color = corDaNuvem,
            radius = larguraTotal * 0.28f,
            center = Offset(x = larguraTotal * 0.25f, y = alturaTotal * 0.45f)
        )
        drawCircle(
            color = corDaNuvem,
            radius = larguraTotal * 0.28f,
            center = Offset(x = larguraTotal * 0.75f, y = alturaTotal * 0.45f)
        )
        drawOval(
            color = corDaNuvem,
            topLeft = Offset(x = larguraTotal * 0.05f, y = alturaTotal * 0.4f),
            size = Size(width = larguraTotal * 0.9f, height = alturaTotal * 0.6f)
        )
    }
}

@Composable
fun TrilhaBotao(
    onClick: () -> Unit,
    logoId: Int,
    descricaoLogo: String,
    texto: String,
    buttonColor: Color = Color(0xFF5F78AE),
    usarLogoCompacta: Boolean = false
) {
    val logoSizeDefault = 44.dp
    val logoSizeCompact = 36.dp
    val logoTextSpacing = 12.dp
    val imagemSize = if (usarLogoCompacta) logoSizeCompact else logoSizeDefault

    Button(
        onClick = onClick,
        modifier = Modifier
            .width(280.dp)
            .height(70.dp)
            .shadow(
                elevation = 16.dp,
                shape = RoundedCornerShape(10.dp),
                ambientColor = Color.Black.copy(alpha = 0.8f),
                spotColor = Color.Black.copy(alpha = 0.8f)
            ),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor,
            contentColor = Color.White
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = logoId),
                contentDescription = descricaoLogo,
                modifier = Modifier.size(imagemSize)
            )
            Spacer(modifier = Modifier.width(logoTextSpacing))
            Text(
                text = texto,
                fontFamily = NotoSansFamily,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun HomeScreen(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0D47A1))
    ) {
        val estrelas = listOf(
            Offset(30f, 60f), Offset(175f, 80f), Offset(330f, 60f),
            Offset(90f, 220f), Offset(260f, 220f),
            Offset(30f, 360f), Offset(175f, 380f), Offset(330f, 360f),
            Offset(90f, 530f), Offset(260f, 530f),
            Offset(30f, 700f), Offset(175f, 720f), Offset(330f, 700f)
        )

        estrelas.forEach { offset ->
            TwinklingStar(
                modifier = Modifier.offset(x = offset.x.dp, y = offset.y.dp).align(Alignment.TopStart),
                baseSize = 60.dp
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 70.dp)
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            Box(contentAlignment = Alignment.Center) {
                NuvemDesenhadaComCodigo(
                    modifier = Modifier
                        .width(320.dp)
                        .height(150.dp),
                    corDaNuvem = Color(0xFFE8E8E8)
                )
                Text(
                    text = "Escolha a trilha sonora do\n sonho do seu anjinho!",
                    color = Color(0xFF333333),
                    fontSize = 20.sp,
                    fontFamily = NotoSansFamily,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    lineHeight = 24.sp,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 10.dp)
                )
            }

            Spacer(modifier = Modifier.height(60.dp))

            TrilhaBotao(
                onClick = {
                    navController.navigate("timer_ruido_screen")
                },
                logoId = R.drawable.logo_ruido_branco,
                descricaoLogo = "Logo Ruído Branco",
                texto = "Som Ruído Branco",
                buttonColor = Color(0xFF5F78AE)
            )

            Spacer(modifier = Modifier.height(42.dp))

            TrilhaBotao(
                onClick = { /* TODO */ },
                logoId = R.drawable.logo_batimento_cardiaco,
                descricaoLogo = "Logo Batimento Cardíaco",
                texto = "Batimento Cardíaco",
                buttonColor = Color(0xFF5F78AE),
                usarLogoCompacta = true
            )

            Spacer(modifier = Modifier.height(42.dp))

            TrilhaBotao(
                onClick = { /* TODO */ },
                logoId = R.drawable.logo_chuva,
                descricaoLogo = "Logo Chuva",
                texto = "Som de Tempestade",
                buttonColor = Color(0xFF5F78AE),
                usarLogoCompacta = true
            )

            Spacer(modifier = Modifier.height(42.dp))

            TrilhaBotao(
                onClick = { /* TODO */ },
                logoId = R.drawable.logo_cancao_de_ninar,
                descricaoLogo = "Logo Canção de Ninar",
                texto = "Composição de Ninar",
                buttonColor = Color(0xFF5F78AE),
                usarLogoCompacta = true
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomePreview() {
    DreamDreamTheme {
        HomeScreen(navController = rememberNavController())
    }
}