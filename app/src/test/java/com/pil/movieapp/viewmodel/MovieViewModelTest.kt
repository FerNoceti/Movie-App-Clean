package com.pil.movieapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.pil.movieapp.data.database.MovieDataBase
import com.pil.movieapp.data.service.MovieService
import com.pil.movieapp.domain.entity.Movie
import com.pil.movieapp.domain.usecase.GetMoviesUseCase
import com.pil.movieapp.domain.util.CoroutineResult
import com.pil.movieapp.presentation.viewmodel.MovieViewModel
import com.pil.movieapp.testObserver
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@OptIn(ExperimentalCoroutinesApi::class)
class MovieViewModelTest {

    private val testDispatcher = UnconfinedTestDispatcher()
    private lateinit var movieViewModel: MovieViewModel

    @MockK
    private lateinit var getMoviesUseCase: GetMoviesUseCase

    @MockK
    private lateinit var service: MovieService

    @MockK
    private lateinit var database: MovieDataBase
    private val movies: List<Movie> =
        listOf(
            Movie(
                title = "title",
                overview = "overview",
                posterPath = "posterPath",
                releaseDate = "releaseDate",
                voteAverage = 1.0f,
                voteCount = 1,
                originalLanguage = "originalLanguage",
            )
        )

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        Dispatchers.setMain(testDispatcher)
        getMoviesUseCase = spyk(GetMoviesUseCase(service, database))
        movieViewModel = MovieViewModel(getMoviesUseCase)
    }

    @Test
    fun `set liveData with SHOW_INFO as value`() {
        // Given
        coEvery { getMoviesUseCase() } returns CoroutineResult.Success(movies)
        val testObserver = movieViewModel.getValue().testObserver()

        // When
        runBlocking { movieViewModel.callService().join() }

        // Then
        assertEquals(
            MovieViewModel.MainData(
                MovieViewModel.MainStatus.SHOW_INFO,
                movies
            ),
            testObserver.observedValues[0]
        )
    }

    @Test
    fun `set liveData with EMPTY as value`() {
        // Given
        coEvery { getMoviesUseCase() } returns CoroutineResult.Success(emptyList())
        val testObserver = movieViewModel.getValue().testObserver()

        // When
        runBlocking { movieViewModel.callService().join() }

        // Then
        assertEquals(
            MovieViewModel.MainData(
                MovieViewModel.MainStatus.EMPTY,
                emptyList()
            ),
            testObserver.observedValues[0]
        )
    }

    @Test
    fun `set liveData with GO_BACK as value`() {
        // Given
        val testObserver = movieViewModel.getValue().testObserver()

        // When
        movieViewModel.goBack()

        // Then
        assertEquals(
            MovieViewModel.MainData(
                MovieViewModel.MainStatus.GO_BACK,
                emptyList()
            ),
            testObserver.observedValues[0]
        )
    }
}
