package br.com.rmoreira.app

import android.app.Application
import android.support.test.InstrumentationRegistry
import br.com.rmoreira.sdk.SdkApplication
import br.com.rmoreira.sdk.models.GpsTrackerModel
import br.com.rmoreira.sdk.network.routers.UserDataRouter
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

}
