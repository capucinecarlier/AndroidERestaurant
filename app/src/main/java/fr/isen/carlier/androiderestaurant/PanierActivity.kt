package fr.isen.carlier.androiderestaurant

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import fr.isen.carlier.androiderestaurant.databinding.ActivityPanierBinding
import org.json.JSONObject

class PanierActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPanierBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPanierBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadList()

        binding.orderButton.setOnClickListener{
            startActivityForResult(intent, REQUEST_CODE)
        }
    }


    private fun loadList() {
        val basket = Panier.getBasket(this)
        val items = basket.items
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = PanierAdapter(items) {
            basket.removeItem(it)
            basket.save(this)
            loadList()
        }
    }

    private fun makeRequest(){
        val path = "http://test.api.catering.bluecodegames.com/"

        val queue = Volley.newRequestQueue(this)
        val basket = Panier.getBasket(this)
        val parameters = JSONObject()


        val request = JsonObjectRequest(
            Request.Method.POST,
            path,
            parameters,
            {
                Log.d("request", it.toString(2))
                basket.clear()
                basket.save(this)
                finish()
            },
            {
                Log.d("request", it.message ?: "Une erreur est survenue")
            }
        )

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK){
            Toast.makeText(this, "SEND ORDER", Toast.LENGTH_LONG).show()
        }
    }

    companion object{
        const val REQUEST_CODE = 111
    }
}