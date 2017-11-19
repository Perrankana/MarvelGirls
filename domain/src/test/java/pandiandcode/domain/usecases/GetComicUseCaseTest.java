package pandiandcode.domain.usecases;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.schedulers.TestScheduler;
import pandiandcode.databoundary.ComicData;
import pandiandcode.databoundary.ComicRepository;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by Rocio Ortega on 19/11/2017.
 */

public class GetComicUseCaseTest {

    @Mock
    ComicRepository mRepositoryMock;

    private GetComicUseCase mUseCase;

    @Before
    public void setup() {
        initMocks(this);
        mUseCase = new GetComicUseCase(new TestScheduler(), new TestScheduler(), mRepositoryMock);
    }

    @Test
    public void thatRequestPreformed() {
        when(mRepositoryMock.getComic(anyInt())).thenReturn(Observable.<ComicData>just(comicData()));
        ComicParam param = new ComicParam(123);

        mUseCase.run(param);

        verify(mRepositoryMock).getComic(123);
    }

    private ComicData comicData() {
        return new ComicData(4321, "comic name", "comic description", "thumbnailImage", new ArrayList<String>());
    }
}
