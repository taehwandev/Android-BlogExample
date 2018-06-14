package tech.thdev.app.data.source.login

import tech.thdev.app.data.LoginItem

interface LoginDataSource {

    companion object {
        const val TYPE_SUCCESS = 0
        const val TYPE_DONT_MATCH = 1
    }

    suspend fun login(user: LoginItem): Int
}