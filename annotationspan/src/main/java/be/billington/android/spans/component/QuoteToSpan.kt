package be.billington.android.spans.component

import android.text.style.QuoteSpan

class QuoteToSpan : TagSpanner() {

    override val tagNames: List<String> = listOf("quote", "code")

    override fun toSpans(tagAttr: TagAttr): Array<Any> {
        return arrayOf(QuoteSpan())
    }

}
