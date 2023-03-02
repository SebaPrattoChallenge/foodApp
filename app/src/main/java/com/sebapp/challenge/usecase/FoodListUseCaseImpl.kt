package com.sebapp.challenge.usecase

import com.sebapp.challenge.data.model.Response
import com.sebapp.challenge.data.model.foodsTop
import com.sebapp.challenge.repository.FoodRepository

class FoodListUseCaseImpl(
    private val foodRepository: FoodRepository
) : FoodListUseCase {

    override suspend fun getListFood(): foodsTop {
        return foodRepository.getListFood()
    }
}
