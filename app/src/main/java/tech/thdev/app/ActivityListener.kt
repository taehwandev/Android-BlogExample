package tech.thdev.app

import tech.thdev.app.common.smartlock.SmartLockViewModel

interface ActivityListener {

    val smartLockViewModel: SmartLockViewModel

    fun showLogin()

    fun showLogout()
}