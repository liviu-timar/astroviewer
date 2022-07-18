package com.adyen.android.assignment.data.remote

import com.adyen.android.assignment.BuildConfig
import com.adyen.android.assignment.data.remote.models.AstronomyPictureDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface PlanetaryService {
    /**
     * APOD - Astronomy Picture of the day.
     * See [the docs](https://api.nasa.gov/) and [github micro service](https://github.com/nasa/apod-api#docs-)
     */
    @GET("planetary/apod?api_key=${BuildConfig.API_KEY}")
    suspend fun getPictures(@Query("count") count: Int = 20): Response<List<AstronomyPictureDto>>
}
