package com.example.plantpedia.home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.example.plantpedia.Common_Tree_Plant_Detail.Common_Tree_Detail
import com.example.plantpedia.Common_Tree_Plant_Detail.Common_Tree_Detail_Adapter
import com.example.plantpedia.Common_Tree_Plant_Detail.Common_Tree_Detail_Model
import com.example.plantpedia.R
import com.example.plantpedia.databinding.FragmentHomeBinding
import org.json.JSONObject

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
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)


        binding.homeRecyclerView.layoutManager = GridLayoutManager(requireContext(), 3)




        //common tree and ploant coading handle

        binding.tvRose.setOnClickListener {

            helo(binding.tvRose, "rosa-chinensis")


        }

        binding.tvCoconut.setOnClickListener {

            helo(binding.tvCoconut, "cocos-nucifera")


        }

        binding.tvNeem.setOnClickListener {

            helo(binding.tvNeem, "azadirachta-indica")


        }
        binding.tvMango.setOnClickListener {

            helo(binding.tvMango, "mangifera-indica")
        }

        binding.tvPomegranate.setOnClickListener {

            helo(binding.tvPomegranate, "punica-granatum")
        }

        binding.tvBanyan.setOnClickListener {

            helo(binding.tvBanyan, "ficus-benghalensis")
        }

        binding.tvPeepal.setOnClickListener {

            helo(binding.tvPeepal, "ficus-religiosa")
        }

        binding.tvTulsi.setOnClickListener {

            helo(binding.tvTulsi, "ocimum-tenuiflorum")
        }

        binding.tvGuava.setOnClickListener {

            helo(binding.tvGuava, "psidium-guajava")
        }

        binding.tvAshoka.setOnClickListener {

            helo(binding.tvAshoka, "saraca-asoca")
        }

        binding.tvHibiscus.setOnClickListener {

            helo(binding.tvHibiscus, "hibicus-rosa-sinensis")
        }

        binding.tvAloeVera.setOnClickListener {

            helo(binding.tvAloeVera, "aloe-barbadensis-miller")
        }


        // alphabetical order item load---------------------------


        // home screen load hote hi ak default a ko load karadiya






        binding.homeA.setOnClickListener {
            val nameList = arrayListOf(
                "Alyssum",
                "Anise",
                "apple",
                "Apple Mint",
                "acacia",
                "almond",
                "avacado",
                "arjun",
                "Aeonium",
                "African Daisy",
                "Ajuga"
            )
            val common_name = nameList.joinToString(",")
            recyclerviewload(common_name, binding.homeA, "A")
        }

        binding.homeB.setOnClickListener {
            val nameList = arrayListOf(
                "Broome raintree",
                "Bila",
                "Button fern",
                "Broom",
                "Begonia",
                "Big Bluestem",
                "Bur Oak",
                "Bromeliad"
            )
            val name = nameList.joinToString(",")
            recyclerviewload(name, binding.homeB, "B")
        }

        binding.homeC.setOnClickListener {
            val nameList = arrayListOf(
                "Carrot",
                "Canna",
                "Candytuft",
                "Custard-apple",
                "Cabbage",
                "Cactus",
                "Calendula",
                "Camellia",
                "Candy corn plant",
                "Coral Tree"
            )
            val name = nameList.joinToString(",")
            recyclerviewload(name, binding.homeC, "C")
        }

        binding.homeD.setOnClickListener {
            val nameList = arrayListOf(
                "Daisy",
                "Daisy",
                "Dahlia",
                "Daisy",
                "Ditabark",
                "Dwarf palmetto",
                "Desert rose",
                "Daisy",
                "Desert rose",
                "Dogwood"
            )
            val name = nameList.joinToString(",")
            recyclerviewload(name, binding.homeD, "D")
        }

        binding.homeE.setOnClickListener {
            val nameList = arrayListOf(
                "Evergreen oak",
                "European ash",
                "Eryngo",
                "European larch",
                "Eastern hemlock",
                "Eastern cottonwood",
                "Elephant grass",
                "Egyptian lotus",
                "Escallonia",
                "Elecampane",
                "Endive",
                "Ephedra",
                "Elkhorn fern",
                "Espino",
                "Eryngium"


            )
            val name = nameList.joinToString(",")
            recyclerviewload(name, binding.homeE, "E")
        }

        binding.homeF.setOnClickListener {
            val nameList = arrayListOf(
                "Field maple",
                "Forget-me-not",
                "Foxglove",
                "Fennel",
                "Feverfew",
                "Firethorn",
                "Flax",
                "Fountain grass",
                "Fuchsia",
                "Forsythia"
            )
            val name = nameList.joinToString(",")
            recyclerviewload(name, binding.homeF, "F")
        }

        binding.homeG.setOnClickListener {

            val nameList = arrayListOf(

                "Green ash",
                "Ginkgo",
                "Globe thistle",
                "Groundnut",
                "Garlic",
                "Golden samphire",
                "Governor's plum",
                "Gazania",
                "Gooseneck Loosestrife",
                "Golden curant",
                "Grass tree"


            )
            val name = nameList.joinToString(",")
            recyclerviewload(name, binding.homeG, "G")
        }

        binding.homeH.setOnClickListener {
            val nameList = arrayListOf(
                "Field maple",
                "Forget-me-not",
                "Foxglove",
                "Fennel",
                "Feverfew",
                "Firethorn",
                "Hawthorn",
                "Heather",
                "Hyacinth",
                "Heather",
                "Horse chestnut",
                "Figwort",
                "Hyssop",
                "Honeysuckle"


            )
            val name = nameList.joinToString(",")
            recyclerviewload(name, binding.homeH, "H")
        }

        binding.homeI.setOnClickListener {
            val nameList = arrayListOf(

                "Indian rosewood",
                "Italilan cypress",
                "Indian strawberry",
                "Indian lettuce",
                "Indian nightshade",
                "Indian tobacco",
                "Indian maple",
                "Ivy",
                "Ivy gourd",
                "Italian arum",
                "Inkberry",
                "Isotoma"


            )
            val name = nameList.joinToString(",")
            recyclerviewload(name, binding.homeI, "I")
        }

        binding.homeJ.setOnClickListener {

            val nameList = arrayListOf(


                "Japanese knotweed",
                "Japanese honeysuckle",
                "Japanese rose",
                "Johnsongrass",
                "Japanese maple",
                "Japanese holly fern",
                "Japanese snowbell",
                "Japanese hop",
                "Japanese flowering dogwood",
                "Japanese cherry",
                "Japanese chestnut",
                "Japanese angelica tree",
                "Japanese holly",
                "Japanese privet",
                "Jambolan",
                "Japanese pieris",
                "Japanese persimmon",
                "Jack"
            )
            val name = nameList.joinToString(",")
            recyclerviewload(name, binding.homeJ, "J")
        }

        binding.homeK.setOnClickListener {

            val nameList = arrayListOf(
                "Kalanchoe",
                "Kangaroo paw",
                "Kudzu",
                "Korean maple",
                "Kowhai",
                "Korean cherry",
                "Korean pine",
                "Knobcone pine",
                "Kumquat",
                "Khat",
                "Kangaroo paw"
            )


            val name = nameList.joinToString(",")
            recyclerviewload(name, binding.homeK, "K")
        }

        binding.homeL.setOnClickListener {

            val nameList = arrayListOf(
                "Linden",
                "Lombardy poplar",
                "Loblolly pine",
                "Longleaf pine",
                "Loquat",
                "Limber pine",
                "Longan",
                "Luehea",
                "Leadwood",
                "Lavender tree"
            )

            val name = nameList.joinToString(",")
            recyclerviewload(name, binding.homeL, "L")
        }

        binding.homeM.setOnClickListener {

            val nameList = arrayListOf(
                "Mimosa",
                "Myrtle",
                "Mountain pine",
                "Mediterranean cypress",
                "Marula",
                "Manchineel",
                "Milkwood",
                "Monkey pod",
                "Mahua",
                "Mesquite"
            )

            val name = nameList.joinToString(",")
            recyclerviewload(name, binding.homeM, "M")
        }

        binding.homeN.setOnClickListener {


            val nameList = arrayListOf(
                "Northern red oak", "Northern hackberry", "Neem", "Northern catalpa", "Northern ash"
            )

            val name = nameList.joinToString(",")
            recyclerviewload(name, binding.homeN, "N")
        }

        binding.homeO.setOnClickListener {

            val nameList = arrayListOf(
                "Oak",
                "Oregon ash",
                "Olive",
                "Osmanthus",



            )


            val name = nameList.joinToString(",")
            recyclerviewload(name, binding.homeO, "O")
        }

        binding.homeP.setOnClickListener {

            val nameList = arrayListOf(
                "Primrose",
                "Pomegranate",
                "Peach",
                "Persimmon",
                "Plum",
                "Pepper",
                "Pine"
            )
            val name = nameList.joinToString(",")
            recyclerviewload(name, binding.homeP, "P")
        }

        binding.homeQ.setOnClickListener {
            val nameList = arrayListOf(
                "Quinine", "Quassia"
            )
            val name = nameList.joinToString(",")
            recyclerviewload(name, binding.homeQ, "Q")
        }

        binding.homeR.setOnClickListener {
            val nameList = arrayListOf(
                "Respberry",
                "Rosemary",
                "Rhododendron",
                "Redwood"
            )
            val name = nameList.joinToString(",")
            recyclerviewload(name, binding.homeR, "R")
        }

        binding.homeS.setOnClickListener {

            val nameList = arrayListOf(
                "Sunflower",
                "Sassafras",
                "Sage",
                "Spinach",
                "Sorghum",
                "Sugarcane",
                "Sesbania"
            )

            val name = nameList.joinToString(",")
            recyclerviewload(name, binding.homeS, "S")
        }

        binding.homeT.setOnClickListener {
            val nameList = arrayListOf(
                "Tamarind", "Teak", "Tulip", "Tiger Lily", "Tomato", "Tansy", "Thyme", "Tibouchina", "Tilia", "Tarragon",
                "Tree Fern", "Trillium", "Torenia", "Totara", "Toyon", "Tamarack", "Tabebuia", "Tsuga", "Thujopsis", "Tulip Tree"
            )
            val name = nameList.joinToString(",")
            recyclerviewload(name, binding.homeT, "T")
        }

        binding.homeU.setOnClickListener {
            val nameList = arrayListOf(
                "Umbrella Pine", "Uva Ursi", "Ulex", "Ulluco", "Uncaria", "Usnea", "Urtica", "Ulex europaeus", "Umbellularia", "Urn Plant",
                "Utricularia", "Uvularia", "Uapaca", "Ulmus", "Ulva", "Urena", "Urochloa", "Ulex minor", "Uapaca kirkiana", "Urvillea"
            )
            val name = nameList.joinToString(",")
            recyclerviewload(name, binding.homeU, "U")
        }

        binding.homeV.setOnClickListener {

            val nameList = arrayListOf(
                "Varnishtree", "Verbena", "Viburnum", "Vitex", "Vinca", "Vanilla", "Velvet Bean", "Vaccinium", "Valerian", "Vetch",
                "Venus Flytrap", "Victoria Regia", "Violet", "Vernonia", "Viburnum opulus", "Viola", "Vatica", "Vitis", "Velvetleaf", "Valeriana"
            )
            val name = nameList.joinToString(",")
            recyclerviewload(name, binding.homeV, "V")
        }

        binding.homeW.setOnClickListener {

            val nameList = arrayListOf(
                "Wild Iris", "Wisteria", "Willow", "Walnut", "Water Lily", "Wheat", "Wax Myrtle", "Weigela", "White Cedar", "Wolfberry",
                "Wood Sorrel", "Wolfsbane", "Wahoo", "Western Red Cedar", "Wintergreen", "Woodbine", "Watercress", "Wallflower", "Wych Elm", "White Oak"
            )
            val name = nameList.joinToString(",")
            recyclerviewload(name, binding.homeW, "W")
        }

        binding.homeX.setOnClickListener {

            val nameList = arrayListOf(
                "Xylosma", "Xerophyte", "Xanthium", "Xanthoceras", "Ximenia", "Xeranthemum", "Xanthosoma", "Xylopia", "Xanthorrhoea", "Xerophylla",
                "Xylocarpus", "Xylocopa", "Xyris", "Xanthocarpus", "Xanthium strumarium", "Xenophyllum", "Xanthorrhoea australis", "Xiphidium", "Xylopia aethiopica", "Xerophyta"
            )
            val name = nameList.joinToString(",")
            recyclerviewload(name, binding.homeX, "X")
        }

        binding.homeY.setOnClickListener {
            val nameList = arrayListOf("European ash", "Indian-gooseberry", "Northern white-cedar")
            val name = nameList.joinToString(",")
            recyclerviewload(name, binding.homeY, "Y")
        }
        binding.homeZ.setOnClickListener {

            val nameList = arrayListOf(
                "Zamia",
                "Zelkova",
                "Zinnia",
                "Zoysia",
                "Zantedeschia",
                "Zebra plant",
                "Ziziphus",
                "Zebra cactus",
                "Zingiber",
                "Zizania",
                "Zebra grass",
                "Zanthoxylum",
                "Zygopetalum",
                "Zamioculcas",
                "Zebrawood",
                "Zygocactus",
                "Zygophyllum",
                "Ziziphus jujuba",
                "Zea luxurians",
                "Zelkova tree",
                "Ziziphus spina-christi",
                "Zinnia elegans",
                "Zoysia japonica",
                "Zantedeschia aethiopica",
                "Zinnia angustifolia",
                "Zinnia haageana",
                "Zinnia peruviana",
                "Zygogynum",
                "Zinnia multiflora",
                "Zinnia violacea",
                "Zygophyllum album",
                "Zygophyllum simplex",
                "Zelkova abelicea",
                "Zamia skinneri",
                "Ziziphus lotus",
                "Zanthoxylum armatum",
                "Zamia wallisii",
                "Zygopetalum mackayi",
                "Zamia ulei",
                "Zelkova schneideriana",
                "Ziziphus obtusifolia",
                "Zygophyllum fabago",
                "Zamia disodon",
                "Zinnia acerosa",
                "Zamia portoricensis",
                "Zanthoxylum piperitum",
                "Ziziphus celata",
                "Zygophyllum xanthoxylum",
                "Zelkova hondoensis",
                "Ziziphus microphylla",
                "Zinnia grandiflora",
                "Zamia elegantissima",
                "Ziziphus pubescens",
                "Ziziphus oxyphylla",
                "Zamia cremnophila",
                "Zygogynum auriculatum",
                "Ziziphus zizyphus",
                "Zamia cunaria",
                "Zygophyllum fabago vulgaris",
                "Ziziphus mistol",
                "Zea perennis",
                "Zamia encephalartoides",
                "Ziziphus jujuba spinosa"
            )


            // convert list to single string
            val nameString = nameList.joinToString(",")


            recyclerviewload(nameString, binding.homeZ, "Z")


        }







        return binding.root
