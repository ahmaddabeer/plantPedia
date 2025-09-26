package com.example.plantpedia.Catogray

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.SpinnerAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.example.plantpedia.R
import com.example.plantpedia.databinding.FragmentCatogaryBinding
import com.example.plantpedia.databinding.FragmentHomeBinding
import org.json.JSONObject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Catogary.newInstance] factory method to
 * create an instance of this fragment.
 */
class Catogary : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    // assign the _binding variable initially to null and
    // also when the view is destroyed again it has to be set to null
    private var _binding: FragmentCatogaryBinding? = null

    // with the backing property of the kotlin we extract
    // the non null value of the _binding
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCatogaryBinding.inflate(inflater, container, false)

        val editsearch: EditText = binding.categoryEditsearch

        val recyclerView: RecyclerView = binding.catgoryRecyclerview

        recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)

        val SpinAdapter: ArrayAdapter<*>

        val helo = arrayOf(


            "Search By Page Number",
            "Search By Common Name",
            "Search By Sintific Name"
        )


        SpinAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, helo)

        binding.searchTypeChange.adapter = SpinAdapter
        val loader: ProgressBar = binding.loader


        val nameList = ArrayList<categoryModel>()
        val adapter = categoryAdapter(requireContext(), nameList)
        recyclerView.adapter = adapter


        // ek common function for API call
        fun loadPlants(url: String) {
            loader.visibility = View.VISIBLE

            AndroidNetworking.get(url)
                .setPriority(com.androidnetworking.common.Priority.HIGH)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(p0: JSONObject?) {
                        nameList.clear()

                        val dataArray = p0!!.getJSONArray("data")

                        for (i in 0 until dataArray.length()) {
                            val obj = dataArray.getJSONObject(i)

                            val cn: String = obj.getString("scientific_name")
                            val imurl: String = obj.getString("image_url")
                            val slug: String = obj.getString("slug")
                            val plant = categoryModel(cn, imurl, slug)

                            nameList.add(plant)
                        }

                        adapter.notifyDataSetChanged()
                        loader.visibility = View.GONE   // hide after success
                    }

                    override fun onError(p0: ANError?) {
                        Toast.makeText(requireContext(), p0?.message, Toast.LENGTH_SHORT).show()
                        loader.visibility = View.GONE   // hide on error
                    }
                })
        }

        // Default page=1 load on start
        var defaultUrl =
            "https://trefle.io/api/v1/plants?token=od-gIUe-eHFoxtSQsJWcdtmtFsRckGQjJJHGP7YslmU&page=1"
        loadPlants(defaultUrl)

        // listener on EditText
        editsearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val number = s.toString().trim()

                var url = defaultUrl

                if (number.isNotEmpty() && number.all { it.isDigit() }) {
                    url =
                        "https://trefle.io/api/v1/plants?token=od-gIUe-eHFoxtSQsJWcdtmtFsRckGQjJJHGP7YslmU&page=$number"
                } else if (number.isNotEmpty()) {
                    url =
                        "https://trefle.io/api/v1/plants/search?token=od-gIUe-eHFoxtSQsJWcdtmtFsRckGQjJJHGP7YslmU&q=$number"
                }

                loadPlants(url)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Catogary.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Catogary().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}