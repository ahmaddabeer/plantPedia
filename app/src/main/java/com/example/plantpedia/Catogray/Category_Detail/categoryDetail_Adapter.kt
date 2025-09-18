package com.example.plantpedia.Catogray.Category_Detail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.plantpedia.R

class categoryDetail_Adapter(

    private val context: Context,
    private val data:ArrayList<CDdetai_Model>

) : RecyclerView.Adapter<categoryDetail_Adapter.ViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.categeory_detail_images_load_row,parent,false)
        return ViewHolder(view)



    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {




        val item = data[position]


        holder.copyright.setText(item.copyright)

        Glide.with(holder.imageurl.context)
            .load(item.imgurl)
            .placeholder(R.drawable.baseline_image_search_24)
       //      .thumbnail(0.25f)

            .error(R.drawable.baseline_running_with_errors_24)
            .diskCacheStrategy(DiskCacheStrategy.ALL) // Cache all versions
            .timeout(60000) // 60 seconds timeout
            .into(holder.imageurl)
    }

    override fun getItemCount(): Int = data.size


    class ViewHolder(view: View): RecyclerView.ViewHolder(view){



        val imageurl = view.findViewById<ImageView>(R.id.detailimages)
        val copyright = view.findViewById<TextView>(R.id.copyright)
    }

}