package com.yara.juststudioapp.ui.profile.inner

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.yara.juststudioapp.databinding.FragmentProfileNotificationsBinding
import com.yara.juststudioapp.ui.profile.inner.adapter.NotificationListRecyclerAdapter
import com.yara.juststudioapp.ui.profile.inner.viewmodel.ProfileNotificationsViewModel

class ProfileNotificationsFragment : Fragment() {

    private var _binding: FragmentProfileNotificationsBinding? = null
    private val viewModel by lazy {
        ViewModelProvider(this).get(ProfileNotificationsViewModel::class.java)
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentProfileNotificationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = NotificationListRecyclerAdapter(listOf())
        binding.rvNotifications.adapter = adapter
        binding.rvNotifications.layoutManager = LinearLayoutManager(activity)

        viewModel.notificationListLiveData.observe(viewLifecycleOwner) { list ->
            adapter.notifications = list
            adapter.notifyDataSetChanged()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}