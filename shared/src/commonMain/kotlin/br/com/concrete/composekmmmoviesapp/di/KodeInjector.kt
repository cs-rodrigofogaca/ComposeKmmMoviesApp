package br.com.concrete.composekmmmoviesapp.di

import br.com.concrete.composekmmmoviesapp.MoviesSdk
import br.com.concrete.composekmmmoviesapp.network.FindMovieApi
import br.com.concrete.composekmmmoviesapp.network.GenreApi
import br.com.concrete.composekmmmoviesapp.network.MovieApi
import br.com.concrete.composekmmmoviesapp.repository.FindMovieRepository
import br.com.concrete.composekmmmoviesapp.repository.GenreRepository
import br.com.concrete.composekmmmoviesapp.repository.MovieRepository
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import kotlinx.serialization.json.Json
import org.kodein.di.*
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
val di = DI {

    bind<MoviesSdk>() with singleton { MoviesSdk() }

    bind<MovieApi>() with provider { MovieApi() }

    bind<GenreApi>() with provider { GenreApi() }

    bind<FindMovieApi>() with provider { FindMovieApi() }

    bind<MovieRepository>() with singleton { MovieRepository(instance()) }

    bind<GenreRepository>() with singleton { GenreRepository(instance()) }

    bind<FindMovieRepository>() with singleton { FindMovieRepository(instance()) }

    bind<HttpClient>() with provider {
        HttpClient {
            install(JsonFeature) {
                val json: Json = kotlinx.serialization.json.Json { ignoreUnknownKeys = true }
                serializer = KotlinxSerializer(json)
            }
            install(Logging){
                logger = Logger.DEFAULT
                level = LogLevel.INFO
            }
        }
    }
}
