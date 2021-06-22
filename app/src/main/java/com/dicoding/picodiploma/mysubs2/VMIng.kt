package com.dicoding.picodiploma.mysubs2

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONArray
import java.lang.Exception

class VMIng: ViewModel() {
    val listData = ArrayList<ModelUser>()
    val listFollowing = MutableLiveData<ArrayList<ModelUser>>()

    fun getFollowing(userNames : String){
        val client = AsyncHttpClient()
        val url = "https://api.github.com/users/$userNames/following"
        client.addHeader("Authorization", "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")
        client.addHeader("User-Agent", "Request")
        client.get(url, object : AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray
            ) {
                try {
                    val result = String(responseBody)
                    val responseArray = JSONArray(result)
                    for (i in 0 until responseArray.length()) {
                        val item = responseArray.getJSONObject(i)
                        val userItems= ModelUser()
                        userItems.usernameFollowing = item.getString("login")
                        userItems.avatarFollowing = item.getString("avatar_url")
                        listData.add(userItems)
                    }
                    listFollowing.postValue(listData)

                }catch (e: Exception){
                    Log.d("Exception", e.message.toString())
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                Log.d("onFailure", error?.message.toString())
            }

        })
    }

    fun getListFollowing(): LiveData<ArrayList<ModelUser>> {
        return listFollowing
    }
}