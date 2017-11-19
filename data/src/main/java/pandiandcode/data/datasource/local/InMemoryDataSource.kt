package pandiandcode.data.datasource.local

import io.reactivex.Observable
import pandiandcode.data.datasource.MarvelDataSource
import pandiandcode.databoundary.ComicData
import pandiandcode.databoundary.ComicListData

/**
 * Created by Rocio Ortega on 18/11/2017.
 */
class InMemoryDataSource : MarvelDataSource {

    override fun getComics(characterId: Int, characterName: String): Observable<ComicListData> {
        return when (characterId) {
            ComicStorage.characterId -> Observable.just(ComicStorage.comicListData)
            else -> Observable.just(null)
        }
    }

    override fun containsCharacterComic(characterId: Int) = ComicStorage.characterId == characterId

    override fun getComic(comicId: Int): Observable<ComicData> {
        ComicStorage.comicListData?.comicList?.forEach { comicData ->
            if (comicData.id == comicId) {
                return Observable.just(comicData)
            }
        }
        return Observable.just(null)
    }

    override fun saveComics(characterId: Int, comicListData: ComicListData) {
        ComicStorage.characterId = characterId
        ComicStorage.comicListData = comicListData
    }
}