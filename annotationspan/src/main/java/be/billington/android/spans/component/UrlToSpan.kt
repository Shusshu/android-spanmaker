package be.billington.android.spans.component

import android.text.style.URLSpan

class UrlToSpan : TagSpanner() {

    override val tagNames: List<String> = listOf("url")

    override fun toSpans(tagAttr: TagAttr): Array<Any> {
        val url = tagAttr.attribute
        return arrayOf(URLSpan(url))
    }

}
