package com.storemate.storemate.ui.details

import android.os.Bundle
import android.transition.TransitionInflater
import androidx.core.view.ViewCompat
import com.eutor.core.base.BaseFragment


import dagger.hilt.android.AndroidEntryPoint
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.eutor.core.data.models.search.SearchItem
import com.eutor.core.databinding.DetailsFragmentBinding
import com.eutor.core.listener.OnItemClickListener

import com.storemate.storemate.R
import com.storemate.storemate.databinding.FragmentSearchBinding
import com.storemate.storemate.ui.search.SearchFragmentDirections
import com.storemate.storemate.ui.search.SearchViewModel
import android.content.Intent
import android.net.Uri


@AndroidEntryPoint
class DetailsFragment : BaseFragment<DetailsFragmentBinding, DetailViewModel>(),OnItemClickListener<String>{

    private val detailViewModel: DetailViewModel by viewModels()
    private val args: DetailsFragmentArgs by navArgs()

    override val layoutId: Int
        get() = R.layout.details_fragment

    override fun getVM(): DetailViewModel = detailViewModel

  /*  override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }*/

    override fun bindVM(binding: DetailsFragmentBinding, vm: DetailViewModel) {

        with(binding) {
            details = args.owner
            binding.listener=this@DetailsFragment
        }
    }

    override val title: String
        get() = "Details"
    override val arrow: Boolean
        get() =true

    override fun onItemClick(item: String, position: Int) {
        findNavController().navigate(
            DetailsFragmentDirections.actionDetailsToWebView2(item)
        )
    }
}