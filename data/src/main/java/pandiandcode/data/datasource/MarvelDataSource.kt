package pandiandcode.data.datasource

import io.reactivex.Observable
import pandiandcode.databoundary.ComicData
import pandiandcode.databoundary.ComicListData


/**
 * Created by Rocio Ortega on 17/11/2017.
 */
interface MarvelDataSource {

    fun getComics(characterId: Int) : Observable<ComicListData>

}