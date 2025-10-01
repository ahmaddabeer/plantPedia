package com.example.plantpedia.wishList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.plantpedia.Room.PlantDao
import com.example.plantpedia.Room.PlantDetaBase
import com.example.plantpedia.databinding.FragmentWishListBinding

class WishList : Fragment() {




    // assign the _binding variable initially to null and
    // also when the view is destroyed again it has to be set to null
    private var _binding: FragmentWishListBinding? = null

    private lateinit var database: PlantDetaBase
    private lateinit var plantAdapter: PlantAdapter


    // with the backing property of the kotlin we extract
    // the non null value of the _binding
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentWishListBinding.inflate(inflater, container, false)

        // RecyclerView setup
        binding.wishListRecyclerview.layoutManager = GridLayoutManager(requireContext(), 3)
        plantAdapter = PlantAdapter(requireContext(), emptyList())
        binding.wishListRecyclerview.adapter = plantAdapter



        // Database instance
        database = androidx.room.Room.databaseBuilder(
            requireContext(),
            PlantDetaBase::class.java,
            "plantDB"
        ).build()

        // LiveData observe karna
        database.plantDao().getplant().observe(viewLifecycleOwner) { plantList ->
            plantAdapter.updateList(plantList)
        }






        return binding.root
    }



}
