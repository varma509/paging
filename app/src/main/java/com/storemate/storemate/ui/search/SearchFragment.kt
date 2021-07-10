package com.storemate.storemate.ui.search

import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.eutor.core.base.BaseFragment


import dagger.hilt.android.AndroidEntryPoint
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadState
import com.eutor.core.data.models.search.SearchItem
import com.eutor.core.listener.OnItemClickListener
import com.eutor.core.util.PagingLoadStateAdapter

import com.eutor.core.util.RxSearchWidget
import com.storemate.storemate.R
import com.storemate.storemate.databinding.FragmentSearchBinding
import com.storemate.storemate.ui.adapters.GitMembersAdapter
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel>(),
    RxSearchWidget.RxSearchListener {


    private var _binding: FragmentSearchBinding? = null


    private var searchJob: Job? = null
    private val binding get() = _binding!!

    private val searchViewModel: SearchViewModel by viewModels()
     private lateinit var adapter: GitMembersAdapter

    override val layoutId: Int
        get() = R.layout.fragment_search
    override val title: String
        get() = "Search"
    override val arrow: Boolean
        get() = true

    override fun getVM(): SearchViewModel =searchViewModel

    private val onItemClickListener = object : OnItemClickListener<SearchItem> {
        override fun onItemClick(item: SearchItem, position: Int) {
            findNavController().navigate(
                SearchFragmentDirections.actionToDetail(item.owner)
            )
        }
    }

    @ExperimentalPagingApi
    override fun bindVM(binding: FragmentSearchBinding, vm: SearchViewModel) =with(binding) {
            _binding=binding
            adapter = GitMembersAdapter(onItemClickListener)
            RxSearchWidget(binding.searchET, this@SearchFragment)
            binding.list.adapter = adapter
            binding.lifecycleOwner = this@SearchFragment
            with(adapter) {
                binding.list.adapter = withLoadStateHeaderAndFooter(
                    header = PagingLoadStateAdapter(this),
                    footer = PagingLoadStateAdapter(this)
                )
                launchOnLifecycleScope {
                    loadStateFlow.distinctUntilChangedBy { it.refresh }
                        .filter { it.refresh is LoadState.NotLoading }
                        .collectLatest { binding.list.scrollToPosition(0) }
                }
            }
        binding.searchET.setText("Paging")


           adapter.addLoadStateListener { loadState ->

            // show empty list
          /*  val isListEmpty = loadState.refresh is LoadState.NotLoading && adapter.itemCount == 0
            showEmptyList(isListEmpty)*/
            val errorState = loadState.source.append as? LoadState.Error
                ?: loadState.source.prepend as? LoadState.Error
                ?: loadState.append as? LoadState.Error
                ?: loadState.prepend as? LoadState.Error
            errorState?.let {
                Toast.makeText(
                    requireContext(),
                    "\uD83D\uDE28 Wooops ${it.error}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        }


    @ExperimentalPagingApi
    override fun onSearchTextChange(query: String?) {
        query?.let {
            initSearch(query)
        }
    }

    @ExperimentalPagingApi
    fun initSearch(query: String) {
        searchJob?.cancel()
        val data=viewModel.getSearch(query)
      searchJob=  lifecycleScope.launch {
            data.collectLatest{ adapter.submitData(it)  }
        }
    }

    private fun showEmptyList(show: Boolean) {
        if (show) {
            binding.emptyList.visibility = View.VISIBLE
            binding.list.visibility = View.GONE
        } else {
            binding.emptyList.visibility = View.GONE
            binding.list.visibility = View.VISIBLE
        }
    }

}