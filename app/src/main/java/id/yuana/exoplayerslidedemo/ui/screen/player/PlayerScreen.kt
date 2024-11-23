package id.yuana.exoplayerslidedemo.ui.screen.player

import androidx.annotation.OptIn
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.Player.REPEAT_MODE_ALL
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import id.yuana.exoplayerslidedemo.data.model.Media
import id.yuana.exoplayerslidedemo.data.model.isVideo
import id.yuana.exoplayerslidedemo.data.repository.MediaRepository

@Composable
fun PlayerScreen(
    modifier: Modifier = Modifier,
    mediaRepository: MediaRepository
) {
    var mediaSource by remember { mutableStateOf(emptyList<Media>()) }

    LaunchedEffect(Unit) {
        mediaSource = mediaRepository.all()
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        if (mediaSource.isNotEmpty()) {
            PlayerImpl(mediaSource)
        }
    }
}

@OptIn(UnstableApi::class)
@Composable
private fun PlayerImpl(
    data: List<Media>,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            data.forEach { media ->
                if (media.isVideo()) {
                    addMediaItem(MediaItem.fromUri(media.file))
                } else {
                    addMediaItem(
                        MediaItem.Builder().setUri(media.file).setImageDurationMs(media.duration)
                            .build()
                    )
                }
            }

            prepare()
            playWhenReady = true
            repeatMode = REPEAT_MODE_ALL
        }
    }

    AndroidView(
        factory = { PlayerView(context).apply { player = exoPlayer } },
        modifier = modifier
    )

    DisposableEffect(Unit) {
        onDispose { exoPlayer.release() }
    }
}