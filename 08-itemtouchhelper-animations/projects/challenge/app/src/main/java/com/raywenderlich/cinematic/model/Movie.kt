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
package com.raywenderlich.cinematic.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.raywenderlich.cinematic.util.convertToFiveStarScale
import com.raywenderlich.cinematic.util.toLanguageName
import com.raywenderlich.cinematic.util.toYear
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Movie(

    @SerializedName("overview")
    val overview: String = "",

    @SerializedName("original_language")
    val originalLanguage: String = "",

    @SerializedName("original_title")
    val originalTitle: String = "",

    @SerializedName("video")
    val video: Boolean = false,

    @SerializedName("title")
    val title: String = "",

    @SerializedName("poster_path")
    val posterPath: String = "",

    @SerializedName("backdrop_path")
    val backdropPath: String = "",

    @SerializedName("release_date")
    val releaseDate: String = "",

    @SerializedName("popularity")
    val popularity: Float = 0f,

    @SerializedName("vote_average")
    val voteAverage: Float = 0f,

    @SerializedName("id")
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0,

    @SerializedName("adult")
    val adult: Boolean = false,

    @SerializedName("vote_count")
    val voteCount: Int = 0,

    val isFavorite: Boolean = false,
) : Parcelable {

  val rating: Float
    get() = voteAverage.convertToFiveStarScale()

  val movieInfo: String
    get() = "${this.releaseDate.toYear()}\t\t${this.originalLanguage.toLanguageName()}\t\t${this.voteCount} ratings"
}
