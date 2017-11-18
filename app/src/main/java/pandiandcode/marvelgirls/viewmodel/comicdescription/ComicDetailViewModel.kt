package pandiandcode.marvelgirls.viewmodel.comicdescription

import android.databinding.Bindable
import android.os.Bundle
import pandiandcode.databoundary.ComicData
import pandiandcode.domain.usecases.ComicParam
import pandiandcode.domain.usecases.GetComicUseCase
import pandiandcode.marvelgirls.BR
import pandiandcode.marvelgirls.view.ComicDetailActivity
import pandiandcode.marvelgirls.viewmodel.BaseViewModel
import java.util.*

/**
 * Created by Rocio Ortega on 18/11/2017.
 */
class ComicDetailViewModel(comicUseCase: GetComicUseCase) : BaseViewModel() {

    var mComicUseCase = comicUseCase
    var mName: String = ""
    var mDescription: String = ""
    var mImage: String = ""
    var mProgressVisible: Boolean = true

    @Bindable
    fun isProgressVisible(): Boolean = mProgressVisible

    fun setProgressVisible(progressVisible: Boolean) {
        mProgressVisible = progressVisible
        notifyPropertyChanged(BR.progressVisible)
    }

    @Bindable
    fun getName(): String = mName

    fun setName(name: String) {
        mName = name
        notifyPropertyChanged(BR.name)
    }

    @Bindable
    fun getDescription(): String = mDescription

    fun setDescription(description: String) {
        mDescription = description
        notifyPropertyChanged(BR.description)
    }

    @Bindable
    fun getImage(): String = mImage

    fun setImage(image: String) {
        mImage = image
        notifyPropertyChanged(BR.image)
    }

    override fun onLoad(bundle: Bundle?) {
        bundle?.let {
            var comicId = bundle.getInt(ComicDetailActivity.INTENT_COMIC_ID)
            mComicUseCase.run(ComicParam(comicId))
                    .doFinally({ setProgressVisible(false) })
                    .subscribe(this::onComicLoaded)
        }

    }

    fun onComicLoaded(comicData: ComicData) {
        setName(comicData.name)
        comicData.description?.let { setDescription(it) }
        setImage(comicData.images)
    }

    fun setImage(images: ArrayList<String>) {
        val from = 0
        val to = images.size
        val randomIndex = getRandomNumber(to, from)
        setImage(images[randomIndex])
    }

    fun getRandomNumber(to: Int, from: Int): Int = Random().nextInt(to - from) + from
}