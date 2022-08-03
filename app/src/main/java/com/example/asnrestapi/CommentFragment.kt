package com.example.asnrestapi

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.asnrestapi.adpter.CommentAdapter
import com.example.asnrestapi.api.RetrofitAPI
import com.example.asnrestapi.data.comment
import com.example.asnrestapi.databinding.FragmentCommentBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CommentFragment : Fragment() {
    private var _binding: FragmentCommentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCommentBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        fetch()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun fetch(){
        val call = RetrofitAPI.create().getComments()
        call.enqueue(object : Callback<List<comment>>{
            override fun onResponse(call: Call<List<comment>>, response: Response<List<comment>>) {
                val comments = response.body()
                val adapter = comments?.let { CommentAdapter(it) }
                binding.recyclerComment.adapter = adapter
            }

            override fun onFailure(call: Call<List<comment>>, t: Throwable) {
                Log.d("CommentFragment" , t.message.toString())
            }
        })
    }
}