package com.sebapp.challenge.presentation.foodDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.sebapp.challenge.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemDetail()
        linkMaps()
    }

    private fun linkMaps() {
        binding.tvMaps.setOnClickListener {
            val action = DetailFragmentDirections.actionDetailFragmentToMapsFragment(args.food)
            findNavController().navigate(action)
        }
    }

    private fun itemDetail() {
        binding.apply {
            with(args) {
                Glide.with(imageViewPhoto)
                    .load(args.food.image)
                    .centerCrop()
                    .into(imageViewPhoto)
                tvName.text = food.name
                tvDescription.text = food.description
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
