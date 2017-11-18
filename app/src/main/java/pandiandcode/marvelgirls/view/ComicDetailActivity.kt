package pandiandcode.marvelgirls.view

import android.content.Context
import android.content.Intent
import org.koin.android.ext.android.inject
import pandiandcode.marvelgirls.R
import pandiandcode.marvelgirls.viewmodel.BaseViewModel
import pandiandcode.marvelgirls.viewmodel.comicdescription.ComicDetailViewModel

/**
 * Created by Rocio Ortega on 18/11/2017.
 */
class ComicDetailActivity : BaseActivity() {

    private val viewModel by inject<ComicDetailViewModel>()

    override fun getViewModel(): BaseViewModel = viewModel

    override fun getLayout(): Int = R.layout.activity_comic_description

    companion object {

        private val INTENT_COMIC_ID = "user_id"

        fun newIntent(context: Context, comicId: Int): Intent {
            val intent = Intent(context, ComicDetailActivity::class.java)
            intent.putExtra(INTENT_COMIC_ID, comicId)
            return intent
        }
    }
}


