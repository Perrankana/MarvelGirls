package pandiandcode.marvelgirls.viewmodel.comics

import android.databinding.Bindable
import android.os.Bundle
import pandiandcode.marvelgirls.BR
import pandiandcode.marvelgirls.navigation.Navigator
import pandiandcode.marvelgirls.viewmodel.BaseViewModel

/**
 * Created by Rocio Ortega on 18/11/2017.
 */
class ComicItemViewModel(navigator: Navigator?, id: Int, name: String, image: String) : BaseViewModel() {

    var mNavigator = navigator
    var mId = id
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
        mNavigator?.onComicSelected(mId)
    }

    override fun onLoad(bundle: Bundle?) {

    }
}