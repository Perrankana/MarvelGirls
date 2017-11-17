package pandiandcode.marvelgirls.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.module.AndroidModule
import pandiandcode.data.datasource.MarvelDataSource
import pandiandcode.data.datasource.remote.MarvelDataApi
import pandiandcode.data.datasource.remote.RetrofitDataSource
import pandiandcode.data.repository.MarvelRepository
import pandiandcode.databoundary.ComicRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.experimental.and
import kotlin.experimental.or

/**
 * Created by Rocio Ortega on 17/11/2017.
 */

fun myAppModules() = listOf(MyModule(), RemoteDataSourceModule())

class MyModule : AndroidModule() {
    override fun context() = applicationContext {

        context(name = "MainActivity") {
            provide { }
        }

        provide { provideMarvelDataSource(get()) }

        provide { provideComicRepository(get()) }
    }
}

fun provideMarvelDataSource(marvelDataApi: MarvelDataApi): MarvelDataSource
        = RetrofitDataSource(marvelDataApi)

fun provideComicRepository(marvelDataSource: MarvelDataSource): ComicRepository
        = MarvelRepository(marvelDataSource)

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

            val ts = Date().time.toString()
            val publicKey = "6a7ed890b4b941a925202a5630d5b162"
            val privateKey = "0f1d0fdf46a0bf32f962b0b9997233c0395cdf8e"
            var request = chain.request()
            val url = request.url().newBuilder()
                    .addQueryParameter("apikey", publicKey)
                    .addQueryParameter("ts", ts)
                    .addQueryParameter("hash", md5(ts + privateKey + publicKey))
                    .build()
            request = request.newBuilder().url(url).build()
            chain.proceed(request)
        }

fun provideMarvelDataApi(retrofit: Retrofit): MarvelDataApi
        = retrofit.create(MarvelDataApi::class.java)


fun md5(md5: String): String? {
    try {
        val md = java.security.MessageDigest.getInstance("MD5")
        val array = md.digest(md5.toByteArray())
        val sb = StringBuffer()
        for (i in array.indices) {
            sb.append(Integer.toHexString((array[i] and 0xFF.toByte() or 0x100.toByte()).toInt()).substring(1, 3))
        }
        return sb.toString()
    } catch (e: java.security.NoSuchAlgorithmException) {
    }

    return null
}