package pandiandcode.data;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

import java.util.ArrayList;

import io.reactivex.Observable;
import pandiandcode.data.datasource.MarvelDataSource;
import pandiandcode.data.repository.MarvelRepository;
import pandiandcode.databoundary.ComicData;
import pandiandcode.databoundary.ComicListData;
import pandiandcode.databoundary.ComicRepository;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by Rocio Ortega on 19/11/2017.
 */

public class MarvelRepositoryTest {

    @Mock
    MarvelDataSource mRemoteDataSource;

    @Mock
    MarvelDataSource mLocalDataSource;

    @Captor
    ArgumentCaptor<Integer> mCharacterIdCaptor;

    @Captor
    ArgumentCaptor<Integer> mComicIdCaptor;

    @Captor
    ArgumentCaptor<ComicListData> mComicListCaptor;

    ComicRepository mMarvelRepository;

    @Before
    public void setup() {
        initMocks(this);
        mMarvelRepository = new MarvelRepository(mRemoteDataSource, mLocalDataSource);
    }

    @Test
    public void thatRequestToRemotePerformedIfNoCacheAvailable() {
        when(mLocalDataSource.containsCharacterComic(anyInt())).thenReturn(false);
        when(mRemoteDataSource.getComics(anyInt())).thenReturn(Observable.just(comicListData()));

        mMarvelRepository.getComics(1234);
        verify(mRemoteDataSource).getComics(mCharacterIdCaptor.capture());
    }

    @Test
    public void thatRequestToLocalPerformedIfCacheAvailable() {
        when(mLocalDataSource.containsCharacterComic(anyInt())).thenReturn(true);
        when(mLocalDataSource.getComics(anyInt())).thenReturn(Observable.just(comicListData()));

        mMarvelRepository.getComics(1234);
        verify(mLocalDataSource).getComics(mCharacterIdCaptor.capture());
    }

    @Test
    public void thatRequestToRemoteNeverPerformedIfCacheAvailable() {
        when(mLocalDataSource.containsCharacterComic(anyInt())).thenReturn(true);

        mMarvelRepository.getComics(1234);

        verify(mRemoteDataSource, never()).getComics(mCharacterIdCaptor.capture());
    }

    @Test
    public void thatRequestToLocalNeverPerformedIfCacheNoAvailable() {
        when(mLocalDataSource.containsCharacterComic(anyInt())).thenReturn(false);
        when(mRemoteDataSource.getComics(anyInt())).thenReturn(Observable.just(comicListData()));

        mMarvelRepository.getComics(1234);

        verify(mLocalDataSource, never()).getComics(mCharacterIdCaptor.capture());
    }

    @Ignore @Test
    public void thatRemoteDataIsCached() {

        // needs to figure out how to test doOnNext in Observable

        final ComicListData comicListData = comicListData();

        when(mLocalDataSource.containsCharacterComic(anyInt())).thenReturn(false);
        Observable<ComicListData> remoteObservable = Observable.just(comicListData());

        when(mRemoteDataSource.getComics(anyInt())).thenReturn(remoteObservable);

        mMarvelRepository.getComics(1234);

        verify(mLocalDataSource).saveComics(mCharacterIdCaptor.capture(), comicListData);
    }

    @Test
    public void thatRequestToLocalPerformedIfRequestSingleComic(){
        when(mLocalDataSource.getComic(anyInt())).thenReturn(Observable.just(comicData()));

        mMarvelRepository.getComic(1234);

        verify(mLocalDataSource).getComic(mComicIdCaptor.capture());
    }

    @Test
    public void thatRequestToRemoteNeverPerformIfRequestSingleComic(){
        when(mLocalDataSource.getComic(anyInt())).thenReturn(Observable.just(comicData()));

        mMarvelRepository.getComic(1234);

        verify(mRemoteDataSource, never()).getComic(mComicIdCaptor.capture());
    }

    private ComicListData comicListData() {
        return new ComicListData(getComicDataList());
    }

    private ArrayList<ComicData> getComicDataList() {
        ArrayList<ComicData> comicList = new ArrayList<>();
        comicList.add(comicData());
        return comicList;
    }

    private ComicData comicData() {
        return new ComicData(4321, "comic name", "comic description", "thumbnailImage", new ArrayList<String>());
    }
}
