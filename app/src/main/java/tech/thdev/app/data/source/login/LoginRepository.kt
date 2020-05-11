package tech.thdev.app.data.source.login

import tech.thdev.app.data.User

/**
 * Created by tae-hwan on 2/17/17.
 */
class LoginRepository : LoginSource {

    private val loginLocalDataSource = LoginLocalDataSource()

    override fun dumpLogin(user: User) =
        loginLocalDataSource.dumpLogin(user)
}