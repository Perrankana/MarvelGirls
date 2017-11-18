package pandiandcode.marvelgirls.viewmodel.comics

import android.databinding.Bindable
import pandiandcode.marvelgirls.BR
import pandiandcode.marvelgirls.viewmodel.BaseViewModel

/**
 * Created by Rocio Ortega on 18/11/2017.
 */
class ComicItemViewModel(name: String, image: String) : BaseViewModel() {

    var mName = name
    var mImage = image

    init {
        notifyPropertyChanged(BR.name)
        notifyPropertyChanged(BR.image)
    }

    @Bindable
    fun getName(): String = mName

    @Bindable
    fun getImage(): String = mImage

    fun onItemClicked(source: Object) {

    }

    override fun onLoad() {

    }
}