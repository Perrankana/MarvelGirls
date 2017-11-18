package pandiandcode.domain

import io.reactivex.Observable
import io.reactivex.Scheduler

/**
 * Created by Rocio Ortega on 18/11/2017.
 */
abstract class QueryUseCase<R, in T>(workScheduler: Scheduler, observeScheduler: Scheduler) {

    private var mWorkScheduler: Scheduler = workScheduler
    private var mObserveScheduler: Scheduler = observeScheduler

    fun run(params: T): Observable<R> = buildUseCaseObservable(params)
                .subscribeOn(mWorkScheduler)
                .observeOn(mObserveScheduler)


    protected abstract fun buildUseCaseObservable(params: T): Observable<R>

}