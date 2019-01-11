package com.example.skeleton.feature.mvvm

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.skeleton.feature.ProductAdapter.OnProductActionListener
import com.example.skeleton.feature.model.Product
import com.example.skeleton.mvvm.BaseViewModel
import com.example.skeleton.util.Event

class FeatureViewModel(application: Application) : BaseViewModel(application), OnProductActionListener {
    val state = FeatureState()

    lateinit var p1: Product
    lateinit var p2: Product

    init {
        initMocks()
        state.apply {
            products.add(p1)
            products.add(p2)
            onItemFetched.postValue(Event(true))
        }
    }

    override fun onInitialProductCardInstantiated() {

    }

    override fun onProductClick(position: Int) {

    }

    class Factory(
            private val application: Application
    ) : ViewModelProvider.AndroidViewModelFactory(application) {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>) = FeatureViewModel(
                application
        ) as T
    }

    private fun initMocks() {
        p1 = Product(
                "https://i5.walmartimages.com/asr/4f0ccd91-d321-48ca-9ae5-9a214b7abdeb_1.b2a17e0e5ea5c3ef700b6e25ebb94cf0.jpeg?odnHeight=450&odnWidth=450&odnBg=ffffff",
        "Rose Cottage Girls' Hunter Green Jacket Dress",
        12.2F)

        p2 = Product(
            "https://i5.walmartimages.com/asr/83c838bd-fde5-4392-ae6a-5d93451f7b12_1.df14a0eb01795588590e9840f62fdde2.jpeg?odnHeight=450&odnWidth=450&odnBg=ffffff",
            "Wrangler Men's Relaxed Fit Jeans",
            17.87F)
    }
}
