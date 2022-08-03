package com.example.asnrestapi.adpter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.asnrestapi.FirstFragment
import com.example.asnrestapi.FirstFragmentDirections
import com.example.asnrestapi.R
import com.example.asnrestapi.data.post
import java.nio.file.Files.find

class ItemAdapter(
    private val Post : List<post>
) :RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(val view : View):RecyclerView.ViewHolder(view){
        val idItem : TextView = view.findViewById(R.id.PostIdItem)
        val titleItem : TextView = view.findViewById(R.id.PostTitleItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_view , parent , false)
        return ItemViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val posts = Post[position]
        holder.idItem.text = posts.id.toString()
        holder.titleItem.text = posts.title

        holder.view.setOnClickListener{
            val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(postid = posts.id)
            it.findNavController().navigate(action)

        }
    }

    override fun getItemCount() =  Post.size
}