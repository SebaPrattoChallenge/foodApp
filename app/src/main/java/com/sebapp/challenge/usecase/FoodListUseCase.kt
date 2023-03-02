package com.sebapp.challenge.usecase

import com.sebapp.challenge.data.model.Response
import com.sebapp.challenge.data.model.foodsTop

interface FoodListUseCase {
    suspend fun getListFood(): foodsTop
}
