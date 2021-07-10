package com.storemate.storemate

import androidx.activity.viewModels
import com.eutor.core.base.BaseActivity
import com.eutor.core.util.ConnectivityManager
import com.storemate.storemate.databinding.ActivityMainBinding

import com.storemate.storemate.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding,MainViewModel>() {

    private val mainViewModel: MainViewModel by viewModels()
    @Inject
    lateinit var connectivityManager: ConnectivityManager

    override val layoutId: Int
        get() = R.layout.activity_main

    override fun getVM(): MainViewModel =mainViewModel

    override fun bindVM(binding: ActivityMainBinding, vm: MainViewModel) = Unit

}