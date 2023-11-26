package com.yara.juststudioapp.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yara.juststudioapp.databinding.ItemBannerBinding
import com.yara.juststudioapp.model.Banner

class BannerListRecyclerAdapter(var banners: List<Banner>) :
    RecyclerView.Adapter<BannerListRecyclerAdapter.BannerViewHolder>() {

    private var _binding: ItemBannerBinding? = null
    private val binding get() = _binding!!

    inner class BannerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        _binding = ItemBannerBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return BannerViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return banners.size
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        val item = banners[position]
        holder.itemView.apply {
            binding.ivBanner.setImageResource(item.image)
        }
    }
}