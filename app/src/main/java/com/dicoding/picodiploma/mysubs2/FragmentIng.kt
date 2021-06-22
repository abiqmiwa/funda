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
import com.dicoding.picodiploma.mysubs2.databinding.FragmentIngBinding

class FragmentIng : Fragment() {

    companion object {
        const val EXTRA_USERNAME = "extra_username"

        fun newInstance(username: String): FragmentIng {
            val fragment = FragmentIng()
            val bundle = Bundle()
            bundle.putString(EXTRA_USERNAME, username)
            fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var adapter: AdapterIng
    private lateinit var modelVM: VMIng
    private lateinit var binding: FragmentIngBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentIngBinding.inflate(inflater, container, false)
        adapter = AdapterIng()

        binding.rvFollowing.layoutManager = LinearLayoutManager(activity)
        binding.rvFollowing.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val username = arguments?.getString(EXTRA_USERNAME)

        adapter.notifyDataSetChanged()

        binding.rvFollowing.setHasFixedSize(true)

        modelVM = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            VMIng::class.java)

        modelVM.getFollowing(username.toString())
        Log.d("username: ", username.toString())

        modelVM.getListFollowing().observe(activity!!, Observer { userItems ->
            if (userItems != null) {
                adapter.setData(userItems)
            }
        })
    }
}