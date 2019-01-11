package com.example.skeleton.feature.model

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("largeImage") val largeImage: String,
    val name: String,
    @SerializedName("salePrice") val salePrice: Float
) {
    companion object {
        val ERROR_PRODUCT = Product("", "", 0F)
    }
}

data class ProductsResult(
    @SerializedName("nextPage") val nextPage: String,
    val items: List<Product>
)
