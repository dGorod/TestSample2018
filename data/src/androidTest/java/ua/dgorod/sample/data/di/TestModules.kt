package ua.dgorod.sample.data.di

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.Module
import org.koin.dsl.module.module
import org.mockito.Mockito.mock
import ua.dgorod.sample.data.api.ApiInterface
import ua.dgorod.sample.data.db.MyDatabase

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