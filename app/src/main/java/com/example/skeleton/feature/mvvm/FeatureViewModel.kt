package com.example.skeleton.feature.mvvm

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.skeleton.R
import com.example.skeleton.feature.ProductAdapter.OnProductActionListener
import com.example.skeleton.feature.ProductManager
import com.example.skeleton.feature.model.Product.Companion.ERROR_PRODUCT
import com.example.skeleton.feature.model.ProductsResult
import com.example.skeleton.mvvm.BaseViewModel
import com.example.skeleton.util.Event
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposables
import timber.log.Timber

const val ITEM_THRESHOLD_TO_FETCH_MORE = 5

class FeatureViewModel(
    application: Application,
    private val productManager: ProductManager) : BaseViewModel(application), OnProductActionListener {
    val state = FeatureState()
    var productsDisposable = Disposables.empty()

    init {
        productsDisposable = productManager.products()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onProductSuccess, ::onProductFail)
    }

    private fun onProductSuccess(productsResult: ProductsResult) {
        state.apply {
            if (productsResult.items.isNullOrEmpty()) {
                viewAnimatorId.postValue(R.id.empty_content)
            } else {
                viewAnimatorId.postValue(R.id.view_pager)
                products.addAll(productsResult.items)
                nextPage.postValue(productsResult.nextPage)
                onItemFetched.postValue(Event(true))
            }
        }
    }

    private fun onProductFail(t: Throwable) {
        // adjust error message based on Throwable type
        state.viewAnimatorId.postValue(R.id.error_frame)
        Timber.d(t)
    }

    override fun onProductClick(position: Int) {

    }

    fun onPageSelected(position: Int) {
        if (position == state.products.size - ITEM_THRESHOLD_TO_FETCH_MORE) {
            getNextPage()
        }
    }

    private fun getNextPage() {
        productsDisposable = productManager.products(state.nextPage.value)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onNextPageProductSuccess, ::onNextPageProductFail)
    }

    private fun onNextPageProductSuccess(productsResult: ProductsResult) {
        state.apply {
            products.addAll(productsResult.items)
            nextPage.postValue(productsResult.nextPage)
            notifyDataSetChanged.postValue(Event(true))
        }
    }

    private fun onNextPageProductFail(t: Throwable) {
        state.apply {
            products.add(ERROR_PRODUCT)
            notifyDataSetChanged.postValue(Event(true))
        }
        Timber.d(t)
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
