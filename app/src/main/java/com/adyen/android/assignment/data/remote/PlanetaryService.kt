package com.adyen.android.assignment.data.remote

import com.adyen.android.assignment.BuildConfig
import com.adyen.android.assignment.data.remote.adapters.DayAdapter
import com.adyen.android.assignment.data.remote.models.AstronomyPictureDto
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface PlanetaryService {
    /**
     * APOD - Astronomy Picture of the day.
     * See [the docs](https://api.nasa.gov/) and [github micro service](https://github.com/nasa/apod-api#docs-)
     */
    @GET("planetary/apod?api_key=${BuildConfig.API_KEY}")
    suspend fun getPictures(@Query("count") count: Int = 20): Response<List<AstronomyPictureDto>>

    companion object {

        private val moshi: Moshi = Moshi
            .Builder()
            .add(DayAdapter())
            .add(KotlinJsonAdapterFactory())
            .build()

        private val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(BuildConfig.NASA_BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
        }

        val instance: PlanetaryService by lazy { retrofit.create(PlanetaryService::class.java) }
    }
}
