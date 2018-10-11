package ua.dgorod.sample.di

import org.koin.dsl.module.Module
import org.koin.dsl.module.module
import ua.dgorod.sample.data.repository.RepoRepositoryImpl
import ua.dgorod.sample.domain.interactor.RepoInteractor
import ua.dgorod.sample.domain.repository.RepoRepository
import ua.dgorod.sample.ui.activity.MainActivity

/**
 * Bean with [MainActivity] context scope. Provides beans for work with repositories.
 *
 * @author Dmytro Gorodnytskyi
 * on 05-Jun-18.
 */
val reposModule: Module = module {

    single { RepoInteractor(get()) }

    single { RepoRepositoryImpl(get(), get()) as RepoRepository }
}