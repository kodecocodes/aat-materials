package com.hackertronix.cinematic.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.hackertronix.cinematic.util.convertToFiveStarScale
import com.hackertronix.cinematic.util.toLanguageName
import com.hackertronix.cinematic.util.toYear
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

	val isFavourite: Boolean = false,
) : Parcelable {

	val rating: Float
	get() = voteAverage.convertToFiveStarScale()

	val movieInfo: String
	get() = "${this.releaseDate.toYear()}\t\t${this.originalLanguage.toLanguageName()}\t\t${this.voteCount} ratings"
}
