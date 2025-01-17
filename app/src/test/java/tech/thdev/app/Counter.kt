package tech.thdev.app

import org.junit.jupiter.api.Test

class Counter {
    var count: Int = 0 //두 쓰레드에 의해 공유되는 변수
    val `object`: Any = Any()

    //    @Synchronized
    /*synchronized*/ fun increment() {
        println("increment")
        synchronized(`object`) {
            println("before increment count $count")
            count++ //첫 번째 쓰레드에 의해 실행되는 문장
            println("after increment count $count")
        }
    }

    //    @Synchronized
    /*synchronized*/ fun decrement() {
        println("decrement")
        // 동기화가 필요없는 다른 작업
        synchronized(`object`) {
            println("before decrement count $count")
            count--
            println("after decrement count $count")
        }
    }

    @Test
    @Throws(InterruptedException::class)
    fun testTwo() {
        val N = 30

        val sb = StringBuilder()

        for (i in 0..0) {
            val counter = Counter()
            val threadA = Thread(object : Runnable {
                override fun run() {
                    run {
                        var i = N
                        while (i-- > 0) {
                            counter.increment()
                        }
                    }
                }
            })

            val threadB = Thread {
                for (i in N downTo 1) {
                    counter.decrement()
                }
            }
            threadA.start()
            threadB.start()
            threadA.join()
            threadB.join()
            println("count " + counter.count)
        }
    }
}
