package com.sebapp.challenge.presentation.foodList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.sebapp.challenge.data.model.Response
import com.sebapp.challenge.usecase.FoodListUseCase
import kotlinx.coroutines.Dispatchers
import okio.IOException
import retrofit2.HttpException

class FoodViewModel(
    private val foodListUseCase: FoodListUseCase
) : ViewModel() {
    fun getMainListData() = liveData(Dispatchers.IO) {
        emit(Response.Loading())
        try {
            val listFood = foodListUseCase.getListFood()
            if (listFood.status.code == 200) {
                emit(Response.Success(listFood.foods))
            }
        } catch (e: IOException) {
            emit(Response.Failure(e))
        } catch (e: HttpException) {
            emit(Response.Failure(e))
        }
    }
}
