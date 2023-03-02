package com.sebapp.challenge.presentation.foodList

import android.graphics.text.TextRunShaper
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenStateAtLeast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sebapp.challenge.data.model.Response
import com.sebapp.challenge.data.model.foodsTop
import com.sebapp.challenge.databinding.FragmentCharacterListBinding
import com.sebapp.challenge.framework.showSnackError
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.getActivityViewModel

class FoodListFragment : Fragment() {

    private var _binding: FragmentCharacterListBinding? = null
    private val binding get() = _binding!!
    //    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private val recyclerViewAdapter by lazy { RecyclerViewAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharacterListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initViewModel()
        itemDetail()
    }

    private fun initRecyclerView() {
        binding.apply {
            recyclerView.apply {
                layoutManager = LinearLayoutManager(context)
//                recyclerViewAdapter = RecyclerViewAdapter()
                adapter = recyclerViewAdapter
            }
        }
    }

    private fun initViewModel() {
        val viewModel = getActivityViewModel<FoodViewModel>()
        lifecycleScope.launch {
            lifecycle.whenStateAtLeast(Lifecycle.State.CREATED) {
                viewModel.getMainListData().observeForever { response ->
                    when (response) {
                        is Response.Loading -> {
                            showLoading(true)
                        }
                        is Response.Success -> {
                            showLoading(false)
                            recyclerViewAdapter.submitList(response.data)
                        }
                        is Response.Failure -> {
                            showSnackError(response.e.message.toString())
                        }
                    }
                }
            }

        }
    }
    private fun showLoading(visible: Boolean) {
        when (visible) {
            true -> binding.progressBar.visibility = View.VISIBLE
            false -> binding.progressBar.visibility = View.GONE
        }
    }
    private fun itemDetail() {
        recyclerViewAdapter.setOnItemClickListener { food ->
            val action =
                FoodListFragmentDirections
                    .actionCharacterListFragmentToDetailFragment(
                        food
                    )
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
