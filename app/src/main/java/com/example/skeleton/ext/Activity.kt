package com.example.skeleton.ext

import android.app.Activity
import com.example.skeleton.app.App

val Activity.app
    get() = application as App
