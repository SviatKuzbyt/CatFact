package ua.sviatkuzbyt.catfact.data.managers

import retrofit2.http.GET
import retrofit2.http.Query
import ua.sviatkuzbyt.catfact.data.structures.FactResponse

interface FactManager {

    @GET("/")
    suspend fun getFactText(
        @Query("lang") language: String
    ): FactResponse
}