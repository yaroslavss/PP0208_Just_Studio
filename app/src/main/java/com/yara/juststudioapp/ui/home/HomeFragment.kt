package com.yara.juststudioapp.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.carousel.CarouselLayoutManager
import com.yara.juststudioapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val viewModel by lazy {
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val tvAddress: TextView = binding.tvAddress
        viewModel.text_address.observe(viewLifecycleOwner) {
            tvAddress.text = it
        }

        val tvPhone: TextView = binding.tvPhone
        viewModel.text_phone.observe(viewLifecycleOwner) {
            tvPhone.text = it
        }

        initBannerListRecycler()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initBannerListRecycler() {
        val adapter = BannerListRecyclerAdapter(listOf())

        binding.rvBannersCarousel.adapter = adapter
        binding.rvBannersCarousel.layoutManager = CarouselLayoutManager()

        viewModel.bannerListLiveData.observe(viewLifecycleOwner) { list ->
            adapter.banners = list
            adapter.notifyDataSetChanged()
        }
    }
}