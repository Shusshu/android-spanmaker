package be.billington.android.spans.component

import android.content.ContentResolver
import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.text.style.ImageSpan
import android.util.Log
import androidx.core.graphics.drawable.DrawableCompat
import be.billington.android.spans.component.ImageUrl.Companion.HTTP

interface ImageUrl {

    fun canProcess(url: String): Boolean

    fun processUrl(url: String): String

    companion object {

        const val HTTP = "http"

    }
}

open class ImageToSpan(private val context: Context) : TagSpanner(), ImageUrl {

    override val tagNames: List<String> = listOf("img", "image")

    override fun toSpans(tagAttr: TagAttr): Array<Any> {
        val url = tagAttr.attribute

        if (url == null || url.isEmpty()) {
            return emptyArray()
        }
        if (canProcess(url)) {
            val fileUrl = processUrl(url)
            if (fileUrl.isNotBlank()) {
                return arrayOf(ImageSpan(context, Uri.parse(fileUrl)))
            }
        }
        return emptyArray()
    }


    /**
     * Intended to be available for a child class to change the default behavior
     * By default this transformer does not support network calls as they need to be done outside the main thread
     *
     * @param url
     * @return
     */
    override fun canProcess(url: String): Boolean {
        return !url.startsWith(HTTP)
    }

    /**
     * Intended to be available for a child class to change the default behavior
     * By default this transformer does not support network calls as they need to be done outside the main thread
     *
     * @param url
     * @return
     */
    override fun processUrl(url: String): String {
        return url
    }

}


open class ImageResToSpan(private val context: Context) : ImageToSpan(context) {

    override fun toSpans(tagAttr: TagAttr): Array<Any> {
        if (tagAttr.attributes?.containsKey(ATTR_COLOR) == true && tagAttr.attributes.containsKey(ATTR_URL)) {
            val color = tagAttr.attributes[ATTR_COLOR]
            val url = tagAttr.attributes[ATTR_URL].orEmpty()
            if (canProcess(url)) {
                val fileUrl = processUrl(url)
                if (fileUrl.isNotBlank()) {
                    var drawable: Drawable = createDrawable(context, Uri.parse(fileUrl)) ?: return emptyArray()
                    try {
                        val imageTintColor = Color.parseColor(color)
                        drawable = DrawableCompat.wrap(drawable)
                        DrawableCompat.setTint(drawable, imageTintColor)
                        DrawableCompat.setTintMode(drawable, PorterDuff.Mode.SRC_IN)
                    } catch (ex: IllegalArgumentException) {
                        // Unknown color
                    }
                    return arrayOf(ImageSpan(drawable))
                }
            }
            return emptyArray()

        } else {
            return super.toSpans(tagAttr)
        }
    }

    override fun canProcess(url: String): Boolean {
        if (url.startsWith(HTTP)) {
            return false
        }
        var filename = url
        var resType = RES_TYPE_DRAWABLE
        var packageName = context.packageName
        if (url.startsWith(ContentResolver.SCHEME_ANDROID_RESOURCE)) {
            val idx = url.lastIndexOf(SLASH)
            if (idx == -1) {
                return false
            }
            val urlSplit = url.split(SLASH)
            if (urlSplit.size != 5) {
                return false
            }
            packageName = urlSplit[2]
            resType = urlSplit[3]
            filename = urlSplit[4]
            if (RES_TYPE_DRAWABLE != resType) {
                return false
            }
        }
        val drawableResId = context.resources.getIdentifier(filename, resType, packageName)
        return drawableResId != 0
    }

    override fun processUrl(url: String): String {
        return if (url.startsWith(ContentResolver.SCHEME_ANDROID_RESOURCE)) {
            url
        } else makeInAppFileBaseUriStr(url)
    }

    private fun makeInAppFileBaseUriStr(filename: String): String {
        return StringBuilder(ContentResolver.SCHEME_ANDROID_RESOURCE)
                .append("://")
                .append(context.packageName)
                .append(SLASH)
                .append(RES_TYPE_DRAWABLE)
                .append(SLASH)
                .append(filename).toString()
    }

    private fun createDrawable(context: Context, uri: Uri): Drawable? {
        try {
            val inputStream = context.contentResolver.openInputStream(uri)
            inputStream.use {
                val bitmap = BitmapFactory.decodeStream(it)
                val drawable = BitmapDrawable(context.resources, bitmap)
                drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
                return drawable
            }

        } catch (e: Exception) {
            Log.e("ImageResToSpan", "Failed to loaded content $uri", e)
        }
        return null
    }

    companion object {

        private const val RES_TYPE_DRAWABLE = "drawable"
        private const val ATTR_COLOR = "color"
        private const val ATTR_URL = "url"
        private const val SLASH = "/"

    }

}
