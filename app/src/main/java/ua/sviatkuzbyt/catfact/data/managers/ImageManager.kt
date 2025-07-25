package ua.sviatkuzbyt.catfact.data.managers

import retrofit2.http.GET
import retrofit2.http.Query
import ua.sviatkuzbyt.catfact.data.structures.ImageResponse

interface ImageManager {
    @GET("cat")
    suspend fun getImageUrl(
        @Query ("json") json: Boolean = true
    ): ImageResponse
}