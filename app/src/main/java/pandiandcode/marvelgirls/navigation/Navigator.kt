package pandiandcode.marvelgirls.navigation

import android.content.Context
import pandiandcode.marvelgirls.view.ComicDetailActivity

/**
 * Created by Rocio Ortega on 18/11/2017.
 */
open class Navigator(var context: Context) {

    fun onComicSelected(comicId: Int) {
        context.startActivity(ComicDetailActivity.newIntent(context, comicId))
    }
}