package com.example.skeleton.feature

import com.example.skeleton.app.App
import com.example.skeleton.feature.mvvm.FeatureViewModel
import com.example.skeleton.scope.FeatureScope
import dagger.Module
import dagger.Provides

@Module
class FeatureModule {
    @Provides
    @FeatureScope
    fun provideFeedViewModelFactory(app: App) =
        FeatureViewModel.Factory(app)
}
