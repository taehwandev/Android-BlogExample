package tech.thdev.butter_knife_example.presenter;

import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.concurrent.CountDownLatch;

import tech.thdev.butter_knife_example.adapter.model.PhotoDataModel;
import tech.thdev.butter_knife_example.network.RetrofitPhoto;
import tech.thdev.butter_knife_example.network.domain.Photo;
import tech.thdev.butter_knife_example.presenter.view.PhotoPresenterView;
import tech.thdev.butter_knife_example.presenter.view.PhotoSearchPresenterView;

import static com.jayway.awaitility.Awaitility.await;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Tae-hwan on 5/3/16.
 */
@RunWith(AndroidJUnit4.class)
public class PhotoSearchPresenterTest {

    private PhotoSearchPresenter photoSearchPresenter;
    private PhotoSearchPresenterView photoSearchPresenterView;
    private PhotoDataModel photoDataModel;

    @org.junit.Before
    public void setUp() throws Exception {
        photoSearchPresenterView = mock(PhotoSearchPresenterView.class);
        photoDataModel = mock(PhotoDataModel.class);
        photoSearchPresenter = new PhotoSearchPresenter(photoSearchPresenterView, RetrofitPhoto.getRetrofitPhoto(), photoDataModel);
    }

    @Test
    public void testLoadSearchPhotos() throws Exception {
        final boolean[] finish = {false};
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                finish[0] = true;
                return invocationOnMock;
            }
        }).when(photoSearchPresenterView).onRefresh();

        photoSearchPresenter.loadSearchPhotos(20, 1, "paris");

        await().until(new Runnable() {
            @Override
            public void run() {
                while (!finish[0]) {
                    verify(photoDataModel, atLeastOnce()).add((Photo) any());
                }
            }
        });

        verify(photoSearchPresenterView).onRefresh();
    }
}