package com.liviutimar.astroviewer.data.api

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test


class PlanetaryServiceTest {

    /**
     * Integration test -
     * ensures the [generated key](https://api.nasa.gov/) returns results from the api
     */
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getPictures_successfulRequest() = runTest {
        val response = PlanetaryService.instance.getPictures()
        assert(response.isSuccessful)
    }
}
