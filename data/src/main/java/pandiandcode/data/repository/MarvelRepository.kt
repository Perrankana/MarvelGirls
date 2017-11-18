package pandiandcode.data.repository

import io.reactivex.Observable
import pandiandcode.data.datasource.MarvelDataSource
import pandiandcode.databoundary.ComicData
import pandiandcode.databoundary.ComicListData
import pandiandcode.databoundary.ComicRepository

/**
 * Created by Rocio Ortega on 17/11/2017.
 */
class MarvelRepository(marvelDataSource: MarvelDataSource, localSource: MarvelDataSource) : ComicRepository {

    var mRemoteSource = marvelDataSource
    var mLocalSource = localSource

    override fun getComics(characterId: Int): Observable<ComicListData> =
            mRemoteSource.getComics(characterId)
                    .doOnNext { comicListData ->
                        mLocalSource.saveComics(characterId, comicListData)
                    }

    override fun getComic(comicId: Int): Observable<ComicData> = mLocalSource.getComic(comicId)
}