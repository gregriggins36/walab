package com.example.skeleton.feature.net

import com.example.skeleton.feature.model.ProductsResult
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductService {
    @GET("paginated/items")
    fun products(@Query("apiKey") apiKey: String,
                 @Query("maxId") maxId: Int?): Single<ProductsResult>
}
