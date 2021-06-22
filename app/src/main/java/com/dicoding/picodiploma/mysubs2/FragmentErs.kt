package com.dicoding.picodiploma.mysubs2

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.picodiploma.mysubs2.databinding.FragmentErsBinding

class FragmentErs : Fragment() {
    companion object {
        const val EXTRA_USERNAME = "extra_username"

        fun newInstance(username: String): FragmentErs {
            val fragment = FragmentErs()
            val bundle = Bundle()
            bundle.putString(EXTRA_USERNAME, username)
            fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var adapter: AdapterErs
    private lateinit var modelVM: VMErs
    private lateinit var binding: FragmentErsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentErsBinding.inflate(inflater, container, false)
        adapter = AdapterErs()

        binding.rvFollowers.layoutManager = LinearLayoutManager(activity)
        binding.rvFollowers.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val username = arguments?.getString(EXTRA_USERNAME)

        adapter.notifyDataSetChanged()

        binding.rvFollowers.setHasFixedSize(true)
        showLoading(true)

        modelVM = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            VMErs::class.java)
        modelVM.getFollowers(username.toString())
        Log.d("username: ", username.toString())


        modelVM.getListFollowers().observe(activity!!, Observer { userItems ->
            if (userItems != null) {
                adapter.setData(userItems)
                showLoading(false)
            }
        })
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}