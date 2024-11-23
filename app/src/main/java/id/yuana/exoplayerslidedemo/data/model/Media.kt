package id.yuana.exoplayerslidedemo.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Media(
    val file: String,
    val type: String,
    val duration: Long = -1
) {
    companion object {
        const val TYPE_VIDEO = "video"
        const val TYPE_IMAGE = "image"
    }
}

fun Media.isVideo(): Boolean = type == Media.TYPE_VIDEO
fun Media.isImage(): Boolean = type == Media.TYPE_IMAGE
