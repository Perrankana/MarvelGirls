package pandiandcode.data


import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.MockitoAnnotations.initMocks
import pandiandcode.data.datasource.MarvelDataSource
import pandiandcode.data.repository.MarvelRepository
import pandiandcode.databoundary.ComicData
import pandiandcode.databoundary.ComicListData
import pandiandcode.databoundary.ComicRepository

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class MarvelRepositoryTest {

    @Mock
    lateinit var mRemoteDataSource: MarvelDataSource

    @Captor
    lateinit var mComicListData : ArgumentCaptor<ComicListData>

    lateinit var mMarvelRepository: ComicRepository

    @Before
    fun setup(){
        initMocks(this)
        mMarvelRepository = MarvelRepository(mRemoteDataSource)
    }

    @Test
    fun thatRemoteRequestPerformed() {
//        Mockito.when(mRemoteDataSource.getComics(1234)).themReturn(Observable.just(comicListData()))
//
//         mMarvelRepository.getComics(1234)
//
//        verify(mRemoteDataSource).getComics(1234)


    }

    fun comicListData() : ComicListData = ComicListData(getComicDataList())

    fun getComicDataList() : ArrayList<ComicData> {
        var comicList = ArrayList<ComicData>()
        comicList.add(ComicData(4321, "comic name", "comic description", "thumbnailImage", "image"))
        return comicList
    }
}