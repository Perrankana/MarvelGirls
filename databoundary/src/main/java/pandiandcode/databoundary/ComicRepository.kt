package pandiandcode.databoundary

import io.reactivex.Observable


/**
 * Created by Rocio Ortega on 17/11/2017.
 */
interface ComicRepository {

    fun getComics(characterId: Int, characterName: String) : Observable<ComicListData>

    fun getComic(comicId: Int) : Observable<ComicData>
}