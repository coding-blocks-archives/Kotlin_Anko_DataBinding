package com.codingblocks.kotlinobservables

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import com.codingblocks.kotlinobservables.observables.ObservableReducer
import com.codingblocks.kotlinobservables.observables.StandardObservableProperty
import com.codingblocks.kotlinobservables.viewextensions.bindString
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        MainActivityUI().apply {
            setContentView(this@MainActivity)
        }
    }
}

class MainActivityUI: AnkoComponent<MainActivity> {
    var hello = StandardObservableProperty("Hello")
    var world = StandardObservableProperty("World")
    var helloWorld = ObservableReducer(hello, world) { (h, w) -> h + w }
    override fun createView(ui: AnkoContext<MainActivity>): View  = with(ui) {
        frameLayout {
            verticalLayout {
                editText {
                    bindString(hello)
                }.lparams(matchParent, wrapContent)
                editText {
                    bindString(world)
                }.lparams(matchParent, wrapContent)
                textView {
                    bindString(helloWorld)
                }.lparams(matchParent, wrapContent)
            }.lparams (
                dip(100),
                wrapContent,
                Gravity.CENTER
            )
        }
    }
}
