package be.billington.android.spans.component

import android.graphics.Typeface
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import androidx.core.text.bold

class LabelToSpan : TagSpanner() {

    override val tagNames: List<String> = listOf("l", "label")

    override fun transform(tagAttr: TagAttr, body: CharSequence): SpannableStringBuilder {
        return SpannableStringBuilder("\n").bold { append(body) }.append("\n")
    }

    override fun toSpans(tagAttr: TagAttr): Array<Any> {
        return arrayOf(StyleSpan(Typeface.BOLD))
    }

}
