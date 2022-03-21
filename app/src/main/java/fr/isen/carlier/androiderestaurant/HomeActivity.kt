package fr.isen.carlier.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        var textView = findViewById(R.id.textView) as TextView
        var textView2 = findViewById(R.id.textView2) as TextView
        var textView3 = findViewById(R.id.textView3) as TextView

        textView.setOnClickListener {
            // make a toast on button click event
            Toast.makeText(this, "PAGE ENTRÉES", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, CategorySelected::class.java)
            intent.putExtra("NomGlobal","Entrées")
            startActivity(intent)
        }

        textView2.setOnClickListener {
            // make a toast on button click event
            Toast.makeText(this, "PAGE PLATS", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, CategorySelected::class.java)
            intent.putExtra("NomGlobal","Plats")
            startActivity(intent)
        }

        textView3.setOnClickListener {
            // make a toast on button click event
            Toast.makeText(this, "PAGE DESSERTS", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, CategorySelected::class.java)
            intent.putExtra("NomGlobal","Desserts")
            startActivity(intent)
        }


    }
}

