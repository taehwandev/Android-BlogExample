package tech.thdev.app.data.source.login

import io.reactivex.rxjava3.core.Flowable
import tech.thdev.app.data.User
import tech.thdev.app.data.UserEntity
import java.util.concurrent.TimeUnit

/**
 * Created by tae-hwan on 2/17/17.
 */
class LoginLocalDataSource : LoginSource {

    companion object {
        /**
         * A dummy authentication store containing known user names and passwords.
         */
        private val DUMMY_CREDENTIALS =
            mutableListOf(
                User("test@test.com", "1234"),
                User("test2@test.com", "4321")
            )
    }

    override fun dumpLogin(user: User): Flowable<UserEntity> {
        return Flowable.just(user)
            .delay(2000, TimeUnit.MILLISECONDS)
            .map {
                Flowable.fromIterable(DUMMY_CREDENTIALS)
                    .map {
                        it.email == user.email && it.password == user.password
                    }
                    .filter {
                        it
                    }
                    .count()
            }
            .map {
                android.util.Log.d("TEMP", "count : ${it.blockingGet()}")
                UserEntity(
                    isSuccess = it.blockingGet() == 1L,
                    email = user.email
                )
            }
    }
}