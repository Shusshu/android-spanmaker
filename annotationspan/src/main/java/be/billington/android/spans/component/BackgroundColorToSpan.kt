package be.billington.android.spans.component

import android.graphics.Color
import android.text.style.BackgroundColorSpan

class BackgroundColorToSpan : TagSpanner() {

    override val tagNames: List<String> = listOf("bgcolor", "backgroundcolor")

    override fun toSpans(tagAttr: TagAttr): Array<Any> {
        val color = Color.parseColor(tagAttr.attribute)
        return arrayOf(BackgroundColorSpan(color))
    }

}
