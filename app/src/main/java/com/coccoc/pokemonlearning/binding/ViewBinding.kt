package com.coccoc.pokemonlearning.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

object ViewBinding {
    @JvmStatic
    @BindingAdapter("imgGlide")
    fun loadImage(view: ImageView?, url: String?) {
        view?.context?.let {
            Glide.with(it)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .into(view)
        }
    }
}