package fr.isen.carlier.androiderestaurant

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.carlier.androiderestaurant.databinding.CellPanierBinding


class PanierAdapter(private val items: List<BasketItem>, val deleteClickListener: (BasketItem)-> Unit): RecyclerView.Adapter<PanierAdapter.BasketViewHolder>() {
    lateinit var context: Context

    class BasketViewHolder(binding: CellPanierBinding) : RecyclerView.ViewHolder(binding.root) {
        val mealName: TextView = binding.name
        val price: TextView = binding.price
        val compteur: TextView = binding.compteur
        val delete: ImageButton = binding.deleteButton
        val imageView: ImageView = binding.imageView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
        context = parent.context
        return BasketViewHolder(
            CellPanierBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        val basketItem = items[position]
        holder.mealName.text = basketItem.plats.name
        holder.compteur.text = "${context?.getString(R.string.compteur)} ${basketItem.compteur.toString()}"

        holder.price.text = "${basketItem.plats.prices.first().price} €"

        holder.delete.setOnClickListener {
            deleteClickListener.invoke(basketItem)
        }

        Picasso.get()
            .load(basketItem.plats.getThumbnailURL())
            .into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return items.count()
    }
}