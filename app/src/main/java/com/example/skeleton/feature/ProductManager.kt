package com.example.skeleton.feature

import com.example.skeleton.feature.model.ProductsResult
import com.example.skeleton.feature.net.ProductNetworkRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ProductManager @Inject constructor(private val productNetworkRepository: ProductNetworkRepository) {
    fun products() : Single<ProductsResult> {
        return productNetworkRepository.products()
            .subscribeOn(Schedulers.io())
    }
}
