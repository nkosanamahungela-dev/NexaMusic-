/**
 * NexaMusic Project (C) 2026
 * Visual treatment adapted from Convx 2.0.0-nightly (GPL-3.0).
 */
package com.nexamusic.music.ui.component

import androidx.compose.foundation.border
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.nexamusic.music.constants.HomeCardCornerRadiusOverrideKey
import com.nexamusic.music.ui.component.shapes.ContinuousRoundedRectangle
import com.nexamusic.music.ui.theme.AppleTokens
import com.nexamusic.music.utils.rememberPreference

@Composable
fun rememberHomeCardShape(): Shape {
    val (cornerOverride) = rememberPreference(HomeCardCornerRadiusOverrideKey, 0)
    val corner = if (cornerOverride > 0) cornerOverride.dp else AppleTokens.CardCornerLarge
    return remember(corner) { ContinuousRoundedRectangle(corner) }
}

fun Modifier.homeCardRim(shape: Shape): Modifier = border(
    width = 1.dp,
    brush = Brush.verticalGradient(
        colors = listOf(
            Color.White.copy(alpha = 0.24f),
            Color.White.copy(alpha = 0.04f),
            Color.Black.copy(alpha = 0.10f),
        ),
    ),
    shape = shape,
)
