package pandiandcode.marvelgirls.viewmodel.comics

import android.databinding.Bindable
import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import pandiandcode.databoundary.ComicListData
import pandiandcode.domain.usecases.ComicsParam
import pandiandcode.domain.usecases.GetComicsUseCase
import pandiandcode.marvelgirls.BR
import pandiandcode.marvelgirls.viewmodel.BaseViewModel

/**
 * Created by Rocio Ortega on 18/11/2017.
 */
class MainViewModel(comicsUseCase: GetComicsUseCase) : BaseViewModel() {

    var mComicsUseCase = comicsUseCase
    var mComicsList = ObservableArrayList<ComicItemViewModel>()

    @Bindable
    fun isProgressVisible(): Boolean = true

    fun setProgressVisible(progressVisible: Boolean) {

    }

    @Bindable
    fun getComicsList(): ObservableList<ComicItemViewModel> = mComicsList

    override fun onLoad() {
        mComicsUseCase.run(ComicsParam())
                .doFinally({ setProgressVisible(false) })
                .subscribe(this::onComicsDataListLoaded)

    }

    fun onComicsDataListLoaded(comicListData: ComicListData) {
        mComicsList.clear()
        comicListData.comicList.forEach {
            mComicsList.add(ComicItemViewModel(it.name, it.thumbnail))
        }
        notifyPropertyChanged(BR.comicsList)
    }
}