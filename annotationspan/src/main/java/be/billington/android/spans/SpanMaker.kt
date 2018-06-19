package be.billington.android.spans

import android.content.Context
import be.billington.android.spans.component.*

object SpanVanillaMaker {

    fun create(): AnnotationSpanWorker = AnnotationSpanWorker(components())

    fun components(): List<TagSpanner> {
        return listOf(
                AlignToSpan(),
                BackgroundColorToSpan(),
                BoldToSpan(),
                BlurToSpan(),
                BulletToSpan(),
                EmbossToSpan(),
                FontFamilyToSpan(),
                ForegroundColorToSpan(),
                ItalicToSpan(),
                LabelToSpan(),
                PhoneToSpan(),
                QuoteToSpan(),
                SizeToSpan(),
                BigSizeToSpan(),
                SmallSizeToSpan(),
                StrikeThroughToSpan(),
                StyleToSpan(),
                SubscriptToSpan(),
                SuperscriptToSpan(),
                UnderlineToSpan(),
                UrlToSpan()
        )
    }

}


object SpanContextMaker {

    fun create(context: Context): AnnotationSpanWorker = AnnotationSpanWorker(components(context))

    fun components(context: Context): List<TagSpanner> {
        return SpanVanillaMaker.components() +
                listOf(
                        FontToSpan(context),
                        ImageResToSpan(context)
                )
    }
}
