package com.example.skeleton.feature.net

import com.example.skeleton.API_KEY
import com.example.skeleton.feature.model.ProductsResult
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ProductNetworkRepository @Inject constructor(private val productService: ProductService) {
    fun products(): Single<ProductsResult> = productService.products(API_KEY, null)
            .subscribeOn(Schedulers.io())

    fun products(nextPage: String): Single<ProductsResult> {
        return productService.products(API_KEY, parseMaxId(nextPage))
            .subscribeOn(Schedulers.io())
    }

    private fun parseMaxId(nextPage: String): Int? {
        val split = nextPage.split("maxId=")
        return if (split.size > 1) {
            val maxId = split[1].split("&")
            if (maxId.size > 1) {
                maxId[0].toInt()
            } else {
                null
            }
        } else {
            null
        }
    }
}
