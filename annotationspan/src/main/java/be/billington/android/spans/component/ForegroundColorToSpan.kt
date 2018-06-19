package be.billington.android.spans.component

import android.graphics.Color
import android.text.style.ForegroundColorSpan

class ForegroundColorToSpan : TagSpanner() {

    override val tagNames: List<String> = listOf("color", "fgcolor", "foregroundcolor")

    override fun toSpans(tagAttr: TagAttr): Array<Any> {
        val color = Color.parseColor(tagAttr.attribute)
        return arrayOf(ForegroundColorSpan(color))
    }

}
