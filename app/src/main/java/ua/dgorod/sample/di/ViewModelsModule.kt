package ua.dgorod.sample.di

import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module
import ua.dgorod.sample.ui.activity.MainActivity
import ua.dgorod.sample.viewmodel.MainViewModel

/**
 * Created by dgorodnytskyi on 10/11/18.
 */
val viewModelsModule: Module = module {

    module(MainActivity::class.java.simpleName) {
        viewModel { MainViewModel(get()) }
    }
}