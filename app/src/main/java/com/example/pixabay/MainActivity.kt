package com.example.pixabay

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pixabay.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val adapter = RvAdapter()
    private var page = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        initListeners()

    }

    private fun initViews() {
        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)
        binding.recyclerView.adapter = adapter
    }

    private fun initListeners() {
        edtListener()
        rvListener()


            binding.btnClick.setOnClickListener {
            if (binding.btnClick.text == "SEARCH") {
                makeRequest(1)
                binding.btnClick.text = "NEXT PAGE"
            } else {
                nextPage()
            }
        }
    }

    private fun makeRequest(page: Int) {
        App.api.getImage(q = binding.edtSearch.text.toString(), page = page, per_page = 12).enqueue(
            object: Callback<PixabayModel> {
                override fun onResponse(
                    call: Call<PixabayModel>,
                    response: Response<PixabayModel>
                ) {
                    response.body()?.let { adapter.addList(page, it.hits) }
                }

                override fun onFailure(call: Call<PixabayModel>, t: Throwable) {
                    Log.d("TAG", "onFailure: ${t.stackTrace}")
                }

            }
        )
    }

    private fun edtListener(){
        binding.edtSearch.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.btnClick.text = "SEARCH"
                page = 1
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
    }

    private fun rvListener() {
        binding.recyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy == 1) nextPage()
                Log.d("TAG", "RECYCLER VIEW SCROLLED")
            }
        })

    }

    private fun nextPage() {
        page++
        makeRequest(page = page)
    }
}