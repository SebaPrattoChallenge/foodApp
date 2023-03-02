package com.sebapp.challenge.repository

import com.sebapp.challenge.data.model.Response
import com.sebapp.challenge.data.model.foodsTop
import com.sebapp.challenge.data.services.ApiService
import java.io.IOException

class FoodRepositoryImpl(
    private var apiService: ApiService
) : FoodRepository {
    override suspend fun getListFood() = apiService.getListFoods()

    /*{

        try {
            val response = apiService.getListFoods()
            if (response.isSuccessful) {
                response.body()?.let {
                    return Response.Success(it)
                }
            }
        } catch (e: IOException) {
            return Response.Failure(e)
        }
    }*/
}
