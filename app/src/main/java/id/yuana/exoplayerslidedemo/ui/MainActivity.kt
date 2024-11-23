package id.yuana.exoplayerslidedemo.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import id.yuana.exoplayerslidedemo.data.repository.MediaRepository
import id.yuana.exoplayerslidedemo.ui.screen.player.PlayerScreen
import id.yuana.exoplayerslidedemo.ui.theme.ExoPlayerSlideDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val mediaRepository = MediaRepository.Impl(this)

        setContent {
            ExoPlayerSlideDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PlayerScreen(
                        modifier = Modifier.padding(innerPadding),
                        mediaRepository = mediaRepository
                    )
                }
            }
        }
    }
}