package com.example.skeleton.feature.model

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("largeImage") val largeImage: String,
    val name: String,
    @SerializedName("salePrice") val salePrice: Float
)

data class ProductsResult(
    @SerializedName("nextPage") val nextPage: String,
    val items: List<Product>
)
