package com.tabi.nytimes.ui.fragments.articles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.tabi.nytimes.databinding.FragmentArticlesBinding
import com.tabi.nytimes.ui.activities.main.MainViewModel
import com.tabi.nytimes.ui.fragments.articles.adapter.ArticleAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ArticlesFragment : Fragment() {
    private lateinit var binding: FragmentArticlesBinding

    @Inject
    lateinit var articleAdapter: ArticleAdapter
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentArticlesBinding.inflate(
            layoutInflater, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeRecyclerViewAdapter()
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = mainViewModel

        onMovieClickListener()
        initObservers()

    }

    private fun initObservers() {
        mainViewModel.refreshArticles.observe(viewLifecycleOwner) {
            if (mainViewModel.isMenuClicked) {
                mainViewModel.isMenuClicked = false
                mainViewModel.isLoading.postValue(true)
                mainViewModel.isResultFound.postValue(false)
                binding.rvArticles.visibility = View.GONE
                mainViewModel.getArticles(it)
            }
        }
    }

    private fun initializeRecyclerViewAdapter() {
        binding.rvArticles.adapter = articleAdapter
    }

    private fun onMovieClickListener() {
        articleAdapter.onItemClick = {
            mainViewModel.clickedArticle.postValue(it)
            val detailFragmentDirection =
                ArticlesFragmentDirections.actionArticleFragmentToDetailFragment()

            findNavController().navigate(detailFragmentDirection)
        }
    }

}