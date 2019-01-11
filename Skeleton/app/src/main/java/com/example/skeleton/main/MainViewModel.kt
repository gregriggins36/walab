package com.example.skeleton.main

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.skeleton.mvvm.BaseViewModel
import com.example.skeleton.util.Event

open class MainViewModel(application: Application) : BaseViewModel(application){
    val onUploadSuccess = MutableLiveData<Event<Boolean>>()

    class Factory(
        private val application: Application) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>) = MainViewModel(application) as T
    }
}