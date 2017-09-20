package com.kb.example.day3app

import io.reactivex.Observable
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class ExampleUnitTest {
    @Test
    @Throws(Exception::class)
    fun addition_isCorrect() {
        assertEquals(4, (2 + 2).toLong())
    }

    @Test
    fun initialsDemo() {
        val testObserver = Observable.just("katy perry", "johnt bravo")
                .map { it.trim().split("  ") }
                .flatMapIterable { it }
                .map { it[0] }
                .map { it.toUpperCase() }
                .toList()
                .map { it.joinToString(separator = ".") }
                .toObservable()
                .test()
        testObserver.assertValues("K.P.J.B")
        testObserver.assertNoErrors()
        testObserver.assertComplete()
    }
}