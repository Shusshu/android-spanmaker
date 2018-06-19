package be.billington.android.spans.component

import android.text.style.TypefaceSpan

class FontFamilyToSpan : TagSpanner() {

    override val tagNames: List<String> = listOf("font-family")

    override fun toSpans(tagAttr: TagAttr): Array<Any> {
        val attribute = tagAttr.attribute
        return arrayOf(TypefaceSpan(attribute))
    }

}
