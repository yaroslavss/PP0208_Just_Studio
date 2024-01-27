package com.yara.juststudioapp.ui.profile

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.yara.juststudioapp.R
import com.yara.juststudioapp.databinding.FragmentProfileBinding
import com.yara.juststudioapp.util.Resource

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val viewModel by lazy {
        ViewModelProvider(this).get(ProfileViewModel::class.java)
    }
    private lateinit var adapter: VPAdapter
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private val tabNames: Array<String> = arrayOf(
        "Основные настройки",
        "Абонементы",
        "Уведомления",
        "Записи",
        "Смена пароля",
        "Написать отзыв",
    )
    private val tabIcons: Array<Int> = arrayOf(
        R.drawable.ic_tab_profile_settings,
        R.drawable.ic_tab_profile_memberships,
        R.drawable.ic_tab_profile_notifications,
        R.drawable.ic_tab_profile_records,
        R.drawable.ic_tab_profile_changepassword,
        R.drawable.ic_tab_profile_feedback
    )
    private val tabIconsSelected: Array<Int> = arrayOf(
        R.drawable.ic_tab_profile_settings_sel,
        R.drawable.ic_tab_profile_memberships_sel,
        R.drawable.ic_tab_profile_notifications_sel,
        R.drawable.ic_tab_profile_records_sel,
        R.drawable.ic_tab_profile_changepassword_sel,
        R.drawable.ic_tab_profile_feedback_sel
    )

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // setup ViewPager2 adapter
        adapter = VPAdapter(this)
        viewPager = binding.vpViewPager
        viewPager.adapter = adapter

        // setup tab layout
        initTabs()

        val navController = findNavController()

        val sharedPreferences =
            requireActivity().applicationContext.getSharedPreferences(
                "settings",
                Context.MODE_PRIVATE
            )

        val token = sharedPreferences.getString("API_TOKEN", "")

        println("!!! profile: $token")

        token?.let {
            if (it.isEmpty()) {
                navController.navigate(R.id.action_profileFragment_to_loginFragment)
            }
            viewModel.getUserInfo(token)
        }

        viewModel.userResult.observe(viewLifecycleOwner) { response ->
            println("!!! $response")
            when (response) {
                is Resource.Success -> {
                    /*val tietEmail = binding.tietEmail as TextView
                    tietEmail.text = response.data.emailUser
                    val tietUserName = binding.tietUserName as TextView
                    tietUserName.text = response.data.userName*/
                }

                is Resource.Error -> {
                    /*response.message?.let { message ->
                        if (message == HTTP_ERROR_UNAUTHORIZED) {
                            navController.navigate(R.id.loginFragment)
                        }
                    }*/
                }

                is Resource.Exception -> {}
                is Resource.Loading -> {}
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initTabs() {
        tabLayout = binding.tlTabLayout

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabNames[position]
            val imgView = ImageView(context);
            imgView.setImageResource(tabIcons[position]);
            tab.customView = imgView;
        }.attach()

        // change selected tab's icon
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                val imgViewSel = ImageView(context);
                imgViewSel.setImageResource(tabIconsSelected[tab.position]);
                tab.customView = imgViewSel;
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {
                val imgView = ImageView(context);
                imgView.setImageResource(tabIcons[tab.position]);
                tab.customView = imgView;
            }
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        // setup first tab as selected
        val imgViewSel = ImageView(context);
        imgViewSel.setImageResource(tabIconsSelected[0]);
        tabLayout.getTabAt(0)?.customView = imgViewSel
    }
}