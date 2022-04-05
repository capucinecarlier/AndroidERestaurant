package fr.isen.carlier.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import fr.isen.carlier.androiderestaurant.databinding.ActivityCategorySelectedBinding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import model.DataResult
import model.Item
import org.json.JSONObject


class CategorySelected : AppCompatActivity() {
    private lateinit var binding: ActivityCategorySelectedBinding

    private lateinit var customAdapter: CustomerAdaptater

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityCategorySelectedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        super.onCreate(savedInstanceState)


        val titleName = intent.getStringExtra("categoryName") ?: ""
        binding.categoryName.text = titleName


        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        customAdapter = CustomerAdaptater(arrayListOf()){
        }


        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = customAdapter

        loadDataFromServerByCategory(titleName)
    }

    companion object{
        val ITEM_KEY = "itemList"
    }


    private fun loadDataFromServerByCategory(category: String) {
        val queue = Volley.newRequestQueue(this)
        val url = "http://test.api.catering.bluecodegames.com/menu"
        val jsonObject = JSONObject()
        jsonObject.put("id_shop", "1")

        val stringRequest = JsonObjectRequest(
            Request.Method.POST, url, jsonObject,
            { response ->
                binding.progressBar.isVisible = false
                val strResp = response.toString()
                val dataResult = Gson().fromJson(strResp, DataResult::class.java)

                val items = dataResult.data.firstOrNull{ it.name_fr == category }?.items ?: arrayListOf()
                binding.recyclerView.adapter = CustomerAdaptater(items){
                    val intent = Intent(this, DetailActivity::class.java)
                    intent.putExtra(ITEM_KEY, it)
                    startActivity(intent)
                }

            }, {
                binding.progressBar.isVisible = false
                Log.d("API", "message ${it.message}")

            })
        queue.add(stringRequest)
    }


}