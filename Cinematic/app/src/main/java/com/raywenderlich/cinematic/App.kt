package com.raywenderlich.cinematic

import android.app.Application
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.raywenderlich.cinematic.data.di.dataModule
import com.raywenderlich.cinematic.data.repository.MoviesRepository
import com.raywenderlich.cinematic.di.appModule
import com.raywenderlich.cinematic.model.CastResponse
import com.raywenderlich.cinematic.model.Movie
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

@KoinApiExtension
class App : Application(), KoinComponent {

  override fun onCreate() {
    super.onCreate()

    startKoin {
      androidLogger(Level.DEBUG)
      androidContext(this@App)
      modules(dataModule + appModule)
    }

    prefillData()
  }

  private fun prefillData() {
    val gson = Gson()
    val database = get<MoviesRepository>()

    val moviesToken = object : TypeToken<List<Movie?>?>() {}.type
    val castToken = object : TypeToken<List<CastResponse?>?>() {}.type
    val movies: List<Movie> =
        gson.fromJson(String(resources.openRawResource(R.raw.movies).readBytes()), moviesToken)
    val cast: List<CastResponse> =
        gson.fromJson(String(resources.openRawResource(R.raw.cast).readBytes()), castToken)

    GlobalScope.launch {
      database.saveMovies(movies)
      database.saveCast(cast)
    }
  }
}