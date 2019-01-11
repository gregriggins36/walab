package com.example.skeleton.ext

import com.umairjavid.kombind.ui.KombindFragment

val KombindFragment<*>.app
    get() = activity!!.app
