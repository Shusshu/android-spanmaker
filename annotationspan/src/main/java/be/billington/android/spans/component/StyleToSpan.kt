package be.billington.android.spans.component

import android.graphics.Color
import android.text.SpannableStringBuilder
import android.text.style.AbsoluteSizeSpan
import android.text.style.BackgroundColorSpan
import android.text.style.CharacterStyle
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.SubscriptSpan
import android.text.style.SuperscriptSpan
import android.text.style.TypefaceSpan
import androidx.core.text.inSpans

class StyleToSpan : TagSpanner() {

    override val tagNames: List<String> = listOf("style")

    override fun transform(tagAttr: TagAttr, body: CharSequence): SpannableStringBuilder {
        return SpannableStringBuilder().inSpans(toSpans(tagAttr)) { append(body) }
    }

    override fun toSpans(tagAttr: TagAttr): Array<Any> {
        val styles: Map<String, String> = tagAttr.attributes.orEmpty()

        return styles.map {
            createStyleSpan(it.key, it.value)
        }.filterNotNull().toTypedArray()
    }

    private fun createStyleSpan(
            attrName: String,
            attrValue: String): CharacterStyle? {

        return when (attrName) {

            ATTR_COLOR, ATTR_FG_COLOR -> ForegroundColorSpan(Color.parseColor(attrValue))

            ATTR_BG_COLOR -> BackgroundColorSpan(Color.parseColor(attrValue))

            ATTR_SIZE, ATTR_ABS_SIZE -> AbsoluteSizeSpan(Integer.parseInt(attrValue), true)

            ATTR_REL_SIZE -> RelativeSizeSpan(attrValue.toFloat())

            ATTR_FONT_FAMILY -> TypefaceSpan(attrValue)

            ATTR_EMBOSS -> EmbossToSpan.embossSpan()

            ATTR_SUPERSCRIPT -> SuperscriptSpan()

            ATTR_SUBSCRIPT -> SubscriptSpan()

            ATTR_BLUR -> BlurToSpan.blurSpan(attrValue.toFloat())

            else -> null
        }
    }

    companion object {

        const val ATTR_COLOR = "color"
        const val ATTR_SIZE = "size"
        const val ATTR_ABS_SIZE = "abssize"
        const val ATTR_REL_SIZE = "relsize"
        const val ATTR_BG_COLOR = "bgcolor"
        const val ATTR_FG_COLOR = "fgcolor"
        const val ATTR_FONT_FAMILY = "font-family"
        const val ATTR_EMBOSS = "emboss"
        const val ATTR_SUPERSCRIPT = "superscript"
        const val ATTR_SUBSCRIPT = "subscript"
        const val ATTR_BLUR = "blur"

    }

}
