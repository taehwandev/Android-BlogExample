package tech.thdev.webviewjavascriptinterface.view.main.presenter;

import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by Tae-hwan on 8/9/16.
 */
@RunWith(AndroidJUnit4.class)
public class MainPresenterTest {

    private MainContract.View view;
    private MainContract.Presenter presenter;

    @Before
    public void setUp() throws Exception {
        view = mock(MainContract.View.class);
        presenter = new MainPresenter();
        presenter.attachView(view);
    }

    @After
    public void tearDown() throws Exception {
        presenter.detachView();
    }

    @Test
    public void testUpdateKeyword() throws Exception {
        // updateKeyword call...
        presenter.getCustomJavaScript().updateKeyword("Keyword change");

        // Then
        verify(view).updateKeyword("Keyword change");
    }
}