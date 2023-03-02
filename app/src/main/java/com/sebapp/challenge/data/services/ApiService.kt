package com.sebapp.challenge.data.services

import com.sebapp.challenge.data.model.Response
import com.sebapp.challenge.data.model.foodsTop
import retrofit2.http.GET

interface ApiService {
    @GET("9fb89918-bf05-4ee2-a565-59e8810c659f")
    suspend fun getListFoods(): foodsTop
}