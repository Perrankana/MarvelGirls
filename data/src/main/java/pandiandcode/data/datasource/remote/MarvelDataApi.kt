package pandiandcode.data.datasource.remote

import io.reactivex.Observable

import pandiandcode.data.datasource.remote.dto.ComicList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Rocio Ortega on 17/11/2017.
 */
interface MarvelDataApi {

    @GET("v1/public/characters/{characterId}/comics")
    fun getComics(@Path("characterId") characterId: Int) : Observable<Response<ComicList>>

}