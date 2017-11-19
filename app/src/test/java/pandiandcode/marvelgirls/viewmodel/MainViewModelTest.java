package pandiandcode.marvelgirls.viewmodel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.schedulers.TestScheduler;
import pandiandcode.databoundary.ComicData;
import pandiandcode.databoundary.ComicListData;
import pandiandcode.databoundary.ComicRepository;
import pandiandcode.domain.usecases.ComicsParam;
import pandiandcode.domain.usecases.GetComicsUseCase;
import pandiandcode.marvelgirls.navigation.Navigator;
import pandiandcode.marvelgirls.viewmodel.comics.MainViewModel;

import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by Rocio Ortega on 19/11/2017.
 */

public class MainViewModelTest {

    @Mock
    GetComicsUseCase mGetComicsUseCase;

    @Mock
    Navigator mNavigator;

    @Captor
    ArgumentCaptor<ComicsParam> mParamCaptor;

    MainViewModel mViewModel;

    @Before
    public void setup() {
        initMocks(this);
        mViewModel = new MainViewModel(mNavigator, mGetComicsUseCase);
    }

    @Test
    public void progressIsDisplayed() {
        Assert.assertThat(mViewModel.isProgressVisible(), is(true));
    }

    @Ignore @Test
    public void requestDataPerformedOnLoad() {
        Observable<ComicListData> comicObservable = Observable.just(comicListData());
        comicObservable.subscribeOn(new TestScheduler())
                .observeOn(new TestScheduler());

        when(mGetComicsUseCase.run(any(ComicsParam.class))).thenReturn(comicObservable);

        mViewModel.onLoad(null);
        verify(mGetComicsUseCase).run(mParamCaptor.capture());
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
