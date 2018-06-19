package be.billington.android.spans.component

import android.graphics.Typeface
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import androidx.core.text.bold

class BoldToSpan : TagSpanner() {

    override val tagNames: List<String> = listOf("b", "bold")

    override fun transform(tagAttr: TagAttr, body: CharSequence): SpannableStringBuilder {
        return SpannableStringBuilder().bold { append(body) }
    }

    override fun toSpans(tagAttr: TagAttr): Array<Any> {
        return arrayOf(StyleSpan(Typeface.BOLD))
    }

}
