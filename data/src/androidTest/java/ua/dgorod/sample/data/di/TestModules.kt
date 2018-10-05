package ua.dgorod.sample.data.di

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.Module
import org.koin.dsl.module.module
import ua.dgorod.sample.data.db.MyDatabase
import ua.dgorod.sample.data.repository.RepoRepositoryImpl
import ua.dgorod.sample.domain.repository.RepoRepository

/**
 * Created by dgorodnytskyi on 10/4/18.
 */
val testDbModule: Module = module {

    single {
        Room.inMemoryDatabaseBuilder(androidContext(), MyDatabase::class.java)
                .allowMainThreadQueries()
                .build()
    }
}

val testReposModule: Module = module {

    single { RepoRepositoryImpl(get(), get()) as RepoRepository }
}