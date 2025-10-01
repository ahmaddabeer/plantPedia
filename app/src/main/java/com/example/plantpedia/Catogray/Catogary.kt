package com.example.plantpedia.Catogray

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.example.plantpedia.Room.PlantDetaBase
import com.example.plantpedia.databinding.FragmentCatogaryBinding
import com.example.plantpedia.wishList.PlantAdapter
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

    private var helo = arrayOf(


        "Search By Page Number", "Search By Common Name", "Search By Sintific Name"
    )

    private var commonNameSuggestion = arrayOf(

        "Silver Oak",
        "Indian Beech",
        "False Ashoka",
        "Alexandrian Laurel",
        "Indian Coral Tree",
        "Devil Tree",
        "Indian Mast Tree",
        "Chinese Fan Palm",
        "Areca Palm",
        "Sago Palm",
        "Traveler’s Palm",
        "Washington Palm",
        "Fan Palm",
        "Oil Palm",
        "Rattan Palm",
        "Betel Nut Palm",
        "Foxtail Palm",
        "Queen Palm",
        "King Palm",
        "Talipot Palm",
        "Screw Pine",
        "Indian Persimmon",
        "Indian Hawthorn",
        "Indian Cork Tree",
        "Indian Soapberry",
        "Indian Gooseberry",
        "Indian Blackberry",
        "Indian Bael",
        "Black Wattle",
        "Silver Wattle",
        "Golden Wattle",
        "River Red Gum",
        "Blue Gum",
        "Snow Gum",
        "Mountain Ash",
        "Stringybark",
        "Bloodwood",
        "Yellow Box",
        "Grey Box",
        "Ironbark",
        "Red Maple",
        "Silver Maple",
        "Sugar Maple",
        "Bigleaf Maple",
        "Norway Maple",
        "Japanese Maple",
        "Paper Birch",
        "Silver Birch",
        "Yellow Birch",
        "River Birch",
        "Sweet Birch",
        "White Oak",
        "Red Oak",
        "Black Oak",
        "Chestnut Oak",
        "Live Oak",
        "Bur Oak",
        "Pin Oak",
        "Scarlet Oak",
        "Shingle Oak",
        "Water Oak",
        "Post Oak",
        "Swamp White Oak",
        "Cherry Blossom",
        "Wild Cherry",
        "Black Cherry",
        "Sweet Cherry",
        "Sour Cherry",
        "Prunus",
        "Apple Tree",
        "Crabapple",
        "Wild Apple",
        "Pear Tree",
        "Wild Pear",
        "Quince Tree",
        "Peach Tree",
        "Nectarine Tree",
        "Plum Tree",
        "Apricot Tree",
        "Almond Blossom Tree",
        "Chestnut Tree",
        "Horse Chestnut",
        "Sweet Chestnut",
        "Beech Tree",
        "European Beech",
        "American Beech",
        "Sycamore Fig",
        "London Plane",
        "American Sycamore",
        "Buttonwood",
        "Aspen",
        "Quaking Aspen",
        "Bigtooth Aspen",
        "Cottonwood",
        "Balsam Poplar",
        "Black Poplar",
        "Lombardy Poplar",
        "White Poplar",
        "Golden Poplar",
        "Redwood",
        "Coast Redwood",
        "Giant Sequoia",
        "Dawn Redwood",
        "Cypress",
        "Bald Cypress",
        "Monterey Cypress",
        "Lawson Cypress",
        "Hinoki Cypress",
        "Leyland Cypress",
        "Juniper",
        "Chinese Juniper",
        "Common Juniper",
        "Red Cedar",
        "White Cedar",
        "Northern White Cedar",
        "Western Red Cedar",
        "Thuja",
        "Larch",
        "Tamarack",
        "Douglas Fir",
        "Grand Fir",
        "Noble Fir",
        "Subalpine Fir",
        "Fraser Fir",
        "Balsam Fir",
        "Kauri Tree",
        "Norfolk Island Pine",
        "Indian Jujube",
        "Indian Mahua",
        "Indian Beechwood",
        "Indian Coral Jasmine",
        "Indian Rain Tree",
        "Indian Flame Tree",
        "Indian Kapok",
        "Indian Tulip Tree",
        "Indian Kino Tree",
        "Indian Almond Tree",
        "Indian Bay Tree",
        "Indian Camphor Tree",
        "Indian Cherry",
        "Indian Medlar",
        "Indian May Apple",
        "Indian Wild Apple",
        "Indian Pear",
        "Indian Plum",
        "Indian Crab Apple",
        "Indian Apricot",
        "Indian Strawberry Tree",
        "Indian Blackwood",
        "Indian Rose Chestnut",
        "Indian Ebony",
        "Indian Locust Tree",
        "Indian Indigo Tree",
        "Indian Tamarisk",
        "Indian Yew",
        "Indian Cypress",
        "Indian Juniper",
        "Indian Hemlock",
        "Indian Larch",
        "Indian Alder",
        "Indian Hornbeam",
        "Indian Ash",
        "Indian Hazel",
        "Indian Buckeye",
        "Indian Magnolia",
        "Indian Tulipwood",
        "Indian Trumpet Tree",
        "Indian Flame of the Forest",
        "Indian Silk Tree",
        "Indian Soap Tree",
        "Indian Pencil Cedar",
        "Indian Hackberry",
        "Indian Elder",
        "Indian Dogwood",
        "Indian Snowbell",
        "Indian Silverberry",
        "Indian Gmelina",
        "Indian Viburnum",
        "Indian Privet",
        "Indian Oleander",
        "Indian Bauhinia",
        "Indian Butterfly Tree",
        "Indian Cassia",
        "Indian White Cedar",
        "Indian Boxwood",
        "Indian Hawthorn",
        "Indian Laurel Fig",
        "Indian Banyan Fig",
        "Indian Cluster Fig",
        "Indian Peepal Fig",
        "Indian Rubber Fig",
        "Indian Strangler Fig",
        "Indian Milkwood",
        "Indian Breadnut Tree",

        "Neem",
        "Peepal",
        "Banyan",
        "Ashoka",
        "Teak",
        "Sal",
        "Mahogany",
        "Rosewood",
        "Mango",
        "Jamun",
        "Guava",
        "Jackfruit",
        "Coconut",
        "Palm",
        "Date Palm",
        "Cashew",
        "Almond",
        "Walnut",
        "Pine",
        "Cedar",
        "Fir",
        "Spruce",
        "Deodar",
        "Birch",
        "Maple",
        "Oak",
        "Elm",
        "Sycamore",
        "Willow",
        "Poplar",
        "Eucalyptus",
        "Acacia",
        "Babool",
        "Shisham",
        "Arjun",
        "Khejri",
        "Chinar",
        "Tulip Tree",
        "Bael",
        "Kadamba",
        "Amaltas",
        "Gulmohar",
        "Flame Tree",
        "Rain Tree",
        "Silk Cotton",
        "Kapok",
        "Cotton Tree",
        "Drumstick",
        "Moringa",
        "Bamboo",
        "Sandalwood",
        "Red Sandalwood",
        "Indian Laburnum",
        "Golden Shower",
        "Pongamia",
        "Neem Chameli",
        "Harsingar",
        "Coral Tree",
        "Bottlebrush",
        "Jacaranda",
        "Frangipani",
        "Plumeria",
        "Hibiscus Tree",
        "Breadfruit",
        "Tamarind",
        "Indian Almond",
        "Custard Apple",
        "Sugar Apple",
        "Pomegranate",
        "Papaya",
        "Mulberry",
        "Fig",
        "Sacred Fig",
        "Indian Laurel",
        "Ficus",
        "Rubber Tree",
        "Clove Tree",
        "Nutmeg Tree",
        "Cinnamon Tree",
        "Starfruit Tree",
        "Lychee",
        "Longan",
        "Dragon Fruit",
        "Coffee Tree",
        "Tea Plant",
        "Olive",
        "Grapefruit",
        "Orange",
        "Lemon",
        "Lime",
        "Mandarin",
        "Pomelo",
        "Banana",
        "Plantain",
        "Kaffir Lime",
        "Bay Leaf Tree"
    )

    private var scientificNameSuggestion = arrayOf(
        "Grevillea robusta",          // Silver Oak
        "Pongamia pinnata",            // Indian Beech
        "Polyalthia longifolia",       // False Ashoka
        "Calophyllum inophyllum",      // Alexandrian Laurel
        "Erythrina variegata",         // Indian Coral Tree
        "Alstonia scholaris",          // Devil Tree
        "Monoon longifolium",          // Indian Mast Tree
        "Livistona chinensis",         // Chinese Fan Palm
        "Dypsis lutescens",            // Areca Palm
        "Cycas revoluta",              // Sago Palm
        "Ravenala madagascariensis",   // Traveler’s Palm
        "Washingtonia robusta",        // Washington Palm
        "Borassus flabellifer",        // Fan Palm
        "Elaeis guineensis",           // Oil Palm
        "Calamus rotang",              // Rattan Palm
        "Areca catechu",               // Betel Nut Palm
        "Wodyetia bifurcata",          // Foxtail Palm
        "Syagrus romanzoffiana",       // Queen Palm
        "Archontophoenix alexandrae",  // King Palm
        "Corypha umbraculifera",       // Talipot Palm
        "Pandanus odorifer",           // Screw Pine
        "Diospyros lotus",             // Indian Persimmon
        "Rhaphiolepis indica",         // Indian Hawthorn
        "Millingtonia hortensis",      // Indian Cork Tree
        "Sapindus mukorossi",          // Indian Soapberry
        "Phyllanthus emblica",         // Indian Gooseberry (Amla)
        "Syzygium cumini",             // Indian Blackberry (Jamun)
        "Aegle marmelos",              // Indian Bael
        "Acacia mearnsii",             // Black Wattle
        "Acacia dealbata",             // Silver Wattle
        "Acacia pycnantha",            // Golden Wattle
        "Eucalyptus camaldulensis",    // River Red Gum
        "Eucalyptus globulus",         // Blue Gum
        "Eucalyptus pauciflora",       // Snow Gum
        "Eucalyptus regnans",          // Mountain Ash
        "Eucalyptus macrorhyncha",     // Stringybark
        "Corymbia gummifera",          // Bloodwood
        "Eucalyptus melliodora",       // Yellow Box
        "Eucalyptus microcarpa",       // Grey Box
        "Eucalyptus sideroxylon",      // Ironbark
        "Acer rubrum",                 // Red Maple
        "Acer saccharinum",            // Silver Maple
        "Acer saccharum",              // Sugar Maple
        "Acer macrophyllum",           // Bigleaf Maple
        "Acer platanoides",            // Norway Maple
        "Acer palmatum",               // Japanese Maple
        "Betula papyrifera",           // Paper Birch
        "Betula pendula",              // Silver Birch
        "Betula alleghaniensis",       // Yellow Birch
        "Betula nigra",                // River Birch
        "Betula lenta",                // Sweet Birch
        "Quercus alba",                // White Oak
        "Quercus rubra",               // Red Oak
        "Quercus velutina",            // Black Oak
        "Quercus montana",             // Chestnut Oak
        "Quercus virginiana",          // Live Oak
        "Quercus macrocarpa",          // Bur Oak
        "Quercus palustris",           // Pin Oak
        "Quercus coccinea",            // Scarlet Oak
        "Quercus imbricaria",          // Shingle Oak
        "Quercus nigra",               // Water Oak
        "Quercus stellata",            // Post Oak
        "Quercus bicolor",             // Swamp White Oak
        "Prunus serrulata",            // Cherry Blossom
        "Prunus avium",                // Sweet Cherry
        "Prunus cerasus",              // Sour Cherry
        "Prunus domestica",            // Plum
        "Prunus armeniaca",            // Apricot
        "Prunus dulcis",               // Almond
        "Mangifera indica",            // Mango
        "Cocos nucifera",              // Coconut
        "Ficus benghalensis",          // Banyan
        "Ficus religiosa",             // Peepal
        "Azadirachta indica",          // Neem
        "Tectona grandis",             // Teak
        "Shorea robusta",              // Sal
        "Swietenia macrophylla",       // Mahogany
        "Dalbergia latifolia",         // Rosewood
        "Psidium guajava",             // Guava
        "Artocarpus heterophyllus",    // Jackfruit
        "Casuarina equisetifolia",     // Casuarina
        "Delonix regia",               // Gulmohar
        "Cassia fistula",              // Amaltas / Golden Shower
        "Butea monosperma",            // Flame of the Forest
        "Albizia saman",               // Rain Tree
        "Bombax ceiba",                // Silk Cotton
        "Moringa oleifera",            // Drumstick Tree
        "Santalum album",              // Sandalwood
        "Pterocarpus santalinus",      // Red Sandalwood
        "Punica granatum",             // Pomegranate
        "Carica papaya",               // Papaya
        "Musa paradisiaca",            // Banana / Plantain
        "Citrus limon",                // Lemon
        "Citrus sinensis",             // Orange
        "Citrus aurantiifolia",        // Lime
        "Citrus maxima",               // Pomelo
        "Coffea arabica",              // Coffee
        "Camellia sinensis",           // Tea
        "Olea europaea",               // Olive
        "Litchi chinensis",            // Lychee
        "Annona squamosa",             // Custard Apple
        "Annona reticulata",           // Bullock’s Heart
        "Ficus carica",                // Common Fig
        "Ficus elastica",              // Rubber Tree
        "Cinnamomum verum",            // True Cinnamon
        "Myristica fragrans",          // Nutmeg
        "Syzygium aromaticum"          // Clove
    )


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












        binding.catgoryRecyclerview.layoutManager = GridLayoutManager(requireContext(), 3)

        val SpinAdapter: ArrayAdapter<*>




        SpinAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, helo)

        binding.searchTypeChange.adapter = SpinAdapter
        val loader: ProgressBar = binding.loader


        val nameList = ArrayList<categoryModel>()
        val adapter = categoryAdapter(requireContext(), nameList)
        binding.catgoryRecyclerview.adapter = adapter


        // ek common function for API call
        fun loadPlants(url: String) {
            binding.loader.visibility = View.VISIBLE

            AndroidNetworking.get(url).setPriority(com.androidnetworking.common.Priority.HIGH)
                .build().getAsJSONObject(object : JSONObjectRequestListener {
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



        if (binding.searchTypeChange != null) {

            val adapter = ArrayAdapter(
                requireContext(), android.R.layout.simple_spinner_item, helo
            )


            binding.searchTypeChange.adapter = adapter

            binding.searchTypeChange.onItemSelectedListener =

                object : AdapterView.OnItemSelectedListener {


                    override fun onItemSelected(
                        parent: AdapterView<*>?, view: View?, position: Int, id: Long
                    ) {

                        Toast.makeText(requireContext(), helo[position], Toast.LENGTH_SHORT).show()

                        if (helo[position].equals("Search By Page Number")) {

                            binding.cancelButton.setOnClickListener {

                                binding.categoryEditsearch.text.clear()
                            }

                            binding.categoryEditsearch.setHint("Search By Page Number")
                            binding.categoryEditsearch.inputType = InputType.TYPE_CLASS_NUMBER
                            nameList.clear()


                            // Default page=1 load on start
                            var defaultUrl =
                                "https://trefle.io/api/v1/plants?token=od-gIUe-eHFoxtSQsJWcdtmtFsRckGQjJJHGP7YslmU&page=1"
                            loadPlants(defaultUrl)


                            // listener on EditText
                            binding.categoryEditsearch.addTextChangedListener(object : TextWatcher {
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

                                override fun beforeTextChanged(
                                    s: CharSequence?, start: Int, count: Int, after: Int
                                ) {
                                }

                                override fun onTextChanged(
                                    s: CharSequence?, start: Int, before: Int, count: Int
                                ) {
                                }
                            })


                        }

                        if (helo[position].equals("Search By Common Name")) {

                            binding.cancelButton.setOnClickListener {

                                binding.categoryEditsearch.text.clear()
                            }

                            val suggesionAdapter: ArrayAdapter<*>

                            suggesionAdapter = ArrayAdapter(
                                requireContext(),
                                android.R.layout.simple_spinner_item,
                                commonNameSuggestion
                            )

                            binding.categoryEditsearch.setAdapter(suggesionAdapter)
                            binding.categoryEditsearch.threshold = 3


                            binding.categoryEditsearch.setHint("Search By Common Name")
                            binding.categoryEditsearch.inputType = InputType.TYPE_CLASS_TEXT
                            nameList.clear()


                            // Default page=1 load on start
                            var defaultUrl =
                                "https://trefle.io/api/v1/plants?token=od-gIUe-eHFoxtSQsJWcdtmtFsRckGQjJJHGP7YslmU&filter[common_name]=coconut"
                            loadPlants(defaultUrl)

                            // listener on EditText
                            binding.categoryEditsearch.addTextChangedListener(object : TextWatcher {
                                override fun afterTextChanged(s: Editable?) {
                                    val commonName = s.toString().trim()

                                    var url = defaultUrl

                                    if (commonName.isNotEmpty() && commonName.all { it.isDigit() }) {
                                        url =
                                            "https://trefle.io/api/v1/plants?token=od-gIUe-eHFoxtSQsJWcdtmtFsRckGQjJJHGP7YslmU&filter[common_name]=$commonName"
                                    } else if (commonName.isNotEmpty()) {
                                        url =
                                            "https://trefle.io/api/v1/plants?token=od-gIUe-eHFoxtSQsJWcdtmtFsRckGQjJJHGP7YslmU&filter[common_name]=$commonName"
                                    }

                                    loadPlants(url)
                                }

                                override fun beforeTextChanged(
                                    s: CharSequence?, start: Int, count: Int, after: Int
                                ) {
                                }

                                override fun onTextChanged(
                                    s: CharSequence?, start: Int, before: Int, count: Int
                                ) {
                                }
                            })


                        }

                        if (helo[position].equals("Search By Sintific Name")) {

                            binding.cancelButton.setOnClickListener {

                                binding.categoryEditsearch.text.clear()
                            }

                            val suggesionAdapter: ArrayAdapter<*>


                            suggesionAdapter = ArrayAdapter(
                                requireContext(),
                                android.R.layout.simple_spinner_item,
                                scientificNameSuggestion
                            )

                            binding.categoryEditsearch.setAdapter(suggesionAdapter)
                            binding.categoryEditsearch.threshold = 3

                            binding.categoryEditsearch.setHint("Search By Sintific Name")
                            binding.categoryEditsearch.inputType = InputType.TYPE_CLASS_TEXT

                            nameList.clear()


                            // Default page=1 load on start
                            var defaultUrl =
                                "https://trefle.io/api/v1/plants?token=od-gIUe-eHFoxtSQsJWcdtmtFsRckGQjJJHGP7YslmU&filter[scientific_name]=Azadirachta indica"
                            loadPlants(defaultUrl)

                            // listener on EditText
                            binding.categoryEditsearch.addTextChangedListener(object : TextWatcher {
                                override fun afterTextChanged(s: Editable?) {
                                    val sintificname = s.toString().trim()

                                    var url = defaultUrl

                                    if (sintificname.isNotEmpty() && sintificname.all { it.isDigit() }) {
                                        url =
                                            "https://trefle.io/api/v1/plants?token=od-gIUe-eHFoxtSQsJWcdtmtFsRckGQjJJHGP7YslmU&filter[scientific_name]=$sintificname"
                                    } else if (sintificname.isNotEmpty()) {
                                        url =
                                            "https://trefle.io/api/v1/plants?token=od-gIUe-eHFoxtSQsJWcdtmtFsRckGQjJJHGP7YslmU&filter[sientific_name]=$sintificname"
                                    }

                                    loadPlants(url)
                                }

                                override fun beforeTextChanged(
                                    s: CharSequence?, start: Int, count: Int, after: Int
                                ) {
                                }

                                override fun onTextChanged(
                                    s: CharSequence?, start: Int, before: Int, count: Int
                                ) {
                                }
                            })

                        }

                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        TODO("Not yet implemented")
                    }
                }
        }









        return binding.root
    }


}