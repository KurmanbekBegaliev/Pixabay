package com.example.pixabay

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class Repository @Inject constructor(private val api: PixabayApi) {

//    fun makeRequest(query: String, page: Int): List<ImageModel>{
//        var models = mutableListOf<ImageModel>()
//
//        api.getImage(q = query, page = page, per_page = 12).enqueue(
//            object : Callback<PixabayModel> {
//                override fun onResponse(
//                    call: Call<PixabayModel>,
//                    response: Response<PixabayModel>
//                ) {
//                    val back = response.body()
//                    models = back
//                }
//
//                override fun onFailure(call: Call<PixabayModel>, t: Throwable) {
//                    Log.d("TAG", "onFailure: ${t.stackTrace}")
//                }
//
//            }
//        )
//        models.let { return it }
//    }


    fun makeRequest(query: String, page: Int): LiveData<PixabayModel> {
        var liveData = MutableLiveData<PixabayModel>()

        api.getImage(q = query, page = page, per_page = 12).enqueue(
            object: Callback<PixabayModel> {
                override fun onResponse(
                    call: Call<PixabayModel>,
                    response: Response<PixabayModel>
                ) {
                    liveData.postValue(response.body())
                }

                override fun onFailure(call: Call<PixabayModel>, t: Throwable) {
                    Log.d("TAG", "onFailure: ${t.stackTrace}")
                }

            }
        )
        return liveData
    }


}