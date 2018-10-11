package ua.dgorod.sample

import android.app.Application
import org.koin.android.ext.android.startKoin
import ua.dgorod.sample.di.viewModelsModule

/**
 * Created by dgorodnytskyi on 6/5/18.
 */
class TestApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(viewModelsModule))
    }
}