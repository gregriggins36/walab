package com.example.skeleton.main

import com.example.skeleton.app.App
import com.example.skeleton.app.AppComponent
import com.example.skeleton.scope.FeatureScope
import dagger.Component

@FeatureScope
@Component(
    dependencies = [AppComponent::class],
    modules = [MainModule::class]
)
interface MainComponent {
    fun inject(mainActivity: MainActivity)

    val app: App
}
