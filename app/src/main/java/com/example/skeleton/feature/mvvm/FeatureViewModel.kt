package com.example.skeleton.feature.mvvm

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.skeleton.R
import com.example.skeleton.feature.ProductAdapter.OnProductActionListener
import com.example.skeleton.feature.ProductManager
import com.example.skeleton.feature.model.ProductsResult
import com.example.skeleton.mvvm.BaseViewModel
import com.example.skeleton.util.Event
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposables
import timber.log.Timber

class FeatureViewModel(
    application: Application,
    productManager: ProductManager) : BaseViewModel(application), OnProductActionListener {
    val state = FeatureState()
    var productsDisposable = Disposables.empty()

    init {
        productsDisposable = productManager.products()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onProductSuccess, ::onProductFail)
    }

    private fun onProductSuccess(productsResult: ProductsResult) {
        state.apply {
            viewAnimatorId.postValue(R.id.view_pager)
            products.addAll(productsResult.items)
            onItemFetched.postValue(Event(true))
        }
    }

    private fun onProductFail(t: Throwable) {
        // adjust error message based on Throwable type
        state.viewAnimatorId.postValue(R.id.error_frame)
        Timber.d(t)
    }

    override fun onProductClick(position: Int) {

    }

    override fun onCleared() {
        productsDisposable.dispose()
    }

    class Factory(
        private val application: Application,
        private val productManager: ProductManager
    ) : ViewModelProvider.AndroidViewModelFactory(application) {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>) = FeatureViewModel(
            application,
            productManager
        ) as T
    }
}
