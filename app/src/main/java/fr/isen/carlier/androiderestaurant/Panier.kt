package fr.isen.carlier.androiderestaurant

import android.content.Context
import com.google.gson.GsonBuilder
import java.io.File
import java.io.Serializable

class Panier(val items: MutableList<BasketItem>):  Serializable {
        var itemsCount: Int = 0
                get() {

                        //compter items
                        /*var count = 0
                        items.forEach {
                            count += it.quantity
                        }
                        return count*/

                        val count = items.map {
                                it.compteur
                        }.reduceOrNull { acc, i -> acc +i } ?: 0
                        return (count ?:0) as Int

                }

        var totalPrice: Float = 0f
                get() {

                        return (items.map { item ->
                                item.compteur * item.plats.prices.first().price.toFloat()
                        }.reduceOrNull { acc, fl -> acc + fl } ?: 0f) as Float
                }

        fun addItem(item: Plats, compteur: Int){
                val existingItem = items.firstOrNull{ it.plats.name == item.name }
                existingItem?.let {
                        existingItem.compteur += compteur
                } ?: run {
                        val basketItem = BasketItem(item, compteur)
                        items.add(basketItem)
                }
        }

        fun removeItem(basketItem: BasketItem){
                items.remove(basketItem)
        }

        fun clear(){
                items.removeAll { true }
        }

        fun save(context: Context){
                val jsonFile =  File(context.cacheDir.absolutePath + BASKET_FILE)

                jsonFile.writeText(toJson())
                updateCounter(context)
        }


        fun toJson(): String {
                return GsonBuilder().create().toJson(this)
        }

        private fun updateCounter(context: Context){
                val sharedPreferences = context.getSharedPreferences(USER_PREFERENCES_NAME, Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putInt(ITEMS_COUNT, itemsCount)
                editor.apply()
        }


        companion object{
                fun getBasket(context: Context): Panier {
                        val count = Panier(mutableListOf()).itemsCount

                        val jsonFile = File(context.cacheDir.absolutePath + BASKET_FILE)
                        return if (jsonFile.exists()) {
                                val json = jsonFile.readText()
                                GsonBuilder().create().fromJson(json, Panier::class.java)
                        } else {
                                Panier(mutableListOf())
                        }
                }

                const val BASKET_FILE = "basket.json"
                const val ITEMS_COUNT = "ITEMS_COUNT"
                const val USER_PREFERENCES_NAME = "USER_PREFERENCES_NAME"
        }
}

class BasketItem(val plats: Plats,var compteur: Int ): Serializable{

}