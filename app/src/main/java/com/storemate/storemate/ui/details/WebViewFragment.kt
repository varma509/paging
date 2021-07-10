package com.storemate.storemate.ui.details

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.eutor.core.base.BaseFragment

import com.storemate.storemate.R
import com.storemate.storemate.databinding.FragmentWebViewBinding
import com.storemate.storemate.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class WebViewFragment: BaseFragment<FragmentWebViewBinding, MainViewModel>() {

    private val detailViewModel: MainViewModel by viewModels()
    private val args: WebViewFragmentArgs by navArgs()

   override val layoutId: Int
        get() = R.layout.fragment_web_view

    override fun getVM(): MainViewModel = detailViewModel

    override fun bindVM(binding: FragmentWebViewBinding, vm: MainViewModel) {

        with(binding) {

            webView.apply {
                settings.javaScriptEnabled = true
                loadUrl(args.data)
                requestFocus()
            }

        }
    }

    override val title: String
        get() = "WebView"

    override val arrow: Boolean
        get() =true

}