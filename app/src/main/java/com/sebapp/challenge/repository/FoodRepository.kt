package com.sebapp.challenge.repository

import com.sebapp.challenge.data.model.Response
import com.sebapp.challenge.data.model.foodsTop

interface FoodRepository {
    suspend fun getListFood(): foodsTop
}
