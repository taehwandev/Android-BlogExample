package tech.thdev.app.data.source.login

import tech.thdev.app.data.LoginItem

interface LoginDataSource {

    suspend fun login(user: LoginItem): Boolean
}