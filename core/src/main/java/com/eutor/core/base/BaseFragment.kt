package com.eutor.core.base

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.eutor.core.ext.observe


abstract class BaseFragment<DB : ViewDataBinding, VM : BaseViewModel> : Fragment() {
    lateinit var viewModel: VM
    private lateinit var binding: DB

    @get:LayoutRes
    abstract val layoutId: Int


    abstract val title: String

    abstract val arrow:Boolean

    abstract fun getVM(): VM

    abstract fun bindVM(binding: DB, vm: VM)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getVM()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindVM(binding, viewModel)
        with(viewModel) {
            observe(progressLiveEvent) { show ->
                if (show) (activity as BaseActivity<*, *>).showProgress()
                else (activity as BaseActivity<*, *>).hideProgress()
            }

            observe(errorMessage) { msg ->
                Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun launchOnLifecycleScope(execute: suspend () -> Unit) {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            execute()
        }
    }

    @SuppressLint("ResourceType")
    override fun onResume() {
        super.onResume()
       /* (activity as MainActivity).supportActionBar?.title = title

        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(arrow)
        (activity as MainActivity).supportActionBar?.setDisplayShowHomeEnabled(arrow)
        (activity as MainActivity).supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)*/
    }
}