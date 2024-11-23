package id.yuana.exoplayerslidedemo.data.repository

import android.content.Context
import id.yuana.exoplayerslidedemo.data.model.Media
import kotlinx.serialization.json.Json

interface MediaRepository {

    suspend fun all(): List<Media>

    class Impl(
        private val context: Context
    ) : MediaRepository {

        override suspend fun all(): List<Media> {
            val raw = context.assets.open("media.json").use { it.bufferedReader().readText() }
            return Json.decodeFromString<List<Media>>(raw).map { it.copy(
                file = "asset:///media/${it.file}"
            ) }
        }

    }
}