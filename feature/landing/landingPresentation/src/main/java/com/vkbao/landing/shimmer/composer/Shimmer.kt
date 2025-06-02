package com.vkbao.landing.shimmer.composer

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vkbao.landing.home.composer.backgroundColor

@Composable
fun LandingShimmer(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = Modifier.fillMaxSize().background(backgroundColor)
    ) {
        Spacer(modifier = Modifier.height(140.dp))
        CardSectionShimmer()
        Spacer(modifier = Modifier.weight(1f))
        ShimmerBox(
            modifier = Modifier.fillMaxSize()
                .padding(start = 21.dp, bottom = 21.dp, end = 21.dp)
        )
    }
}

@Composable
fun ShimmerBox(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(12.dp)
) {
    val shimmerColors = listOf(
        Color.LightGray.copy(alpha = 0.6f),
        Color.LightGray.copy(alpha = 0.2f),
        Color.LightGray.copy(alpha = 0.6f)
    )

    val transition = rememberInfiniteTransition()
    val translateAnim by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset(translateAnim, translateAnim),
        end = Offset(translateAnim + 200f, translateAnim + 200f)
    )

    Box(
        modifier = modifier
            .background(brush = brush, shape = shape)
    )
}

@Composable
fun CardSectionShimmer(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(backgroundColor, shape = RoundedCornerShape(12.dp))
            .padding(top = 40.dp, start = 16.dp, end = 16.dp, bottom = 40.dp)
    ) {
        ShimmerRow() // fromCurrency
        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Spacer(modifier = Modifier.width(8.dp))
            ShimmerBox(
                modifier = Modifier
                    .size(46.dp),
                shape = CircleShape
            )
            Spacer(modifier = Modifier.weight(1f))
            ShimmerBox(
                modifier = Modifier
                    .width(100.dp)
                    .height(20.dp),
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        ShimmerRow() // toCurrency
    }
}

@Composable
fun ShimmerRow() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        ShimmerBox(
            modifier = Modifier
                .width(80.dp)
                .height(24.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        ShimmerBox(
            modifier = Modifier
                .width(12.dp)
                .height(12.dp),
            shape = CircleShape
        )
        Spacer(modifier = Modifier.width(8.dp))
        Spacer(modifier = Modifier.weight(1f))
        ShimmerBox(
            modifier = Modifier
                .height(60.dp)
                .fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ShimmerPreview() {
    LandingShimmer()
}