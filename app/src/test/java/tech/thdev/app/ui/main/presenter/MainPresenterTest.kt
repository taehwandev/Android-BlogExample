package tech.thdev.app.ui.main.presenter

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import tech.thdev.app.ui.main.presenter.MainContract.Presenter

/**
 * Created by Tae-hwan on 8/9/16.
 */
class MainPresenterTest {

    private var view: MainContract.View? = null
    private var presenter: Presenter? = null

    @Before
    @Throws(Exception::class)
    fun setUp() {
        view = mock(MainContract.View::class.java)
        presenter = MainPresenter()
        presenter?.attachView(view!!)
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        presenter?.detachView()
    }

    @Test
    @Throws(Exception::class)
    fun testUpdateKeyword() {
        // updateKeyword call...
        presenter?.customJavaScript?.updateKeyword("Keyword change")

        // Then
        verify(view)?.updateKeyword("Keyword change")
    }
}