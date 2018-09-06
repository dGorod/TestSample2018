package ua.dgorod.sample

import android.app.Application
import android.os.Looper
import com.squareup.leakcanary.LeakCanary
import com.squareup.picasso.Picasso
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.android.schedulers.AndroidSchedulers
import org.koin.android.ext.android.startKoin
import org.koin.android.logger.AndroidLogger
import org.koin.core.Koin
import timber.log.Timber
import ua.dgorod.sample.data.di.apiModule
import ua.dgorod.sample.data.di.dbModule
import ua.dgorod.sample.di.reposModule

/**
 * Created by dgorodnytskyi on 6/5/18.
 */
class App: Application() {

    companion object {
        val appModules = listOf(apiModule, dbModule, reposModule)
    }

    override fun onCreate() {
        super.onCreate()

        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        LeakCanary.install(this)
        //BlockCanary.install(this, AppBlockCanaryContext()).start()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Koin.logger = AndroidLogger()

            val built = Picasso.Builder(this).indicatorsEnabled(true).build()
            Picasso.setSingletonInstance(built)
        }

        startKoin(this, appModules)

        // Enable RxAndroid Async API
        RxAndroidPlugins.setInitMainThreadSchedulerHandler {
            AndroidSchedulers.from(Looper.getMainLooper(), true)
        }
    }
}