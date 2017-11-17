package pandiandcode.marvelgirls

import android.app.Application
import org.koin.android.ext.android.startAndroidContext
import pandiandcode.marvelgirls.di.myAppModules

/**
 * Created by Rocio Ortega on 17/11/2017.
 */
class MarvelApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startAndroidContext(this, myAppModules())
    }
}