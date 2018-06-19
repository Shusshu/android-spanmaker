package be.billington.android.spans.component

import android.text.style.BulletSpan

class BulletToSpan : TagSpanner() {

    override val tagNames: List<String> = listOf("bullet")

    override fun toSpans(tagAttr: TagAttr): Array<Any> {
        return arrayOf(if (tagAttr.attribute != null && !tagAttr.attribute.isEmpty()) {
            try {
                BulletSpan(Integer.valueOf(tagAttr.attribute))
            } catch (ex: NumberFormatException) {
                BulletSpan()
            }

        } else {
            BulletSpan()
        })
    }

}
