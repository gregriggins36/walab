package com.example.skeleton.feature;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.example.skeleton.R;
import com.example.skeleton.feature.model.Product;

public class ProductPageView extends CardView {
    @BindView(R.id.product_title) TextView mProductTitle;
    @BindView(R.id.product_price) TextView mProductPrice;
    @BindView(R.id.product_image) ImageView mProductImage;

    public ProductPageView(Context context) {
        super(context);
        initialize();
    }

    public ProductPageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public ProductPageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    private void initialize() {
        inflate(getContext(), R.layout.view_product_page, this);
        ButterKnife.bind(this);
        setElevation(getResources().getDimensionPixelSize(R.dimen.spacing_xsmall));
        setUseCompatPadding(true);
    }

    public void setContent(@NonNull Product product) {
        mProductTitle.setText(product.getName());
        mProductPrice.setText(String.valueOf(product.getSalePrice()));
        Glide.with(getContext())
                .load(product.getLargeImage())
                .into(mProductImage);
    }
}
