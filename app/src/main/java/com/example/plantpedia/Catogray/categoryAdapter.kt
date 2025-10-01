package com.example.plantpedia.Catogray

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.bumptech.glide.Glide
import com.example.plantpedia.Catogray.Category_Detail.category_Detail
import com.example.plantpedia.R
import com.example.plantpedia.Room.Plant
import com.example.plantpedia.Room.PlantDetaBase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class categoryAdapter(

    private val context: Context,
    private val items: ArrayList<categoryModel>

) : RecyclerView.Adapter<categoryAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val view =
            LayoutInflater.from(context).inflate(R.layout.category_item_single_row, parent, false)

        return ViewHolder(view)
    }
    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val item = items[position]

        // 🌿 Image load karo
        Glide.with(holder.image.context)
            .load(item.img)
            .placeholder(R.drawable.baseline_image_search_24)
            .error(R.drawable.baseline_running_with_errors_24)
            .into(holder.image)

        holder.itemname.text = item.common_Name

        // 🌿 Image click → detail activity
        holder.image.setOnClickListener { v ->
            val intent = Intent(v.context, category_Detail::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                putExtra("name", item.common_Name)
                putExtra("url", item.img)
                putExtra("slug", item.slug)
            }
            Log.d("hleo", "${item.slug}")
            v.context.startActivity(intent)
        }

        // 🌿 Database instance (better hoga agar Adapter ke constructor me ek hi baar pass karo)
        val database = Room.databaseBuilder(
            holder.itemView.context,
            PlantDetaBase::class.java,
            "plantDB"
        ).build()

        // 🌿 First check → already wishlist me hai kya?
        GlobalScope.launch {
            val existing = database.plantDao().getPlantByName(item.common_Name)
            (holder.itemView.context as Activity).runOnUiThread {
                if (existing != null) {
                    holder.faverateButton.setImageResource(R.drawable.savefilled)
                } else {
                    holder.faverateButton.setImageResource(R.drawable.save)
                }
            }
        }

        // 🌿 Click → insert if not exist
        holder.faverateButton.setOnClickListener {
            GlobalScope.launch {
                val existing = database.plantDao().getPlantByName(item.common_Name)

                (holder.itemView.context as Activity).runOnUiThread {
                    if (existing == null) {
                        GlobalScope.launch {
                            database.plantDao().insertPlant(
                                Plant(0, item.common_Name, item.img)
                            )
                        }
                        holder.faverateButton.setImageResource(R.drawable.savefilled)
                        Toast.makeText(
                            holder.itemView.context,
                            "🌱 \"${item.common_Name}\" added to Wishlist 💚",
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        holder.faverateButton.setImageResource(R.drawable.savefilled)
                        Toast.makeText(
                            holder.itemView.context,
                            "⚠️ \"${item.common_Name}\" is already in your Wishlist 🌿",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }


    override fun getItemCount(): Int = items.size


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val image = itemView.findViewById<ImageView>(R.id.category_row_item_image)
        val itemname = itemView.findViewById<TextView>(R.id.categoryrow_item_text)

        val faverateButton = itemView.findViewById<ImageButton>(R.id.faverateCatogaryScreen)

    }
}