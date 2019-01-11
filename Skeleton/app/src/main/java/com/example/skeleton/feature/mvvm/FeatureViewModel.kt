package com.example.skeleton.feature.mvvm

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.skeleton.mvvm.BaseViewModel

class FeatureViewModel(
        application: Application) : BaseViewModel(application) {
    val state = FeatureState()

    class Factory(
            private val application: Application
    ) : ViewModelProvider.AndroidViewModelFactory(application) {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>) = FeatureViewModel(
                application
        ) as T
    }
}
