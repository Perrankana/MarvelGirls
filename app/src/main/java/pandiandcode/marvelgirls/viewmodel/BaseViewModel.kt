package pandiandcode.marvelgirls.viewmodel

import android.databinding.BaseObservable
import android.os.Bundle

/**
 * Created by Rocio Ortega on 18/11/2017.
 */
abstract class BaseViewModel : BaseObservable(){
    abstract fun onLoad(bundle: Bundle?)
}