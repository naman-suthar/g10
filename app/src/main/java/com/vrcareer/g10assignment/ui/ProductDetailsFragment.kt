package com.vrcareer.g10assignment.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.vrcareer.g10assignment.R
import com.vrcareer.g10assignment.adapters.ImageSlideAdapter
import com.vrcareer.g10assignment.databinding.FragmentProductDetailsBinding
import com.vrcareer.g10assignment.model.Product
import me.relex.circleindicator.CircleIndicator


class ProductDetailsFragment : Fragment() {
    val args: ProductDetailsFragmentArgs by navArgs()
    private lateinit var currProduct: Product
    lateinit var viewPagerAdapter: ImageSlideAdapter
    lateinit var indicator: CircleIndicator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        currProduct = args.product

//        Log.e("Product","$currProduct")
        val binding  = FragmentProductDetailsBinding.inflate(inflater, container, false)
        binding.productTitle.text = currProduct.title
        binding.rating.text = currProduct.rating.toString()
        binding.txtDescription.text = currProduct.description.toString()
        currProduct.images?.let {
            viewPagerAdapter = ImageSlideAdapter(requireContext(), it as ArrayList<String>)
            binding.viewpager.adapter = viewPagerAdapter
            indicator = binding.root.findViewById(R.id.indicator) as CircleIndicator
            indicator.setViewPager(binding.viewpager)
        }
        return binding.root
    }

}