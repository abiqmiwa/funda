package com.dicoding.picodiploma.mysubs2

import android.database.ContentObserver
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.picodiploma.mysubs2.DbContract.FavoriteColumns.Companion.CONTENT_URI
import com.dicoding.picodiploma.mysubs2.databinding.ActivityFavoriteUserBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class FavoriteUser : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteUserBinding
    private lateinit var adapterFav: AdapterFav

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Favorite User"

        adapterFav = AdapterFav()
        loadFavoriteAsync()

        val handlerThread = HandlerThread("DataObserver")
        handlerThread.start()
        val handler = Handler(handlerThread.looper)

        val myObserver = object : ContentObserver(handler){
            override fun onChange(selfChange: Boolean) {
                super.onChange(selfChange)
                loadFavoriteAsync()
            }
        }

        contentResolver.registerContentObserver(CONTENT_URI, true, myObserver)

        binding.rvFavorite.layoutManager = LinearLayoutManager(this)
        binding.rvFavorite.setHasFixedSize(true)
        binding.rvFavorite.adapter = adapterFav

    }

    private fun loadFavoriteAsync(){
        GlobalScope.launch(Dispatchers.Main) {
            binding.progressBar.visibility = View.VISIBLE
            val deferredFavorite = async(Dispatchers.IO) {
                val cursor = contentResolver.query(CONTENT_URI, null, null, null, null)
                HelperMap.mapCursorToArrayList(cursor)
            }
            val favorite = deferredFavorite.await()
            binding.progressBar.visibility = View.INVISIBLE
            if (favorite.size > 0){
                adapterFav.setData(favorite)
            } else {
                adapterFav.setData(ArrayList())
                showSnackbarMessage("Tidak ada data saat ini")
            }
        }
    }

    private fun showSnackbarMessage(message: String) {
        Snackbar.make(binding.rvFavorite, message, Snackbar.LENGTH_SHORT).show()
    }
}