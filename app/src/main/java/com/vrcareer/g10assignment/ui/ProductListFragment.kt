package com.vrcareer.g10assignment.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vrcareer.g10assignment.MyApplication
import com.vrcareer.g10assignment.Status
import com.vrcareer.g10assignment.adapters.ProductListAdapter
import com.vrcareer.g10assignment.databinding.FragmentProductListBinding
import com.vrcareer.g10assignment.vm.ProductListViewModel
import com.vrcareer.g10assignment.vm.ProductListViewModelFactory

class ProductListFragment : Fragment() {
    private lateinit var mProductViewModel: ProductListViewModel
    private lateinit var mRecyclerAdapter: ProductListAdapter
    private lateinit var binding: FragmentProductListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this
        binding = FragmentProductListBinding.inflate(inflater, container, false)
        initData()
        setRecycler(binding.rvProductList)
        obtainListFromServer()
        return binding.root
    }

    private fun obtainListFromServer() {
        mProductViewModel.getAllProduct().observe(this.requireActivity()) {
            when(it){
                is Status.SUCCESS-> {
                    binding.progressBar.visibility = View.GONE
                    mRecyclerAdapter.updateList(it.data.products)
                }
                is Status.FAILURE -> {

                    Toast.makeText(
                        requireContext(),
                        "Failed to load the data ${it.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
                is Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setRecycler(rvProductList: RecyclerView) {
        rvProductList.layoutManager = LinearLayoutManager(requireContext())
        rvProductList.setHasFixedSize(false)
        rvProductList.adapter = mRecyclerAdapter
    }

    private fun initData() {
        mProductViewModel = ViewModelProvider(
            this,
            ProductListViewModelFactory(MyApplication.getApiHelperInstance())
        ).get(ProductListViewModel::class.java)

        mRecyclerAdapter = ProductListAdapter(){
           val action = ProductListFragmentDirections.actionProductListFragmentToProductDetailsFragment(it)
            findNavController().navigate(action)
        }
    }


}