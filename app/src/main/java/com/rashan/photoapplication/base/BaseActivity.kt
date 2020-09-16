package com.rashan.photoapplication.base

import androidx.appcompat.app.AppCompatActivity
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.rashan.photoapplication.model.domain.Photo

abstract class BaseActivity : AppCompatActivity() {
    protected inline fun <reified T : ViewDataBinding> binding(
        @LayoutRes resId: Int
    ): Lazy<T> = lazy { DataBindingUtil.setContentView<T>(this, resId) }
}