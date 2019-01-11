package com.example.skeleton.ui;

import android.content.Context;
import android.support.annotation.IdRes;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.widget.ViewAnimator;

/**
 * ViewAnimator which lets you specify the child to display given an ID.
 *
 * Source: https://github.com/JakeWharton/u2020/blob/master/src/main/java/com/jakewharton/u2020/ui/misc/BetterViewAnimator.java
 */
public class BetterViewAnimator extends ViewAnimator {
    public BetterViewAnimator(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setDisplayedChildId(@IdRes int id) {
        if (getDisplayedChildId() == id) {
            return;
        }
        for (int i = 0, count = getChildCount(); i < count; i++) {
            if (getChildAt(i).getId() == id) {
                setDisplayedChild(i);
                return;
            }
        }
        String name = getResources().getResourceEntryName(id);
        throw new IllegalArgumentException("No view with ID " + name);
    }

    public void setDisplayedChildIdWithoutAnimation(@IdRes int id) {
        Animation inAnimation = getInAnimation();
        Animation outAnimation = getOutAnimation();
        setInAnimation(null);
        setOutAnimation(null);
        setDisplayedChildId(id);
        setInAnimation(inAnimation);
        setOutAnimation(outAnimation);
    }

    @IdRes
    public int getDisplayedChildId() {
        return getChildAt(getDisplayedChild()).getId();
    }
}
