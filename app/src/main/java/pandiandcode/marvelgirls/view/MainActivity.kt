package pandiandcode.marvelgirls.view

import android.os.Bundle
import org.koin.android.ext.android.inject
import pandiandcode.marvelgirls.R
import pandiandcode.marvelgirls.navigation.Navigator
import pandiandcode.marvelgirls.viewmodel.BaseViewModel
import pandiandcode.marvelgirls.viewmodel.comics.MainViewModel

class MainActivity : BaseActivity() {

    private val viewModel by inject<MainViewModel>()

    override fun getViewModel(): BaseViewModel = viewModel

    override fun getLayout(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.onLoad()

        val navigator = object: Navigator{
            override fun onComicSelected(comicId: Int) {
                goToComicDetail(comicId)
            }
        }

        viewModel.setNavigator(navigator)
    }

    fun goToComicDetail(comicId: Int) {
        startActivity(ComicDetailActivity.newIntent(this, comicId))
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.setNavigator(null)
    }
}
