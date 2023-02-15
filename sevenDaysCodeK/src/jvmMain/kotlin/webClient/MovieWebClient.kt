package webClient

import kotlinx.coroutines.flow.flow
import webClient.model.toMovie

class MovieWebClient {

    private val service = RetrofitInit().movieService

    fun findTop250Movies() = flow {
        val result = try {
            val movies = service.findTop250Movies()
                .items.map { it.toMovie() }
            println("movies loaded $movies")
            Status.Success(movies)
        } catch (e: Exception) {
            println("exception loaded")
            Status.Error(e)
        }
        emit(result)
    }
}

sealed class Status<out R> {
    data class Success<out T>(val data: T) : Status<T>()
    data class Error(val exception: Exception) : Status<Nothing>()
    object Loading : Status<Nothing>()

}