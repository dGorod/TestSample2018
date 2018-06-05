package ua.dgorod.sample.data.di

import org.koin.android.ext.android.androidApplication
import org.koin.dsl.module.Module
import org.koin.dsl.module.module
import ua.dgorod.sample.data.db.MyDatabase

/**
 * Singleton bean. Provides local database.
 *
 * @author Dmytro Gorodnytskyi
 * on 12-Dec-17.
 */
val dbModule: Module = module {

    bean { MyDatabase.getInstance(androidApplication()) }
}