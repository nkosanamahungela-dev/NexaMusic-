/**
 * Convx Project (C) 2026
 * Licensed under GPL-3.0 | See git history for contributors
 */

package com.nexamusic.music.lyrics

import android.content.Context
import com.nexamusic.music.constants.EnableMusixmatchKey
import com.nexamusic.music.musixmatch.MusixmatchClient
import com.nexamusic.music.utils.dataStore
import com.nexamusic.music.utils.get
import timber.log.Timber

object MusixmatchLyricsProvider : LyricsProvider {
    private const val TAG = "MusixmatchProvider"

    override val name = "Musixmatch"

    override fun isEnabled(context: Context): Boolean {
        // Also initializes the client lazily on first enable check
        val enabled = context.dataStore[EnableMusixmatchKey] ?: true
        if (enabled) {
            MusixmatchClient.init(context)
        }
        return enabled
    }

    override suspend fun getLyrics(
        id: String,
        title: String,
        artist: String,
        duration: Int,
        album: String?,
    ): Result<String> {
        Timber.tag(TAG).d("getLyrics: title='$title', artist='$artist', duration=$duration")
        return try {
            MusixmatchClient.getLyrics(title, artist, duration, album)
        } catch (e: Exception) {
            Timber.tag(TAG).e(e, "Exception in getLyrics")
            Result.failure(e)
        }
    }
}
