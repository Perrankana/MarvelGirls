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

        viewModel.onLoad(savedInstanceState)
    }
}
