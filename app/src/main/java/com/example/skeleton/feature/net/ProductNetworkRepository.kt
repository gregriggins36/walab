package com.example.skeleton.feature.net

import com.example.skeleton.API_KEY
import com.example.skeleton.feature.model.ProductsResult
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ProductNetworkRepository @Inject constructor(private val productService: ProductService) {
    fun products() : Single<ProductsResult> {
        return productService.products(API_KEY, null)
            .subscribeOn(Schedulers.io())
    }
}
