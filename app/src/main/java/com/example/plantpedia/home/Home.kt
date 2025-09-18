package com.example.plantpedia.home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.plantpedia.Catogray.Category_Detail.category_Detail
import com.example.plantpedia.Common_Tree_Plant_Detail.Common_Tree_Detail
import com.example.plantpedia.R
import com.example.plantpedia.databinding.ActivityMainBinding
import com.example.plantpedia.databinding.FragmentHomeBinding
import com.google.android.gms.common.internal.service.Common
import kotlinx.coroutines.Runnable

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Home.newInstance] factory method to
 * create an instance of this fragment.
 */
class Home : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null



    // assign the _binding variable initially to null and
    // also when the view is destroyed again it has to be set to null
    private var _binding: FragmentHomeBinding? = null

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

    @SuppressLint("MissingInflatedId", "UseCompatLoadingForDrawables")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater,container,true)




        //common tree and ploant coading handle

        binding.tvRose.setOnClickListener {

            helo(binding.tvRose,"rosa-chinensis")


        }

        binding.tvCoconut.setOnClickListener {

            helo(binding.tvCoconut,"cocos-nucifera")


        }

        binding.tvNeem.setOnClickListener {

            helo(binding.tvNeem,"azadirachta-indica")


        }
        binding.tvMango.setOnClickListener {

            helo(binding.tvMango,"mangifera-indica")
        }

        binding.tvPomegranate.setOnClickListener {

            helo(binding.tvPomegranate,"punica-granatum")
        }

        binding.tvBanyan.setOnClickListener {

            helo(binding.tvBanyan,"ficus-benghalensis")
        }

        binding.tvPeepal.setOnClickListener {

            helo(binding.tvPeepal,"ficus-religiosa")
        }

        binding.tvTulsi.setOnClickListener {

            helo(binding.tvTulsi,"ocimum-tenuiflorum")
        }

        binding.tvGuava.setOnClickListener {

            helo(binding.tvGuava,"psidium-guajava")
        }

        binding.tvAshoka.setOnClickListener {

            helo(binding.tvAshoka,"saraca-asoca")
        }

        binding.tvHibiscus.setOnClickListener {

            helo(binding.tvHibiscus,"hibicus-rosa-sinensis")
        }

        binding.tvAloeVera.setOnClickListener {

            helo(binding.tvAloeVera,"aloe-barbadensis-miller")
        }

        binding.homeA.setOnClickListener {

            Toast.makeText(requireContext(),"A", Toast.LENGTH_SHORT).show()

            binding.homeRecyclerView.layoutManager = 
        }




















        return view
    }

    fun helo(item: TextView, slug:String){

        item.postDelayed(Runnable {
            item.setBackgroundColor(item.context.getColor(R.color.white))
            item.setBackgroundDrawable(requireContext().getDrawable(R.drawable.bg_search))


        },300)

        item.setBackgroundColor(item.context.getColor(R.color.green3))

        Toast.makeText(requireContext(),"helo", Toast.LENGTH_SHORT).show()



        val iNext = Intent(requireContext(), Common_Tree_Detail::class.java)
        iNext.putExtra("slug","$slug")
        startActivity(iNext)




    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Home.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Home().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}