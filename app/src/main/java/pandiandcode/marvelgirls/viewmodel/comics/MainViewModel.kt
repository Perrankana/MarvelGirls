package pandiandcode.marvelgirls.viewmodel.comics

import android.databinding.Bindable
import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import android.os.Bundle
import pandiandcode.databoundary.ComicListData
import pandiandcode.domain.usecases.ComicsParam
import pandiandcode.domain.usecases.GetComicsUseCase
import pandiandcode.marvelgirls.BR
import pandiandcode.marvelgirls.navigation.Navigator
import pandiandcode.marvelgirls.viewmodel.BaseViewModel

/**
 * Created by Rocio Ortega on 18/11/2017.
 */
class MainViewModel(navigator: Navigator, comicsUseCase: GetComicsUseCase) : BaseViewModel() {

    var mComicsUseCase = comicsUseCase
    var mComicsList = ObservableArrayList<ComicItemViewModel>()
    var mProgressVisible = true

    private var mNavigator: Navigator = navigator

    @Bindable
    fun isProgressVisible(): Boolean = mProgressVisible

    fun setProgressVisible(progressVisible: Boolean) {
        mProgressVisible = progressVisible
        notifyPropertyChanged(BR.progressVisible)
    }

    @Bindable
    fun getComicsList(): ObservableList<ComicItemViewModel> = mComicsList

    override fun onLoad(bundle : Bundle?) {
        mComicsUseCase.run(ComicsParam())
                .doFinally({ setProgressVisible(false) })
                .subscribe(this::onComicsDataListLoaded)

    }

    private fun onComicsDataListLoaded(comicListData: ComicListData) {
        mComicsList.clear()
        comicListData.comicList.forEach {
            mComicsList.add(ComicItemViewModel( mNavigator,it.id, it.name, it.thumbnail))
        }
        notifyPropertyChanged(BR.comicsList)
    }
}