//        return view
    }

    fun recyclerviewload(item: String, view: TextView, text: String) {


        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()


        view.postDelayed(Runnable {
            view.setBackgroundColor(view.context.getColor(R.color.white))
            view.setBackgroundDrawable(requireContext().getDrawable(R.drawable.bg_search))


        }, 300)

        view.setBackgroundColor(view.context.getColor(R.color.green3))

        var url: String =
            "https://trefle.io/api/v1/plants?token=od-gIUe-eHFoxtSQsJWcdtmtFsRckGQjJJHGP7YslmU&filter[common_name]=$item"

        AndroidNetworking.get(url).setPriority(com.androidnetworking.common.Priority.HIGH).build()
            .getAsJSONObject(object : JSONObjectRequestListener {


                override fun onResponse(p0: JSONObject?) {

                    try {

                        var dataArray = p0!!.getJSONArray("data")


                        val datalilst = ArrayList<Common_Tree_Detail_Model>()
                        val adapter = Common_Tree_Detail_Adapter(requireContext(), datalilst)
                        binding.homeRecyclerView.adapter = adapter

                        // clear old data
                        datalilst.clear()

                        for (i in 0 until dataArray.length()) {
                            val obj = dataArray.getJSONObject(i)
                            val cn: String = obj.getString("common_name")
                            val imurl: String = obj.getString("image_url")
                            val slug: String = obj.getString("slug")
                            val plant = Common_Tree_Detail_Model(imurl, cn)

                            datalilst.add(plant)
                        }

                        adapter.notifyDataSetChanged()


                    } catch (e: Exception) {

                        Toast.makeText(requireContext(), e.message.toString(), Toast.LENGTH_SHORT)
                            .show()
                    }


                }

                override fun onError(p0: ANError?) {

                    Toast.makeText(requireContext(), p0?.message.toString(), Toast.LENGTH_SHORT)
                        .show()


                }
            })


    }

    fun helo(item: TextView, slug: String) {

        item.postDelayed(Runnable {
            item.setBackgroundColor(item.context.getColor(R.color.white))
            item.setBackgroundDrawable(requireContext().getDrawable(R.drawable.bg_search))


        }, 300)

        item.setBackgroundColor(item.context.getColor(R.color.green3))

        Toast.makeText(requireContext(), "helo", Toast.LENGTH_SHORT).show()


        val iNext = Intent(requireContext(), Common_Tree_Detail::class.java)
        iNext.putExtra("slug", "$slug")
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
        fun newInstance(param1: String, param2: String) = Home().apply {
            arguments = Bundle().apply {
                putString(ARG_PARAM1, param1)
                putString(ARG_PARAM2, param2)
            }
        }
    }
}