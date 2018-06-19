package be.billington.android.spans

import android.text.Annotation
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannedString
import be.billington.android.spans.component.TagAttr
import be.billington.android.spans.component.TagSpanner

class AnnotationSpanWorker(components: List<TagSpanner>) {

    private var componentsMap: Map<String, TagSpanner> = components.flatMap { it.tagNames.map { name -> name to it } }.toMap()

    fun spannify(text: SpannedString): SpannableString {

        val annotations = text.getSpans(0, text.length, Annotation::class.java)

        val spannedText = SpannableString(text)

        annotations.forEach { annotation ->
            val startIdx = text.getSpanStart(annotation)
            val endIdx = text.getSpanEnd(annotation)

            val spans = componentsMap[annotation.key]?.toSpans(TagAttr(annotation.value))
            spans?.forEach {
                spannedText.setSpan(it, startIdx, endIdx, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
        }

        return spannedText

    }

}
