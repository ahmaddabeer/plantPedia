package com.example.plantpedia.wishList

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.plantpedia.R

class Adapter(
    private val list: ArrayList<DataModel>,
    private val context: Context
) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.search_result_rv_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val modal: DataModel = list[position]
        holder.titleTV.text = modal.title
        holder.snippetTV.text = modal.displayedLink
        holder.descTV.text = modal.snippet

        // Open link in browser when item is clicked
        holder.itemView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(modal.link))
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    // ViewHolder to hold item views
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTV: TextView = itemView.findViewById(R.id.idTVTitle)
        val descTV: TextView = itemView.findViewById(R.id.idTVDescription)
        val snippetTV: TextView = itemView.findViewById(R.id.idTVSnippet)
    }
}