package com.sebapp.challenge.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenStateAtLeast
import com.sebapp.challenge.R
import com.sebapp.challenge.presentation.foodList.FoodViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Developer Applicant Interview"

        lifecycleScope.launch {
            lifecycle.whenStateAtLeast(Lifecycle.State.CREATED) {
                val viewModel = getViewModel<FoodViewModel>()
                viewModel.getMainListData()
            }
        }
    }
}
