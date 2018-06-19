package be.billington.android.spans.component

import android.text.style.URLSpan

class PhoneToSpan : TagSpanner() {

    override val tagNames: List<String> = listOf("phone")

    override fun toSpans(tagAttr: TagAttr): Array<Any> {
        val attribute = tagAttr.attribute
        val url = "tel:$attribute"
        return arrayOf(URLSpan(url))
    }

}
