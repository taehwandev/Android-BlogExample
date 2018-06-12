package tech.thdev.app.data.source.login

import tech.thdev.app.data.LoginItem

object LoginRepository : LoginDataSource {

    private val dummyLoginData = mutableListOf(LoginItem("user_one", "user1234"), LoginItem("user_two", "user1234"))

    override suspend fun login(user: LoginItem): Boolean {
        dummyLoginData.forEach { (userId, password) ->
            if (user.userId == userId && user.password == password) {
                return true
            }
        }
        return false
    }
}