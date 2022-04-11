package fr.isen.carlier.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import fr.isen.carlier.androiderestaurant.databinding.ActivityDetailBinding
import model.Item
import kotlin.math.max

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private var itemCount = 1F
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val item = intent.getSerializableExtra(CategorySelected.ITEM_KEY) as Item
        binding.detailTitle.text = item.name_fr

        //binding.ingredientView.text = item.ingredients.joinToString(", ") { it.name_fr }

        val carouselAdapter = CarouselAdapter(this, item.images)
        binding.detailSlider.adapter = carouselAdapter
    }



        private fun observeClick(){
            binding.moins.setOnClickListener{
                itemCount= max(1f, itemCount-1)
                //refreshShopButton()
                /*val count = itemCount -1
                if(count > 0){
                    itemCount = count
                    refreshShopButton()
                }*/

            }
            binding.plus.setOnClickListener{
                itemCount++
                //refreshShopButton()
            }

    }
}