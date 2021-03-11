package br.com.concrete.composekmmmoviesapp.androidApp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Button
import androidx.compose.material.Text
import br.com.concrete.composekmmmoviesapp.MoviesSdk
import br.com.concrete.composekmmmoviesapp.data.Response
import br.com.concrete.composekmmmoviesapp.domain.FavoriteMovie
import br.com.concrete.composekmmmoviesapp.domain.MoviesResponse
import br.com.concrete.composekmmmoviesapp.shared.Greeting
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : ComponentActivity() {

    private val mainScope = MainScope()
    private val moviesSdk = MoviesSdk()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Text(greet())
            Button(onClick = {
                moviesSdk.saveMovie(FavoriteMovie(1, "", "Panico", "Action,Horror", 2002, "teste"))
            }) {
                "Save"
            }
        }


        mainScope.launch {
            val response: Response<MoviesResponse> = moviesSdk.getPopularMovies()
            if (response is Response.Success)
                Toast.makeText(
                    this@MainActivity,
                    response.data.results[0].originalTitle,
                    Toast.LENGTH_SHORT
                ).show()
            else
                Toast.makeText(this@MainActivity, "ERRO", Toast.LENGTH_SHORT).show()
        }
    }
}
