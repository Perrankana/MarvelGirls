package pandiandcode.marvelgirls.viewmodel

import android.databinding.Bindable
import android.os.Bundle
import pandiandcode.domain.usecases.ComicsParam
import pandiandcode.domain.usecases.GetComicsUseCase

/**
 * Created by Rocio Ortega on 18/11/2017.
 */
class MainViewModel(comicsUseCase: GetComicsUseCase) : BaseViewModel() {

    var mComicsUseCase = comicsUseCase

    @Bindable
    fun isProgressVisible(): Boolean = true

    fun setProgressVisible(progressVisible: Boolean) {

    }

    override fun onLoad() {
        mComicsUseCase.run(ComicsParam())
                .doFinally({ setProgressVisible(false) })
                .subscribe({ onComicsDataListLoaded() })

    }

    fun onComicsDataListLoaded() {

    }
}