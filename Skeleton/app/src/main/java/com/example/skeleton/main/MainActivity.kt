package com.example.skeleton.main

import com.example.skeleton.R
import com.example.skeleton.app.App.Companion.app
import com.example.skeleton.mvvm.BaseActivity

class MainActivity : BaseActivity<MainViewModel, MainViewModel.Factory, MainComponent>() {
    override val layoutResId = R.layout.activity_main
    override val viewModelClass = MainViewModel::class.java

    override fun createComponent(): MainComponent = DaggerMainComponent.builder()
        .appComponent(app.component)
        .mainModule(MainModule())
        .build()
        .also { it.inject(this) }
}