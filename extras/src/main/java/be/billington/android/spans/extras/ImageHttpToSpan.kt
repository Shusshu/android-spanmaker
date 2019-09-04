package be.billington.android.spans.extras

import android.content.Context
import android.util.Log
import be.billington.android.spans.component.ImageResToSpan
import be.billington.android.spans.component.ImageUrl.Companion.HTTP
import okhttp3.OkHttpClient
import okhttp3.Request
import okio.Okio
import okio.buffer
import okio.sink
import okio.source
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException

open class ImageHttpToSpan(private val context: Context,
                           private val client: OkHttpClient = OkHttpClient()) : ImageResToSpan(context) {

    override fun canProcess(url: String): Boolean {
        return if (!url.startsWith(HTTP)) {
            super.canProcess(url)
        } else true
    }

    override fun processUrl(url: String): String {
        if (!url.startsWith(HTTP)) {
            return super.processUrl(url)
        }

        try {
            val response = client.newCall(Request.Builder().url(url).build()).execute()

            val body = response.body ?: return if (super.canProcess(url)) super.processUrl(url) else ""
            val inputStream = body.byteStream()

            val downloadedFile = File.createTempFile("img", "span", context.cacheDir)

            val sink = downloadedFile.sink().buffer()
            sink.use {
                inputStream.use {
                    val source = inputStream.source()
                    sink.writeAll(source)
                }
            }

            return "file://" + downloadedFile.toString()

        } catch (e: FileNotFoundException) {
            Log.e("ImageHttpToSpan", e.message, e)
        } catch (e: IOException) {
            Log.e("ImageHttpToSpan", e.message, e)
        }

        return if (super.canProcess(url)) super.processUrl(url) else ""
    }

}
