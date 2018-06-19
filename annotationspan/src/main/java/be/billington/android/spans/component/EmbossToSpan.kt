package be.billington.android.spans.component

import android.graphics.EmbossMaskFilter
import android.text.style.MaskFilterSpan

class EmbossToSpan : TagSpanner() {

    override val tagNames: List<String> = listOf("emboss")

    override fun toSpans(tagAttr: TagAttr): Array<Any> {
        return arrayOf(embossSpan())
    }

    companion object {

        fun embossSpan(): MaskFilterSpan {
            return MaskFilterSpan(EmbossMaskFilter(floatArrayOf(1f, 1f, 1f), 0.4f, 6f, 3.5f))
        }
    }

}
