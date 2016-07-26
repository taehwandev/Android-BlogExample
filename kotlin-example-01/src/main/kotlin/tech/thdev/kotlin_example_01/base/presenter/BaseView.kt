package tech.thdev.kotlin_example_01.base.presenter

/**
 * Created by Tae-hwan on 7/21/16.
 */
interface BaseView<in P> {

    fun onPresenter(presenter: P)
}