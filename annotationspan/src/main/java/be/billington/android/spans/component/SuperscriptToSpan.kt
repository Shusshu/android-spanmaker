package be.billington.android.spans.component

import android.text.style.SuperscriptSpan

class SuperscriptToSpan : TagSpanner() {

    override val tagNames: List<String> = listOf("superscript")

    override fun toSpans(tagAttr: TagAttr): Array<Any> {
        return arrayOf(SuperscriptSpan())
    }

}
