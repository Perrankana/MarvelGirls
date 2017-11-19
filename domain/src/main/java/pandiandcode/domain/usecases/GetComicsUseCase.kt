package pandiandcode.domain.usecases

import io.reactivex.Observable
import io.reactivex.Scheduler
import pandiandcode.databoundary.ComicListData
import pandiandcode.databoundary.ComicRepository
import pandiandcode.domain.QueryUseCase
import java.util.*

/**
 * Created by Rocio Ortega on 18/11/2017.
 */
open class GetComicsUseCase(workScheduler: Scheduler, observeScheduler: Scheduler, marvelRepository: ComicRepository) :
QueryUseCase<ComicListData,
        ComicsParam>(workScheduler, observeScheduler) {

    var mMarvelRepository: ComicRepository = marvelRepository

    override fun buildUseCaseObservable(params: ComicsParam): Observable<ComicListData>
    = mMarvelRepository.getComics(params.characterId)

}

class ComicsParam {
    var characterId: Int = getRandomComicCharacter()

    private fun getRandomComicCharacter(): Int =
            getMarvelGirlsCharacters()[getRandomNumber(getMarvelGirlsCharacters().size - 1, 0)]
}

fun getRandomNumber(to: Int, from: Int): Int = Random().nextInt(to - from) + from

fun getMarvelGirlsCharacters(): IntArray = intArrayOf(1009629, 1009157, 1009609, 1009189, 1010338, 1009438, 1017577,
        1009562, 1010971)