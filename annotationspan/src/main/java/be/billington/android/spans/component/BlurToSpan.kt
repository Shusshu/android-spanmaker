package be.billington.android.spans.component

import android.graphics.BlurMaskFilter
import android.text.style.MaskFilterSpan

class BlurToSpan(private val defaultRadius: Float = 22.5f) : TagSpanner() {

    override val tagNames: List<String> = listOf("blur")

    override fun toSpans(tagAttr: TagAttr): Array<Any> {
        val radius = tagAttr.attribute?.toFloat() ?: defaultRadius
        return arrayOf(blurSpan(radius))
    }

    companion object {

        fun blurSpan(radius: Float): MaskFilterSpan {
            return MaskFilterSpan(BlurMaskFilter(radius, BlurMaskFilter.Blur.NORMAL))
        }

    }

}
