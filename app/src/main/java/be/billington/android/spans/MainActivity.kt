package be.billington.android.spans

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.SpannedString

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val textSizeToSpan = getText(R.string.size_test) as SpannedString
        size_span.text = SpanVanillaMaker.create().spannify(textSizeToSpan)

        val textColorToSpan = getText(R.string.color_test) as SpannedString
        color_span.text = SpanVanillaMaker.create().spannify(textColorToSpan)

        val textOtherToSpan = getText(R.string.other_test) as SpannedString
        other_span.text = SpanVanillaMaker.create().spannify(textOtherToSpan)
    }

}
