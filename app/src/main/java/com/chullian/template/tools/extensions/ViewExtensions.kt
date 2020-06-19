package com.chullian.template.tools.extensions

import android.view.View

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide(isInvisible: Boolean = false) {
    if (isInvisible) this.visibility = View.INVISIBLE else this.visibility = View.GONE
}