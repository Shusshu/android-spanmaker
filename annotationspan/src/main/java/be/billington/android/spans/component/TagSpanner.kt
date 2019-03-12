package be.billington.android.spans.component

import android.text.SpannableStringBuilder
import androidx.core.text.inSpans

abstract class TagSpanner {

    abstract val tagNames: List<String>

    open fun transform(tagAttr: TagAttr, body: CharSequence): SpannableStringBuilder {
        return SpannableStringBuilder().inSpans(*toSpans(tagAttr)) { append(body) }
    }

    abstract fun toSpans(tagAttr: TagAttr): Array<Any>

}
