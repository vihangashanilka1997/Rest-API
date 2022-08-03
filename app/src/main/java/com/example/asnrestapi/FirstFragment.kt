package com.example.asnrestapi

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.asnrestapi.adpter.ItemAdapter
import com.example.asnrestapi.api.RetrofitAPI
import com.example.asnrestapi.data.post
import com.example.asnrestapi.databinding.FragmentFirstBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
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
        val call = RetrofitAPI.create().getPost()
        call.enqueue(object : Callback<List<post>>{
            override fun onResponse(call: Call<List<post>>, response: Response<List<post>>) {
                val posts = response.body()
                val adapter = posts?.let { ItemAdapter(it) }
                binding.recycler.adapter = adapter
            }

            override fun onFailure(call: Call<List<post>>, t: Throwable) {
                Log.d("FirstFragment" , t.message.toString())
            }
        })
    }
}