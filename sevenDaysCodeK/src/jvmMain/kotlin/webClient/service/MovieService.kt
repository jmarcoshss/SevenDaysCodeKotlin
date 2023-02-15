package webClient.service

import retrofit2.http.GET
import webClient.IMDB_API_KEY
import webClient.model.Top250Data

interface MovieService {

    @GET(IMDB_API_KEY)
    suspend fun findTop250Movies(): Top250Data
}