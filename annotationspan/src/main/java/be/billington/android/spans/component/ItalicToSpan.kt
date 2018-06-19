package be.billington.android.spans.component

import android.graphics.Typeface
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import androidx.core.text.italic

class ItalicToSpan : TagSpanner() {

    override val tagNames: List<String> = listOf("i", "italic")

    override fun transform(tagAttr: TagAttr, body: CharSequence): SpannableStringBuilder {
        return SpannableStringBuilder().italic { append(body) }
    }

    override fun toSpans(tagAttr: TagAttr): Array<Any> {
        return arrayOf(StyleSpan(Typeface.ITALIC))
    }

}
