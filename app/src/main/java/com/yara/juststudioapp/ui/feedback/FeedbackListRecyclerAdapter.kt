package com.yara.juststudioapp.ui.feedback

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yara.juststudioapp.databinding.ItemFeedbackBinding
import com.yara.juststudioapp.model.Feedback

class FeedbackListRecyclerAdapter(var feedbacks: List<Feedback>) :
    RecyclerView.Adapter<FeedbackListRecyclerAdapter.FeedbackViewHolder>() {

    private var _binding: ItemFeedbackBinding? = null
    private val binding get() = _binding!!

    inner class FeedbackViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedbackViewHolder {
        _binding = ItemFeedbackBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return FeedbackViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return feedbacks.size
    }

    override fun onBindViewHolder(holder: FeedbackViewHolder, position: Int) {
        val item = feedbacks[position]
        holder.itemView.apply {
            binding.tvFeedbackUserName.text = item.name
            binding.tvFeedbackDate.text = item.date
            binding.tvFeedbackRating.text = item.rating.toString()
            binding.tvFeedbackText.text = item.text
        }
    }
}