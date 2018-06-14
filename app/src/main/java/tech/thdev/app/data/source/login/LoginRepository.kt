package tech.thdev.app.data.source.login

import kotlinx.coroutines.experimental.delay
import tech.thdev.app.data.LoginItem

object LoginRepository : LoginDataSource {

    private val dummyLoginData = mutableListOf(LoginItem("test", "test1234"), LoginItem("test2", "test1234"))

    override suspend fun login(user: LoginItem): Int {
        var status = LoginDataSource.TYPE_DONT_MATCH
        dummyLoginData.forEach { (userId, password) ->
            if (user.userId == userId && user.password == password) {
                delay(5000L)
                status = LoginDataSource.TYPE_SUCCESS
            }
        }
        return status
    }
}