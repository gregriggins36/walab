package com.example.skeleton.feature

import com.example.skeleton.app.App
import com.example.skeleton.app.AppComponent
import com.example.skeleton.feature.mvvm.FeatureFragment
import com.example.skeleton.net.NetworkModule
import com.example.skeleton.scope.FeatureScope
import dagger.Component

@FeatureScope
@Component(
        dependencies = [AppComponent::class],
        modules = [FeatureModule::class, NetworkModule::class]
)
interface FeatureComponent {
    fun inject(featureFragment: FeatureFragment)

    val app: App
}
