package com.sebapp.challenge.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Food(
    val description: String,
    val image: String,
    val name: String,
    val positionLat: String,
    val positionLong: String
) : Parcelable