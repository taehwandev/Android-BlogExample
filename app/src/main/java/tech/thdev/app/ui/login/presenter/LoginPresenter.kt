package tech.thdev.app.ui.login.presenter

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import tech.thdev.app.base.presenter.CommonPresenter
import tech.thdev.app.data.User
import tech.thdev.app.data.source.login.LoginRepository
import java.util.concurrent.TimeUnit

/**
 * Created by tae-hwan on 2/17/17.
 */
class LoginPresenter(
    private val loginRepository: LoginRepository
) : LoginContract.Presenter, CommonPresenter<LoginContract.View>() {

    companion object {
        private const val MIN_PASSWORD = 3
    }

    private val loginSubject: PublishSubject<TempData> = PublishSubject.create()
    private val loginDisposable: CompositeDisposable = CompositeDisposable()

    init {
        initLogin()
    }

    private fun initLogin() {
        loginSubject
            .throttleFirst(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
            .filter {
                when {
                    it.email.isEmpty() -> {
                        view?.emailEmptyError()
                        false
                    }
                    it.password.isPasswordValid().not() -> {
                        view?.passwordInvalid()
                        false
                    }
                    it.email.isEmailValid().not() -> {
                        view?.emailInvalid()
                        false
                    }
                    else -> {
                        true
                    }
                }
            }
            .map {
                User(it.email, it.password)
            }
            .switchMap {
                view?.showProgress()
                loginRepository.dumpLogin(it)
                    .toObservable()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
            }
            .filter {
                when {
                    it.isSuccess.not() -> {
                        view?.hideProgress()
                        view?.loginFail()
                        false
                    }
                    else -> true
                }
            }
            .subscribe({
                view?.hideProgress()
                view?.successLogin(it.email)
            }, {
                view?.hideProgress()
                view?.loginFail()
            })
            .apply {
                loginDisposable.add(this)
            }
    }

    override fun loginUser(email: String, password: String) {
        loginSubject.onNext(TempData(email, password))
    }

    private fun String.isEmailValid() =
        this.contains("@")

    private fun String.isPasswordValid() =
        this.length > MIN_PASSWORD

    override fun logout() {
        // 세션 처리
        view?.successLogout()
    }

    override fun clear() {
        loginDisposable.clear()
    }

    data class TempData(val email: String, val password: String)
}