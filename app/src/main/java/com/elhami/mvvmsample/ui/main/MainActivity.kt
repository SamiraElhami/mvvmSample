package com.elhami.mvvmsample.ui.main

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.elhami.mvvmsample.BR
import com.elhami.mvvmsample.R
import com.elhami.mvvmsample.databinding.ActivityMainBinding
import com.elhami.mvvmsample.di.ViewModelFactory
import com.elhami.mvvmsample.persistence.WordDao
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var mainActivityVM: MainActivityVM

    @Inject
    lateinit var wordDao: WordDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()
        mainActivityVM.loadData(applicationContext)
    }

    /**
     * binding viewmodel to activity
     */
    private fun performDataBinding() {
        val wordBinding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainActivityVM = ViewModelProviders.of(this, ViewModelFactory(this))
            .get(MainActivityVM::class.java)
        wordBinding.setVariable(BR.words, mainActivityVM)
    }
}
