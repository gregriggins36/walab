package com.example.skeleton.feature.mvvm

import com.example.skeleton.R
import com.example.skeleton.feature.model.Product
import com.example.skeleton.util.Event
import com.umairjavid.kombind.model.MutableLiveArrayList
import com.umairjavid.kombind.model.MutableLiveField

class FeatureState {
    val products = MutableLiveArrayList<Product>()
    val onItemFetched = MutableLiveField<Event<Boolean>>()
    val viewAnimatorId = MutableLiveField(R.id.progress_frame)
    val apiErrorMessage = MutableLiveField(R.string.generic_error_message)

    operator fun invoke(func: FeatureState.() -> Unit) = func()
}
