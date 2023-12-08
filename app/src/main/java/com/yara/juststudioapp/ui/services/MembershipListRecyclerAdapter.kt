package com.yara.juststudioapp.ui.services

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yara.juststudioapp.databinding.ItemMembershipBinding
import com.yara.juststudioapp.domain.model.Membership

class MembershipListRecyclerAdapter(var memberships: List<Membership>) :
    RecyclerView.Adapter<MembershipListRecyclerAdapter.MembershipViewHolder>() {

    private var _binding: ItemMembershipBinding? = null
    private val binding get() = _binding!!

    inner class MembershipViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MembershipViewHolder {
        _binding = ItemMembershipBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MembershipViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return memberships.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MembershipViewHolder, position: Int) {
        val item = memberships[position]
        holder.itemView.apply {
            binding.tvMembershipTitle.text = item.title
            binding.tvMembershipItem1.text = item.item_1
            binding.tvMembershipItem2.text = item.item_2
            binding.tvMembershipItem3.text = item.item_3
            binding.tvMembershipPrice.text = item.price.toString() + " Р"
        }
    }
}