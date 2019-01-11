package com.example.skeleton.binding

import android.databinding.BindingAdapter
import android.graphics.drawable.ColorDrawable
import android.support.v4.content.ContextCompat
import android.widget.ImageView
import com.bumptech.glide.Glide
import java.io.File

object ImageViewBinding {
    @JvmStatic
    @BindingAdapter("imageUrl")
    fun ImageView.setImageUrl(imageUrl: String?) {
        if (!imageUrl.isNullOrBlank()) {
            Glide.with(this.context)
                    .load(imageUrl)
                    .into(this)
        } else {
            Glide.with(this.context).clear(this)
            this.setImageDrawable(ColorDrawable(ContextCompat.getColor(this.context, android.R.color.black)))
        }
    }

    @JvmStatic
    @BindingAdapter("imageFile")
    fun ImageView.setImageFile(imageFile: File?) {
        if (imageFile != null) {
            Glide.with(this.context)
                    .load(imageFile)
                    .into(this)
        }
    }
}
