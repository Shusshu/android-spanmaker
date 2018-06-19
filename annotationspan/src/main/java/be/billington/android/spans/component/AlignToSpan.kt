package be.billington.android.spans.component

import android.text.Layout
import android.text.style.AlignmentSpan

class AlignToSpan : TagSpanner() {

    override val tagNames: List<String> = listOf("align")

    override fun toSpans(tagAttr: TagAttr): Array<Any> {
        val alignment: Layout.Alignment = when (tagAttr.attribute) {
            ALIGN_CENTER -> Layout.Alignment.ALIGN_CENTER
            ALIGN_NORMAL -> Layout.Alignment.ALIGN_NORMAL
            ALIGN_OPPOSITE -> Layout.Alignment.ALIGN_OPPOSITE
            else -> Layout.Alignment.ALIGN_CENTER
        }
        return arrayOf(AlignmentSpan.Standard(alignment))
    }

    companion object {

        const val ALIGN_CENTER = "center"
        const val ALIGN_NORMAL = "normal"
        const val ALIGN_OPPOSITE = "opposite"

    }

}
