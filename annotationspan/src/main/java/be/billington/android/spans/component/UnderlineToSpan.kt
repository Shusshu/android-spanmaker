package be.billington.android.spans.component

import android.text.SpannableStringBuilder
import android.text.style.UnderlineSpan
import androidx.core.text.underline

class UnderlineToSpan : TagSpanner() {

    override val tagNames: List<String> = listOf("u", "underline")

    override fun transform(tagAttr: TagAttr, body: CharSequence): SpannableStringBuilder {
        return SpannableStringBuilder().underline { append(body) }
    }

    override fun toSpans(tagAttr: TagAttr): Array<Any> {
        return arrayOf(UnderlineSpan())
    }

}
