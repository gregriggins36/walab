package com.example.skeleton.feature.mvvm

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.View
import com.example.skeleton.R
import com.example.skeleton.ext.app
import com.example.skeleton.feature.DaggerFeatureComponent
import com.example.skeleton.feature.FeatureComponent
import com.example.skeleton.feature.ProductAdapter
import com.example.skeleton.feature.model.Product
import com.example.skeleton.mvvm.BaseFragment
import com.example.skeleton.ui.ProductPageTransformer
import kotlinx.android.synthetic.main.fragment_feature.*

class FeatureFragment : BaseFragment<FeatureViewModel, FeatureViewModel.Factory, FeatureComponent>() {
    override val viewModelClass = FeatureViewModel::class.java
    override val layoutResId = R.layout.fragment_feature

    override fun createComponent(): FeatureComponent = DaggerFeatureComponent
        .builder()
        .appComponent(app.component)
        .build()
        .also { it.inject(this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.state.onItemFetched.observe(this, Observer {
            renderItems(viewModel.state.products)
        })
    }

    private fun renderItems(items: ArrayList<Product>) {
        val adapter = ProductAdapter(context!!, items, viewModel)
        view_pager.adapter = adapter
        view_pager.setPageTransformer(true, ProductPageTransformer())
        view_pager.offscreenPageLimit = 3
        view_pager.pageMargin = resources.getDimensionPixelSize(R.dimen.spacing_small)
    }
}
