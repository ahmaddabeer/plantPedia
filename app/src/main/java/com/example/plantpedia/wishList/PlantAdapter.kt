// PlantAdapter.kt
package com.example.plantpedia.wishList

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.plantpedia.R
import com.example.plantpedia.Room.Plant
import com.example.plantpedia.Room.PlantDetaBase
import com.example.plantpedia.databinding.ItemPlantBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PlantAdapter(
    private val context: Context,
    private var plantList: List<Plant>
) : RecyclerView.Adapter<PlantAdapter.PlantViewHolder>() {

    inner class PlantViewHolder(val binding: ItemPlantBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantViewHolder {
        val binding = ItemPlantBinding.inflate(LayoutInflater.from(context), parent, false)
        return PlantViewHolder(binding)
    }

    override fun getItemCount(): Int = plantList.size

    override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {
        val plant = plantList[position]
        holder.binding.scientificName.text = plant.scientificName

        holder.binding.plantImage.setOnClickListener {

            (context as android.app.Activity).runOnUiThread {




            }


        }

        holder.binding.plantImage.setOnLongClickListener {

            val database = Room.databaseBuilder(
                context,
                PlantDetaBase::class.java,
                "plantDB"
            ).build()

            android.app.AlertDialog.Builder(context)
                .setTitle("Delete Plant 🌱")
                .setMessage("Are you sure you want to remove \"${plant.scientificName}\" from your wishlist?")
                .setPositiveButton("Delete") { dialog, _ ->
                    GlobalScope.launch {
                        val existing = database.plantDao().getPlantByName(plant.scientificName)
                        if (existing != null) {
                            // Delete from DB
                            database.plantDao().deletePlant(existing)

                            (context as android.app.Activity).runOnUiThread {
                                // Remove from adapter list
                                val updatedList = plantList.filter { it.scientificName != plant.scientificName }
                                updateList(updatedList)

                                // ✅ Show Snackbar with Undo option
                                val snackbar = com.google.android.material.snackbar.Snackbar.make(
                                    holder.binding.root,
                                    "🌱 \"${plant.scientificName}\" deleted",
                                    com.google.android.material.snackbar.Snackbar.LENGTH_LONG
                                )
                                snackbar.setAction("UNDO") {
                                    GlobalScope.launch {
                                        // Re-insert into DB
                                        database.plantDao().insertPlant(existing)
                                        (context as android.app.Activity).runOnUiThread {
                                            // Restore in adapter list
                                            updateList(plantList + existing)
                                        }
                                    }
                                }
                                snackbar.show()
                            }
                        }
                    }
                    dialog.dismiss()
                }
                .setNegativeButton("Cancel") { dialog, _ ->
                    dialog.dismiss()
                }
                .create()
                .show()

            true
        }


        Glide.with(holder.binding.plantImage.context)
            .load(plant.imgUrl)
            .placeholder(R.drawable.baseline_image_search_24)
            .error(R.drawable.baseline_running_with_errors_24)
            .diskCacheStrategy(DiskCacheStrategy.ALL) // Cache all versions
            .timeout(60000) // 60 seconds timeout
            .into(holder.binding.plantImage)


    }


    fun updateList(newList: List<Plant>) {
        plantList = newList
        notifyDataSetChanged()
    }
}
