package pandiandcode.databoundary

import io.reactivex.Observable


/**
 * Created by Rocio Ortega on 17/11/2017.
 */
interface ComicRepository {

    fun getComics(characterId: Int) : Observable<ComicListData>

}