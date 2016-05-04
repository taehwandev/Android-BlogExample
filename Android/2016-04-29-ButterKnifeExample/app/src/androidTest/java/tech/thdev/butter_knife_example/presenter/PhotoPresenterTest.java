package tech.thdev.butter_knife_example.presenter;

import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import tech.thdev.butter_knife_example.adapter.model.PhotoDataModel;
import tech.thdev.butter_knife_example.network.RetrofitPhoto;
import tech.thdev.butter_knife_example.network.domain.Photo;
import tech.thdev.butter_knife_example.presenter.view.PhotoPresenterView;

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
public class PhotoPresenterTest {

    // create  a signal to let us know when our task is done.
    final CountDownLatch signal = new CountDownLatch(1);

    private PhotoPresenter photoPresenter;
    private PhotoPresenterView photoPresenterView;
    private PhotoDataModel photoDataModel;

    @org.junit.Before
    public void setUp() throws Exception {
        photoPresenterView = mock(PhotoPresenterView.class);
        photoDataModel = mock(PhotoDataModel.class);
        photoPresenter = new PhotoPresenter(photoPresenterView, RetrofitPhoto.getRetrofitPhoto(), photoDataModel);
    }

    @org.junit.Test
    public void testLoadPhotos() throws Exception {
        loadPhotos();
    }

    @org.junit.Test
    public void testOnItemClick() throws Exception {
        loadPhotos();

        final int position = 0;
        Photo photo = photoDataModel.getPhotoItem(position);
        when(photoDataModel.getPhotoItem(position)).thenReturn(photo);

        // when
        photoPresenter.photoItemClick(position);

        // then
        verify(photoPresenterView).onBottomSheetShow((Photo) any());
    }

    private void loadPhotos() {
        final boolean[] finish = {false};
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                finish[0] = true;
                Log.e("TAG", "doAnswer");
                return invocationOnMock;
            }
        }).when(photoPresenterView).onRefresh();

        Log.e("TAG", "photoLoad before");
        photoPresenter.loadPhotos(1);
        Log.e("TAG", "photoLoad after");

        await().until(new Runnable() {
            @Override
            public void run() {
                while (!finish[0]) {
                    Log.e("TAG", "await : finish " + finish[0]);
                    verify(photoDataModel, atLeastOnce()).add((Photo) any());
                }
            }
        });

        Log.d("TAG", "onRefresh");
        verify(photoPresenterView).onRefresh();
    }
}