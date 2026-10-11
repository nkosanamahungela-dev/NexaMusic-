/**
 * NexaMusic Project (C) 2026
 * Licensed under GPL-3.0 | See repository README for upstream credits.
 */

package com.nexamusic.music.ui.screens.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialShapes
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.toShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.nexamusic.music.BuildConfig
import com.nexamusic.music.LocalPlayerAwareWindowInsets
import com.nexamusic.music.R
import com.nexamusic.music.ui.component.IconButton
import com.nexamusic.music.ui.component.Material3SettingsGroup
import com.nexamusic.music.ui.component.Material3SettingsItem
import com.nexamusic.music.ui.utils.appTopBarWindowInsets
import com.nexamusic.music.ui.utils.backToMain
import com.nexamusic.music.ui.utils.safeOpenUri
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private const val NEXAMUSIC_REPOSITORY = "https://github.com/nkosanamahungela-dev/NexaMusic-"
private const val NEXAMUSIC_GITHUB_PROFILE = "https://github.com/nkosanamahungela-dev"
private const val NEXAMUSIC_DISCORD_INVITE = "https://discord.gg/tBCfeMvt9"

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun AboutScreen(
    navController: NavController,
    scrollBehavior: TopAppBarScrollBehavior,
    onBack: (() -> Unit)? = null,
) {
    val uriHandler = LocalUriHandler.current
    val context = LocalContext.current
    val cookieShape = MaterialShapes.Cookie7Sided.toShape()
    val buildChannel = if (BuildConfig.IS_NIGHTLY) "NIGHTLY" else "STABLE"

    val installedDate = remember {
        try {
            val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
                .format(Date(packageInfo.firstInstallTime))
        } catch (_: Exception) {
            "Unknown"
        }
    }

    Column(
        Modifier
            .windowInsetsPadding(LocalPlayerAwareWindowInsets.current)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp),
    ) {
        // NexaMusic brand header
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "NexaMusic",
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 48.sp,
                    letterSpacing = 2.sp,
                ),
                color = MaterialTheme.colorScheme.onSurface,
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .border(
                        width = 1.5.dp,
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                        shape = CircleShape,
                    )
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Image(
                    painter = painterResource(R.drawable.nexamusic_logo),
                    contentDescription = "NexaMusic logo",
                    modifier = Modifier.size(24.dp),
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                )

                Text(
                    text = "v" + BuildConfig.VERSION_NAME + " • " + buildChannel,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary,
                )
            }
        }

        // Developer
        Material3SettingsGroup(
            title = "DEVELOPER",
            items = listOf(
                Material3SettingsItem(
                    icon = painterResource(R.drawable.ic_launcher_mono),
                    title = { Text("Smelokuhle Mahungela") },
                    description = {
                        Text(
                            "Lead Developer",
                            color = MaterialTheme.colorScheme.primary,
                        )
                    },
                    tintIcon = false,
                    iconShape = cookieShape,
                    onClick = {
                        uriHandler.safeOpenUri(context, NEXAMUSIC_GITHUB_PROFILE)
                    },
                ),
            ),
        )

        Spacer(modifier = Modifier.height(27.dp))

        // Community
        Material3SettingsGroup(
            title = "COMMUNITY",
            items = listOf(
                Material3SettingsItem(
                    icon = painterResource(R.drawable.github),
                    title = { Text("GitHub Repository") },
                    description = { Text("View source code") },
                    onClick = {
                        uriHandler.safeOpenUri(context, NEXAMUSIC_REPOSITORY)
                    },
                ),
                Material3SettingsItem(
                    icon = painterResource(R.drawable.discord),
                    title = { Text("Discord") },
                    description = { Text("Join the NexaMusic community") },
                    onClick = {
                        uriHandler.safeOpenUri(context, NEXAMUSIC_DISCORD_INVITE)
                    },
                ),
            ),
        )

        Spacer(modifier = Modifier.height(27.dp))

        // App information. No payment or donation options are displayed.
        Material3SettingsGroup(
            title = "APP INFO",
            items = listOf(
                Material3SettingsItem(
                    icon = painterResource(R.drawable.deployed_app_update),
                    title = { Text("Installed Date") },
                    description = { Text(installedDate) },
                ),
                Material3SettingsItem(
                    icon = painterResource(R.drawable.info),
                    title = { Text("Version code") },
                    description = { Text(BuildConfig.VERSION_CODE.toString()) },
                ),
                Material3SettingsItem(
                    icon = painterResource(R.drawable.license_vivi),
                    title = { Text("GNU General Public License v3.0") },
                    description = { Text("GPL-3.0 • Free Open Source Software") },
                    onClick = {
                        uriHandler.safeOpenUri(
                            context,
                            "$NEXAMUSIC_REPOSITORY/blob/main/LICENSE",
                        )
                    },
                ),
            ),
        )

        Spacer(modifier = Modifier.height(20.dp))
    }

    TopAppBar(
        windowInsets = appTopBarWindowInsets(),
        title = { Text("About") },
        navigationIcon = {
            IconButton(
                onClick = { onBack?.invoke() ?: navController.navigateUp() },
                onLongClick = navController::backToMain,
            ) {
                Icon(
                    painterResource(R.drawable.arrow_back),
                    contentDescription = "Back",
                )
            }
        },
        scrollBehavior = scrollBehavior,
    )
}