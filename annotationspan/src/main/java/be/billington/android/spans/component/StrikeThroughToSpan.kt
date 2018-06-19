package be.billington.android.spans.component

import android.text.SpannableStringBuilder
import android.text.style.StrikethroughSpan
import androidx.core.text.strikeThrough

class StrikeThroughToSpan : TagSpanner() {

    override val tagNames: List<String> = listOf("s", "strike", "del")

    override fun transform(tagAttr: TagAttr, body: CharSequence): SpannableStringBuilder {
        return SpannableStringBuilder().strikeThrough { append(body) }
    }

    override fun toSpans(tagAttr: TagAttr): Array<Any> {
        return arrayOf(StrikethroughSpan())
    }

}
