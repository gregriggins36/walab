package com.example.skeleton.feature;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import com.example.skeleton.R;
import com.example.skeleton.feature.model.Product;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ProductAdapter extends PagerAdapter {

    private final ArrayList<Product> mProducts;
    private final ProductPageView[] mProductPageViews;
    private final OnProductActionListener mOnProductActionListener;
    //private final int mInitialPosition;
    private final int mCardElevation;
    private boolean mFirstTimeEnteringView = true;
    private ProductPageView mInitiallySelectedCardView;

    public ProductAdapter(
            @NonNull Context context,
            @NonNull ArrayList<Product> products,
            @NonNull OnProductActionListener onProductActionListener) {
        mOnProductActionListener = onProductActionListener;
        mProducts = products;
        mProductPageViews = new ProductPageView[mProducts.size()];
        //mInitialPosition = initialPosition;
        mCardElevation = context.getResources().getDimensionPixelSize(R.dimen.spacing_xsmall);
    }

    @NotNull
    @Override
    public View instantiateItem(@NotNull ViewGroup container, int position) {
        ProductPageView tutorialPageView = mProductPageViews[position];
        if (tutorialPageView == null) {
            tutorialPageView = new ProductPageView(container.getContext());
            mProductPageViews[position] = tutorialPageView;
        }

        tutorialPageView.setCardElevation(mCardElevation);
        tutorialPageView.setUseCompatPadding(true);

        tutorialPageView.setContent(mProducts.get(position));
        tutorialPageView.setOnClickListener(view -> mOnProductActionListener.onProductClick(position));
        container.addView(tutorialPageView);
//        if (mFirstTimeEnteringView && position == mInitialPosition) {
//            mInitiallySelectedCardView = tutorialPageView;
//            mOnProductActionListener.onInitialProductCardInstantiated();
//            mFirstTimeEnteringView = false;
//        }
        return tutorialPageView;
    }

    public void fadeInInitiallySelectedCardContent() {
        if (mInitiallySelectedCardView != null) {
           // mInitiallySelectedCardView.showActionAndBody(true, true);
        }
    }

    public ProductPageView getView(int position) {
        return mProductPageViews[position];
    }

    @Override
    public void destroyItem(@NotNull ViewGroup container, int position, @NotNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return mProducts.size();
    }

    @Override
    public boolean isViewFromObject(@NotNull View view, @NotNull Object object) {
        return (view == object);
    }

    public interface OnProductActionListener {
        /**
         * Called when the initially selected card is instantiated. This is necessary since by default
         * the view pager will instantiate at least two items when its first created, so we need to keep
         * track of the initial one to hide its elements and start the animations.
         */
        void onInitialProductCardInstantiated();

        void onProductClick(int position);
    }
}
