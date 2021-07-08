/*
 * Copyright (c) 2021 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * This project and source code may use libraries or frameworks that are
 * released under various Open-Source licenses. Use of those libraries and
 * frameworks are governed by their own individual licenses.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
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