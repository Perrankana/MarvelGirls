package pandiandcode.data.datasource.remote

import io.reactivex.Observable
import pandiandcode.data.datasource.MarvelDataSource
import pandiandcode.data.datasource.remote.mapper.toComicListData
import pandiandcode.databoundary.ComicData
import pandiandcode.databoundary.ComicListData

/**
 * Created by Rocio Ortega on 17/11/2017.
 */
class RetrofitDataSource(private val mMarvelDataApi: MarvelDataApi) : MarvelDataSource {

    override fun getComics(characterId: Int, characterName: String): Observable<ComicListData> =
            mMarvelDataApi.getComics(characterId)
                    .flatMap { comicListResponse -> Observable.just(comicListResponse.body()) }
                    .flatMap { comicList -> Observable.just(toComicListData(comicList, characterName)) }

    override fun containsCharacterComic(characterId: Int): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getComic(comicId: Int): Observable<ComicData> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveComics(characterId: Int, comicListData: ComicListData) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}