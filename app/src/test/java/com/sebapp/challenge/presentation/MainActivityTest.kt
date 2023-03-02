package com.sebapp.challenge.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.prattosebastian.auth.extra_utils_test.MainCoroutineRule
import com.sebapp.challenge.data.services.ApiService
import com.sebapp.challenge.framework.common.Constants.Companion.baseURL
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var mainActivity: MainActivity
    private lateinit var services: ApiService

    companion object {
        private lateinit var retrofit: Retrofit

        @BeforeClass
        @JvmStatic
        fun setupCommon() {
            retrofit = Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }

    @Before
    fun setup() {
        mainActivity = MainActivity()
        services = retrofit.create(ApiService::class.java)
    }

    @Test
    fun `service get first page and check return not null`() {
        runBlocking {
            val result = services.getListFoods()
            MatcherAssert.assertThat(
                "Service return null value", result,
                Matchers.`is`(Matchers.notNullValue())
            )
        }
    }
}
