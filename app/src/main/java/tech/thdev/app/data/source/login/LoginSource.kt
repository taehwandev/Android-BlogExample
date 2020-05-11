package tech.thdev.app.data.source.login

import io.reactivex.rxjava3.core.Flowable
import tech.thdev.app.data.User
import tech.thdev.app.data.UserEntity

/**
 * Created by tae-hwan on 2/17/17.
 */
interface LoginSource {

    fun dumpLogin(user: User): Flowable<UserEntity>
}