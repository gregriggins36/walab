package com.example.skeleton.ui;

import android.support.v4.view.ViewPager;
import android.view.View;
import org.jetbrains.annotations.NotNull;

/**
 * PageTransformer that scales down inactive pages and animates the transition when scrolling
 * through pages.
 */
public class ProductPageTransformer implements ViewPager.PageTransformer {
    private static final float MIN_SCALE = 0.85f;

    @Override
    public void transformPage(@NotNull View page, float position) {
        int pageWidth = page.getWidth();
        int pageHeight = page.getHeight();
        float verticalMargin = pageHeight * (1 - MIN_SCALE) / 2;
        float horizontalMargin = pageWidth * (1 - MIN_SCALE) / 2;

        page.setScaleX(MIN_SCALE);
        page.setScaleY(MIN_SCALE);

        if (position < -1) {
            page.setTranslationX(horizontalMargin - verticalMargin / 2);
        } else if (position <= 1) {
            float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));

            verticalMargin = pageHeight * (1 - scaleFactor) / 2;
            horizontalMargin = pageWidth * (1 - scaleFactor) / 2;

            if (position < 0) {
                page.setTranslationX(horizontalMargin - verticalMargin / 2);
            } else {
                page.setTranslationX(-horizontalMargin + verticalMargin / 2);
            }

            page.setScaleX(scaleFactor);
            page.setScaleY(scaleFactor);
        } else {
            page.setTranslationX(-horizontalMargin + verticalMargin / 2);
        }
    }
}
