package pandiandcode.domain.usecases

import io.reactivex.Observable
import io.reactivex.Scheduler
import pandiandcode.databoundary.ComicData
import pandiandcode.databoundary.ComicRepository
import pandiandcode.domain.QueryUseCase

/**
 * Created by Rocio Ortega on 18/11/2017.
 */
class GetComicUseCase(workScheduler: Scheduler, observeScheduler: Scheduler, marvelRepository: ComicRepository) :
        QueryUseCase<ComicData, ComicParam>(workScheduler, observeScheduler) {

    var mMarvelRepository: ComicRepository = marvelRepository

    override fun buildUseCaseObservable(params: ComicParam): Observable<ComicData> = mMarvelRepository.getComic(params.comicId)
}

class ComicParam(val comicId: Int)