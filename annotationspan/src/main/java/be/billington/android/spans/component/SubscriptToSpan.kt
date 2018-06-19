package be.billington.android.spans.component

import android.text.style.SubscriptSpan

class SubscriptToSpan : TagSpanner() {

    override val tagNames: List<String> = listOf("subscript")

    override fun toSpans(tagAttr: TagAttr): Array<Any> {
        return arrayOf(SubscriptSpan())
    }

}
