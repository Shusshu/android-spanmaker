package be.billington.android.spans.component

import android.content.Context
import android.graphics.Typeface
import be.billington.android.spans.CustomTypefaceSpan

class FontToSpan(private val context: Context) : TagSpanner() {

    override val tagNames: List<String> = listOf("font")

    override fun toSpans(tagAttr: TagAttr): Array<Any> {
        val attribute = tagAttr.attribute
        val font = Typeface.createFromAsset(context.assets, attribute)
        return arrayOf(CustomTypefaceSpan(font))
    }

}
