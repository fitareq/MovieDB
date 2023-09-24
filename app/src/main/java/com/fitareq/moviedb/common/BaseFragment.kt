package com.fitareq.moviedb.common

import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {

    protected fun showLoadingScreen() {
        if (activity is BaseActivity)
            (activity as BaseActivity).showLoadingScreen()
    }

    protected fun hideLoadingScreen() {
        if (activity is BaseActivity)
            (activity as BaseActivity).hideLoadingScreen()
    }

}