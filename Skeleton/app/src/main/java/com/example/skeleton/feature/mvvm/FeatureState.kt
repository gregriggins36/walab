package com.example.skeleton.feature.mvvm

class FeatureState {
    operator fun invoke(func: FeatureState.() -> Unit) = func()
}
