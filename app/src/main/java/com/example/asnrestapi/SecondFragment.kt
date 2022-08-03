package com.example.asnrestapi

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.asnrestapi.adpter.ItemAdapter
import com.example.asnrestapi.api.RetrofitAPI
import com.example.asnrestapi.data.post
import com.example.asnrestapi.databinding.FragmentSecondBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    companion object {
        const val POST_ID = "postid"
        private val TAG = SecondFragment::class.java.simpleName
    }

    private var _binding: FragmentSecondBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val postid = arguments?.getInt(POST_ID)

        if (postid != null) {
            RetrofitAPI.create().getPostDetails(postid).enqueue(object :Callback<post>{
                override fun onResponse(call: Call<post>, response: Response<post>) {
                    val post = response.body()

                    if (post != null) {
                        binding.textId.text = post.id.toString()
                        binding.textBody.text = post.body
                        binding.textTitle.text = post.title

                        binding.buttonSecond.setOnClickListener{
                            val action = SecondFragmentDirections
                                .actionSecondFragmentToCommentFragment()
                                it.findNavController().navigate(action)

                        }
                    }
                }

                override fun onFailure(call: Call<post>, t: Throwable) {
                    Log.e(TAG,"Got error :"+t.localizedMessage)
                }
            })
        }







    }
  override fun onDestroyView() {
      super.onDestroyView()
      _binding = null
      }

    }
