package com.example.asnrestapi.adpter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.asnrestapi.R
import com.example.asnrestapi.data.comment


class CommentAdapter (
    private val Comment : List<comment>
) : RecyclerView.Adapter<CommentAdapter.ItemViewHolder>() {

    class ItemViewHolder(val view : View): RecyclerView.ViewHolder(view){
        val idItem : TextView = view.findViewById(R.id.CommentIdItem)
        val nameItem : TextView = view.findViewById(R.id.CommentNameItem)
        val mailItem : TextView = view.findViewById(R.id.CommentEmailItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_comment_view , parent , false)
        return ItemViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val posts = Comment[position]
        holder.idItem.text = posts.id.toString()
        holder.nameItem.text = posts.name
        holder.mailItem.text = posts.email


    }

    override fun getItemCount() =  Comment.size

}