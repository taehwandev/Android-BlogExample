package tech.thdev.kotlin_example_01.base.presenter

/**
 * Created by Tae-hwan on 7/21/16.
 */
interface BasePresenter<in VIEW> {

    /**
     * View Attach.
     */
    fun attachView(view: VIEW)

    /**
     * View detach
     */
    fun detachView()
}