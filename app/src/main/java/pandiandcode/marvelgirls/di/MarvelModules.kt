package pandiandcode.marvelgirls.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.module.AndroidModule
import pandiandcode.data.datasource.MarvelDataSource
import pandiandcode.data.datasource.local.InMemoryDataSource
import pandiandcode.data.datasource.remote.MarvelDataApi
import pandiandcode.data.datasource.remote.RetrofitDataSource
import pandiandcode.data.repository.MarvelRepository
import pandiandcode.databoundary.ComicRepository
import pandiandcode.domain.usecases.GetComicUseCase
import pandiandcode.domain.usecases.GetComicsUseCase
import pandiandcode.marvelgirls.utils.generateMd5
import pandiandcode.marvelgirls.viewmodel.comicdescription.ComicDetailViewModel
import pandiandcode.marvelgirls.viewmodel.comics.MainViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by Rocio Ortega on 17/11/2017.
 */

fun myAppModules() = listOf(MyModule(), RemoteDataSourceModule())

class MyModule : AndroidModule() {
    override fun context() = applicationContext {

        context(name = "MainActivity") {
            provide { MainViewModel(get()) }
        }

        context(name = "ComicDetailActivity") {
            provide { ComicDetailViewModel(get()) }
        }

        provide { provideGetComicsUseCase(get()) }

        provide { provideGetComicUseCase(get()) }

        provide("remoteDataSource") { provideRemoteDataSource(get()) }

        provide("localDataSource") { provideLocalDataSource() }

        provide { provideComicRepository(get("remoteDataSource"), get("localDataSource")) }
    }


}

fun provideGetComicsUseCase(marvelRepository: ComicRepository): GetComicsUseCase
        = GetComicsUseCase(Schedulers.io(), AndroidSchedulers.mainThread(), marvelRepository)

fun provideGetComicUseCase(marvelRepository: ComicRepository): GetComicUseCase
        = GetComicUseCase(Schedulers.io(), AndroidSchedulers.mainThread(), marvelRepository)

fun provideRemoteDataSource(marvelDataApi: MarvelDataApi): MarvelDataSource
        = RetrofitDataSource(marvelDataApi)

fun provideLocalDataSource(): MarvelDataSource
        = InMemoryDataSource()

fun provideComicRepository(remoteDataSource: MarvelDataSource, localDataSource: MarvelDataSource): ComicRepository
        = MarvelRepository(remoteDataSource, localDataSource)

class RemoteDataSourceModule : AndroidModule() {
    override fun context() = applicationContext {

        provide { provideMarvelDataApi(get()) }

        provide { provideRetrofit(get(), get()) }

        provide { provideGson() }

        provide { provideOkHttpClient(get()) }

        provide { provideAuthorizationInterceptor() }
    }
}

fun provideOkHttpClient(authorizationInterceptor: Interceptor): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

    return OkHttpClient.Builder()
            .connectTimeout(60L, TimeUnit.SECONDS)
            .readTimeout(60L, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(authorizationInterceptor)
            .build()

}

fun provideGson(): Gson = GsonBuilder()
        .setDateFormat("")
        .create()

fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient) =
        Retrofit.Builder()
                .baseUrl("http://gateway.marvel.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()

fun provideAuthorizationInterceptor(): Interceptor =
        Interceptor { chain ->

            val ts = Date().time
            val publicKey = "6a7ed890b4b941a925202a5630d5b162"
            val privateKey = "0f1d0fdf46a0bf32f962b0b9997233c0395cdf8e"
            var request = chain.request()
            val url = request.url().newBuilder()
                    .addQueryParameter("apikey", publicKey)
                    .addQueryParameter("ts", ts.toString())
                    .addQueryParameter("hash", generateMd5(ts, privateKey, publicKey))
                    .build()
            request = request.newBuilder().url(url).build()
            chain.proceed(request)
        }

fun provideMarvelDataApi(retrofit: Retrofit): MarvelDataApi
        = retrofit.create(MarvelDataApi::class.java)
