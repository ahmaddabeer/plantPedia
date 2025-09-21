package com.example.plantpedia.Common_Tree_Plant_Detail

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.plantpedia.R

class Common_Tree_Detail_Adapter(

    private val context: Context,
    private val data: ArrayList<Common_Tree_Detail_Model>

): RecyclerView.Adapter<Common_Tree_Detail_Adapter.ViewHolder>(){



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): Common_Tree_Detail_Adapter.ViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.categeory_detail_images_load_row,parent,false)
        return ViewHolder(view)




    }

    override fun onBindViewHolder(holder: Common_Tree_Detail_Adapter.ViewHolder, position: Int) {

        val item = data[position]

        Glide.with(holder.img.context)
            .load(item.img)
            .placeholder(R.drawable.baseline_image_search_24)
            .error(R.drawable.baseline_running_with_errors_24)
            .diskCacheStrategy(DiskCacheStrategy.ALL) // Cache all versions
            .timeout(60000) // 60 seconds timeout
            .into(holder.img)

        holder.title.setText(item.imgname)

        Log.d("title", item.imgname)









    }

    override fun getItemCount(): Int = data.size

    public class ViewHolder(view: View): RecyclerView.ViewHolder(view){

        val img = view.findViewById<ImageView>(R.id.detailimages)
        val title= view.findViewById<TextView>(R.id.copyright)


    }




}