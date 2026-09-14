/**
 * Convx Project (C) 2026
 * Licensed under GPL-3.0 | See git history for contributors
 */

package com.nexamusic.music.ui.screens.settings.integrations

import com.nexamusic.music.ui.utils.appTopBarWindowInsets
import com.nexamusic.music.ui.utils.appTopBarWindowInsets
import androidx.compose.foundation.layout.Column
import com.nexamusic.music.ui.utils.appTopBarWindowInsets
import androidx.compose.foundation.layout.padding
import com.nexamusic.music.ui.utils.appTopBarWindowInsets
import androidx.compose.foundation.layout.windowInsetsPadding
import com.nexamusic.music.ui.utils.appTopBarWindowInsets
import androidx.compose.foundation.rememberScrollState
import com.nexamusic.music.ui.utils.appTopBarWindowInsets
import androidx.compose.foundation.verticalScroll
import com.nexamusic.music.ui.utils.appTopBarWindowInsets
import androidx.compose.material3.ExperimentalMaterial3Api
import com.nexamusic.music.ui.utils.appTopBarWindowInsets
import androidx.compose.material3.Icon
import com.nexamusic.music.ui.utils.appTopBarWindowInsets
import androidx.compose.material3.Text
import com.nexamusic.music.ui.utils.appTopBarWindowInsets
import androidx.compose.material3.TopAppBar
import com.nexamusic.music.ui.utils.appTopBarWindowInsets
import androidx.compose.material3.TopAppBarScrollBehavior
import com.nexamusic.music.ui.utils.appTopBarWindowInsets
import androidx.compose.runtime.Composable
import com.nexamusic.music.ui.utils.appTopBarWindowInsets
import androidx.compose.ui.Modifier
import com.nexamusic.music.ui.utils.appTopBarWindowInsets
import androidx.compose.ui.res.painterResource
import com.nexamusic.music.ui.utils.appTopBarWindowInsets
import androidx.compose.ui.res.stringResource
import com.nexamusic.music.ui.utils.appTopBarWindowInsets
import androidx.compose.ui.unit.dp
import com.nexamusic.music.ui.utils.appTopBarWindowInsets
import androidx.navigation.NavController
import com.nexamusic.music.ui.utils.appTopBarWindowInsets
import com.nexamusic.music.LocalPlayerAwareWindowInsets
import com.nexamusic.music.ui.utils.appTopBarWindowInsets
import com.nexamusic.music.R
import com.nexamusic.music.ui.utils.appTopBarWindowInsets
import com.nexamusic.music.ui.component.IconButton
import com.nexamusic.music.ui.utils.appTopBarWindowInsets
import com.nexamusic.music.ui.component.IntegrationCard
import com.nexamusic.music.ui.utils.appTopBarWindowInsets
import com.nexamusic.music.ui.component.IntegrationCardItem
import com.nexamusic.music.ui.utils.appTopBarWindowInsets
import com.nexamusic.music.ui.utils.backToMain

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IntegrationScreen(
    navController: NavController,
    scrollBehavior: TopAppBarScrollBehavior,
) {
    Column(
        Modifier
            .windowInsetsPadding(LocalPlayerAwareWindowInsets.current)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp),
    ) {
        IntegrationCard(
            title = stringResource(R.string.general),
            items = listOf(
                IntegrationCardItem(
                    icon = painterResource(R.drawable.discord),
                    title = { Text(stringResource(R.string.discord_integration)) },
                    onClick = {
                        navController.navigate("settings/integrations/discord")
                    }
                ),
                IntegrationCardItem(
                    icon = painterResource(R.drawable.music_note),
                    title = { Text(stringResource(R.string.lastfm_integration)) },
                    onClick = {
                        navController.navigate("settings/integrations/lastfm")
                    }
                )
            )
        )
    }

    TopAppBar(
            windowInsets = appTopBarWindowInsets(),
        title = { Text(stringResource(R.string.integrations)) },
        navigationIcon = {
            IconButton(
                onClick = navController::navigateUp,
                onLongClick = navController::backToMain,
            ) {
                Icon(
                    painterResource(R.drawable.arrow_back),
                    contentDescription = null,
                )
            }
        }
    )
}
