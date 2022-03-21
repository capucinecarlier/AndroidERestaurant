package fr.isen.carlier.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class CategorySelected : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_selected)

        var nomGlobal = intent.getStringExtra("NomGlobal")
        var categoryNom = findViewById(R.id.categoryName) as TextView
        categoryNom.text = nomGlobal
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("destroy", "onDestroy: ")
    }
}