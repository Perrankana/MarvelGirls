package pandiandcode.data.repository

import io.reactivex.Observable
import pandiandcode.data.datasource.MarvelDataSource
import pandiandcode.databoundary.ComicListData
import pandiandcode.databoundary.ComicRepository

/**
 * Created by Rocio Ortega on 17/11/2017.
 */
class MarvelRepository(marvelDataSource: MarvelDataSource) : ComicRepository {

    var mRemoteSource = marvelDataSource

    override fun getComics(characterId: Int): Observable<ComicListData> = mRemoteSource.getComics(characterId)

}