package com.tabi.nytimes.ui.fragments.articles.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.tabi.nytimes.R
import com.tabi.nytimes.databinding.ArticleItemBinding
import com.tabi.nytimes.domain.model.Article
import javax.inject.Inject

class ArticleAdapter @Inject constructor() :
    RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {
    lateinit var onItemClick: (Article) -> Unit
    private var articles: ArrayList<Article> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding: ArticleItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.article_item, parent, false
        )

        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article: Article = articles[position]
        holder.onBind(article)
    }

    override fun getItemCount(): Int {
        return if (articles.isEmpty()) {
            0
        } else {
            articles.size
        }
    }

    fun addNewData(newMovieList: ArrayList<Article>) {
        articles.clear()
        articles.addAll(newMovieList)
        notifyDataSetChanged()
    }

    fun clearData() {
        val movieListSize: Int = articles.size
        articles.clear()
        notifyItemRangeRemoved(0, movieListSize)

    }

    inner class ArticleViewHolder(private val articleItemBinding: ArticleItemBinding) :
        RecyclerView.ViewHolder(articleItemBinding.root) {

        fun onBind(article: Article) {

            article.imgUrl = getQualityImage(article)

            articleItemBinding.article = article

            articleItemBinding.root.setOnClickListener {
                if (this@ArticleAdapter::onItemClick.isInitialized) {
                    onItemClick.invoke(article)
                }
            }
        }

    }

    fun getQualityImage(article: Article): String? {
        var maxWidth = 0
        var url: String? = null
        article.media.forEach { media ->
            media.media_metadata.forEach {
                val width = it.width?.toInt()
                if (width!! > maxWidth) {
                    maxWidth = width
                    url = it.url
                }
            }
        }
        return url
    }
}