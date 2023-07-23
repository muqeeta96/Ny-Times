package com.tabi.nytimes.ui.bindingAdapter

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tabi.nytimes.domain.model.ArticleResponse
import com.tabi.nytimes.ui.fragments.articles.adapter.ArticleAdapter

object ViewBindingAdapter {

    @BindingAdapter("imageResource")
    @JvmStatic
    fun setImage(imageView: ImageView, url: String?) {
        if (url != null) {
            Glide.with(imageView.context).load(url).into(imageView)
        }
    }


    @BindingAdapter("setArticlesAdapter")
    @JvmStatic
    fun setArticlesAdapter(rvArticles: RecyclerView, articleResponse: ArticleResponse?) {
        if (articleResponse == null) {
            return
        }
        val adapter = rvArticles.adapter as ArticleAdapter
        if (articleResponse.results.isEmpty() && adapter.itemCount == 0) {
            adapter.clearData()
        } else {
            adapter.addNewData(articleResponse.results)
        }
    }

}