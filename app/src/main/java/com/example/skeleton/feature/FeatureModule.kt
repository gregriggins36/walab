package com.example.skeleton.feature

import com.example.skeleton.BASE_API_URL
import com.example.skeleton.app.App
import com.example.skeleton.feature.mvvm.FeatureViewModel
import com.example.skeleton.feature.net.ProductService
import com.example.skeleton.net.RestClient
import com.example.skeleton.scope.FeatureScope
import dagger.Module
import dagger.Provides

@Module
class FeatureModule {
    @Provides
    @FeatureScope
    fun featureViewModelFactory(app: App, productManager: ProductManager) =
        FeatureViewModel.Factory(app, productManager)

    @Provides
    @FeatureScope
    fun productService(restClient: RestClient): ProductService =
            restClient.createRetrofitAdapter(BASE_API_URL).create(ProductService::class.java)
}
