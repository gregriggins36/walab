package com.example.skeleton.feature.mvvm

import com.example.skeleton.R
import com.example.skeleton.ext.app
import com.example.skeleton.feature.DaggerFeatureComponent
import com.example.skeleton.feature.FeatureComponent
import com.example.skeleton.mvvm.BaseFragment

class FeatureFragment : BaseFragment<FeatureViewModel, FeatureViewModel.Factory, FeatureComponent>() {
    override val viewModelClass = FeatureViewModel::class.java
    override val layoutResId = R.layout.fragment_feature

    override fun createComponent(): FeatureComponent = DaggerFeatureComponent
        .builder()
        .appComponent(app.component)
        .build()
        .also { it.inject(this) }
}
