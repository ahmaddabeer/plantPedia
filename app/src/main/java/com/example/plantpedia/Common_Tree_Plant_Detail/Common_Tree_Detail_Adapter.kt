package com.example.plantpedia.Common_Tree_Plant_Detail

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.plantpedia.R
import com.example.plantpedia.Room.Plant
import com.example.plantpedia.Room.PlantDetaBase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Common_Tree_Detail_Adapter(

    private val context: Context,
    private val data: ArrayList<Common_Tree_Detail_Model>

) : RecyclerView.Adapter<Common_Tree_Detail_Adapter.ViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): Common_Tree_Detail_Adapter.ViewHolder {

        val view = LayoutInflater.from(context)
            .inflate(R.layout.categeory_detail_images_load_row, parent, false)
        return ViewHolder(view)


    }

    override fun onBindViewHolder(
        holder: Common_Tree_Detail_Adapter.ViewHolder,
        position: Int
    ) {
        val item = data[position]

        // 🌿 Image load with Glide
        Glide.with(holder.img.context)
            .load(item.img)
            .placeholder(R.drawable.baseline_image_search_24)
            .error(R.drawable.baseline_running_with_errors_24)
            .diskCacheStrategy(DiskCacheStrategy.ALL) // Cache all versions
            .timeout(60000) // 60 seconds timeout
            .into(holder.img)

        holder.title.text = item.imgname
        Log.d("title", item.imgname)

        // 🌿 Database instance (better: Adapter constructor se pass karo)
        val database = Room.databaseBuilder(
            holder.itemView.context,
            PlantDetaBase::class.java,
            "plantDB"
        ).build()

        // ✅ Pehle check karo ki already wishlist me hai ya nahi
        GlobalScope.launch {
            val existing = database.plantDao().getPlantByName(item.imgname)

            (holder.itemView.context as Activity).runOnUiThread {
                if (existing != null) {
                    holder.faverate.setImageResource(R.drawable.savefilled) // already saved
                } else {
                    holder.faverate.setImageResource(R.drawable.save) // not saved yet
                }
            }
        }

        // 🌿 Click listener
        holder.faverate.setOnClickListener {
            GlobalScope.launch {
                val existing = database.plantDao().getPlantByName(item.imgname)

                (holder.itemView.context as Activity).runOnUiThread {
                    if (existing == null) {
                        // Insert new plant
                        GlobalScope.launch {
                            database.plantDao().insertPlant(
                                Plant(0, item.imgname, item.img)
                            )
                        }
                        holder.faverate.setImageResource(R.drawable.savefilled)
                        Toast.makeText(
                            holder.itemView.context,
                            "🌱 \"${item.imgname}\" added to Wishlist 💚",
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        holder.faverate.setImageResource(R.drawable.savefilled)
                        Toast.makeText(
                            holder.itemView.context,
                            "⚠️ \"${item.imgname}\" is already in your Wishlist 🌿",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }


    override fun getItemCount(): Int = data.size

    public class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val img = view.findViewById<ImageView>(R.id.detailimages)
        val title = view.findViewById<TextView>(R.id.copyright)

        val faverate = view.findViewById<ImageView>(R.id.faveratehomescreen)


    }


}