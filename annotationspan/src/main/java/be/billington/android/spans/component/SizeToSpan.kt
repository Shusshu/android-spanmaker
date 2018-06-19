package be.billington.android.spans.component

import android.text.style.RelativeSizeSpan

open class SizeToSpan(private val defaultSize: Float = 1.0f) : TagSpanner() {

    override val tagNames: List<String> = listOf("size", "relsize")

    override fun toSpans(tagAttr: TagAttr): Array<Any> {
        val size = if (tagAttr.attribute != null && tagAttr.attribute.isNotEmpty()) {
            tagAttr.attribute.toFloat()
        } else {
            defaultSize
        }
        return arrayOf(RelativeSizeSpan(size))
    }

}

class BigSizeToSpan(defaultBigSize: Float = 1.25f) : SizeToSpan(defaultBigSize) {

    override val tagNames: List<String> = listOf("big")

}

class SmallSizeToSpan(defaultSmallSize: Float = 0.8f) : SizeToSpan(defaultSmallSize) {

    override val tagNames: List<String> = listOf("small")

}



