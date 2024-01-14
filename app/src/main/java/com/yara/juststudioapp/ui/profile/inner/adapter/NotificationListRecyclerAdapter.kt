package com.yara.juststudioapp.ui.profile.inner.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.yara.juststudioapp.R
import com.yara.juststudioapp.databinding.ItemNotificationBinding
import com.yara.juststudioapp.domain.model.Notification

class NotificationListRecyclerAdapter(var notifications: List<Notification>) :
    RecyclerView.Adapter<NotificationListRecyclerAdapter.NotificationViewHolder>() {

    private var _binding: ItemNotificationBinding? = null
    private val binding get() = _binding!!

    inner class NotificationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        _binding = ItemNotificationBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return NotificationViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return notifications.size
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        val item = notifications[position]
        holder.itemView.apply {
            binding.chbTitle.text = item.title
            binding.tvDateTime.text = item.dt

            val cvColor = if (item.isUnread) ContextCompat.getColor(
                holder.itemView.context,
                R.color.grey_green
            ) else ContextCompat.getColor(
                holder.itemView.context,
                R.color.white
            )

            binding.cvItemNotification.setCardBackgroundColor(cvColor)
        }
    }
}