package com.example.plantpedia.Catogray

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.plantpedia.Catogray.Category_Detail.category_Detail
import com.example.plantpedia.R


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

        Glide.with(holder.image.context)
            .load(item.img)
            .placeholder(R.drawable.baseline_image_search_24)
            .error(R.drawable.baseline_running_with_errors_24)
            .into(holder.image)

        holder.itemname.setText(item.common_Name)

        holder.image.setOnClickListener {v ->

            val intent= Intent(v.context, category_Detail::class.java)

            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            var itemname = holder.itemname.text
            var imgurl = item.img
            var slug = item.slug
            intent.putExtra("name","$itemname")
            intent.putExtra("url","$imgurl")
            intent.putExtra("slug","$slug")
            Log.d("hleo","$slug")



            v.context.startActivity(intent)
        }




    }

    override fun getItemCount(): Int = items.size


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val image = itemView.findViewById<ImageView>(R.id.category_row_item_image)
        val itemname = itemView.findViewById<TextView>(R.id.categoryrow_item_text)

    }
}