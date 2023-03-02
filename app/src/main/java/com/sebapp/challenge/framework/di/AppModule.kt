package com.sebapp.challenge

import com.sebapp.challenge.data.services.ApiService
import com.sebapp.challenge.framework.common.Constants
import com.sebapp.challenge.presentation.foodList.FoodViewModel
import com.sebapp.challenge.repository.FoodRepository
import com.sebapp.challenge.repository.FoodRepositoryImpl
import com.sebapp.challenge.usecase.FoodListUseCase
import com.sebapp.challenge.usecase.FoodListUseCaseImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(Constants.baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
            .create(ApiService::class.java)
    }
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }
    single<FoodRepository> { FoodRepositoryImpl(get()) }
    single<FoodListUseCase> { FoodListUseCaseImpl(get()) }
    viewModel { FoodViewModel(get()) }
}
