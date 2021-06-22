package com.dicoding.picodiploma.mysubs2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.picodiploma.mysubs2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var adapterUser: AdapterUser
    private lateinit var binding: ActivityMainBinding

    private lateinit var mainViewModel: VMMain

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapterUser = AdapterUser()
        adapterUser.notifyDataSetChanged()
        mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            VMMain::class.java)

        binding.rvUser.layoutManager = LinearLayoutManager(this)
        binding.rvUser.adapter = adapterUser

        adapterUser.setOnItemClickCallback(object : AdapterUser.OnItemClickCallback{
            override fun onItemClicked(data: ModelUser) {
                showSelectedUser(data)
            }
        })

        binding.btnSearch.setOnClickListener{
            val userName = binding.edtQuery.text.toString()
            if (userName.isEmpty()) return@setOnClickListener
            showLoading(true)

            mainViewModel.setUser(userName)

        }
        mainViewModel.getUser().observe(this, { userItems ->
            if (userItems != null) {
                adapterUser.setData(userItems)
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

    private fun showSelectedUser(user: ModelUser) {
        val moveIntent = Intent(this@MainActivity, DetailUser::class.java)
        moveIntent.putExtra(DetailUser.EXTRA_USER, user)
        moveIntent.putExtra(DetailUser.EXTRA_USERNAME, user.username)
        startActivity(moveIntent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_option, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_favorite -> {
                val moveIntent = Intent(this@MainActivity, FavoriteUser::class.java)
                startActivity(moveIntent)
            }
            R.id.action_change_settings -> {
                val mIntent = Intent(this@MainActivity, Settings::class.java)
                startActivity(mIntent)
            }
        }

        return super.onOptionsItemSelected(item)
    }

}