package com.example.skeleton.main

import com.example.skeleton.app.App
import com.example.skeleton.scope.FeatureScope
import dagger.Module
import dagger.Provides

@Module
class MainModule {
    @Provides
    @FeatureScope
    fun provideMainViewModelFactory(app: App) = MainViewModel.Factory(app)
}