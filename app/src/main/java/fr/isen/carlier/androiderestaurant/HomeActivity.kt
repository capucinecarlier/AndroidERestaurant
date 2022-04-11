package fr.isen.carlier.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import fr.isen.carlier.androiderestaurant.databinding.ActivityHomeBinding



class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.HomeStarters.setOnClickListener{
            goToCategory(getString(R.string.entr_es))
        }

        binding.HomeMainCourse.setOnClickListener{
            goToCategory(getString(R.string.plats))
        }

        binding.HomeDesserts.setOnClickListener{
            goToCategory(getString(R.string.desserts))
        }

        binding.BleButton.setOnClickListener {
            val intent = Intent(this, BleScanActivity:: class.java)
            startActivity(intent)

        }

        binding.panier.setOnClickListener {
            val intent = Intent(this, PanierActivity:: class.java)
            startActivity(intent)

        }

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("HomeActivity","Mon activité est détruite")
    }

    private fun goToCategory(category: String){
        val intent = Intent(this, CategorySelected:: class.java)
        Toast.makeText(
            this@MainActivity,
            category,
            Toast.LENGTH_LONG
        ).show()
        intent.putExtra("categoryName", category)
        startActivity(intent)
    }



}

