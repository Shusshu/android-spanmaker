package be.billington.android.spans.component

open class ImageSpeechToSpan() : TagSpanner() {

    override val tagNames: List<String> = listOf("img", "image")

    override fun toSpans(tagAttr: TagAttr): Array<Any> {
        return emptyArray()
    }

}
