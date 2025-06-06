package com.dreamdream.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dreamdream.R


val NotoSansFamily = FontFamily(
    Font(R.font.noto_sans, FontWeight.Normal)
)

@Composable
fun TwinklingStar(
    modifier: Modifier = Modifier,
    baseSize: Dp = 60.dp
) {
    Image(
        painter = painterResource(id = R.drawable.estrelacadente),
        contentDescription = "Estrela",
        modifier = modifier
            .alpha(0.8f)
            .then(Modifier.size(baseSize))
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