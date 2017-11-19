package pandiandcode.domain.usecases;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.schedulers.TestScheduler;
import pandiandcode.databoundary.ComicData;
import pandiandcode.databoundary.ComicListData;
import pandiandcode.databoundary.ComicRepository;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by Rocio Ortega on 19/11/2017.
 */

public class GetComicsUseCaseTest {

    @Mock
    ComicRepository mRepositoryMock;

    private GetComicsUseCase mUseCase;

    @Before
    public void setup() {
        initMocks(this);
        mUseCase = new GetComicsUseCase(new TestScheduler(), new TestScheduler(), mRepositoryMock);
    }

    @Test
    public void thatRequestPreformed(){
        when(mRepositoryMock.getComics(anyInt())).thenReturn(Observable.just(comicListData()));
        ComicsParam param = new ComicsParam();

        mUseCase.run(param);

        verify(mRepositoryMock).getComics(param.getCharacterId());
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
