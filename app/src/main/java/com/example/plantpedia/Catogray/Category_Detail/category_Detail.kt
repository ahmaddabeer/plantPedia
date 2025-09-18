package com.example.plantpedia.Catogray.Category_Detail

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.animation.AnimationUtils
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.bumptech.glide.Glide
import com.example.plantpedia.R
import com.example.plantpedia.databinding.ActivityCategoryDetailBinding
import org.json.JSONObject

class category_Detail : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryDetailBinding


    private var option = arrayOf(
        "Flower", "Fruit", "Bark", "Habit", "Leaf","Other"
    )


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val animationmove = AnimationUtils.loadAnimation(this,R.anim.move)



        binding.clcktohide.startAnimation(animationmove)


        binding.recyclerViewImageHandle.visibility = View.GONE

        var flag:Int = 0

        binding.showimage.setOnClickListener {

            if (flag == 1) {



                binding.clcktohide.visibility = View.GONE
                binding.recyclerViewImageHandle.visibility = View.VISIBLE
                binding.showimage.setText("Hide Image")

                binding.recyclerviewHide.visibility = View.VISIBLE
                flag = 0

            } else {


                binding.clcktohide.visibility = View.VISIBLE
                binding.recyclerViewImageHandle.visibility = View.GONE
                binding.showimage.setText("Show Image")


                binding.recyclerviewHide.visibility = View.GONE
                flag = 1


            }

        }







        binding.categorDetailRecyclerView.layoutManager = GridLayoutManager(this, 1)


        var helo: Intent = intent

        var name: String? = helo.getStringExtra("name")
        var imgurl: String? = helo.getStringExtra("url")
        var slug: String? = helo.getStringExtra("slug")



        Glide.with(this).load("$imgurl").into(binding.categorDetailimage)

        binding.back.setOnClickListener {

            onBackPressed()
        }


        // Take the instance of Spinner and
        // apply OnItemSelectedListener on it which
        // tells which item of spinner is clicked

        val spinner = findViewById<Spinner>(R.id.recyclerViewImageHandle)


        var url: String =
            "https://trefle.io/api/v1/species/$slug?token=od-gIUe-eHFoxtSQsJWcdtmtFsRckGQjJJHGP7YslmU&filter%5Bcommon_name%5D"

        AndroidNetworking.get(url).setPriority(com.androidnetworking.common.Priority.HIGH).build()
            .getAsJSONObject(object : JSONObjectRequestListener {

                override fun onResponse(p0: JSONObject?) {

                    try {


                        var data = p0!!.getJSONObject("data")

                        var image = data.getJSONObject("images") // images ke andar agai

                        if (spinner != null) {
                            val adapter = ArrayAdapter(
                                this@category_Detail, android.R.layout.simple_spinner_item, option
                            )

                            spinner.adapter = adapter

                            spinner.onItemSelectedListener =
                                object : AdapterView.OnItemSelectedListener {
                                    @RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
                                    override fun onItemSelected(
                                        parent: AdapterView<*>, view: View, position: Int, id: Long
                                    ) {

                                        Toast.makeText(
                                            applicationContext, option[position], Toast.LENGTH_SHORT
                                        ).show()







                                        if (option[position].equals("Flower")) {


                                            try {

                                                var nameList = ArrayList<CDdetai_Model>()
                                                binding.categoryDetailProgressbar.visibility =
                                                    View.VISIBLE
                                                binding.categorDetailRecyclerView.visibility =
                                                    View.GONE

                                                // clear old data
                                                nameList.clear()
                                                adapter.notifyDataSetChanged()

                                                val flower = image.getJSONArray("flower")

                                                for (i in 0 until flower.length()) {

                                                    var imgindex = flower.getJSONObject(i)

                                                    var iurl = imgindex.getString("image_url")
                                                    var cp = imgindex.getString("copyright")

                                                    Log.d("image", iurl)
                                                    Log.d("Image", cp)

                                                    val plant = CDdetai_Model(iurl, cp)

                                                    nameList.add(plant)


                                                }
                                                val adapter = categoryDetail_Adapter(
                                                    this@category_Detail,
                                                    nameList
                                                )
                                                binding.categorDetailRecyclerView.adapter = adapter
                                                adapter.notifyDataSetChanged()

                                                binding.categoryDetailProgressbar.visibility =
                                                    View.GONE
                                                binding.categorDetailRecyclerView.visibility =
                                                    View.VISIBLE


                                            } catch (e: Exception) {

                                                e.printStackTrace()
                                                binding.categoryDetailProgressbar.visibility =
                                                    View.GONE
                                                binding.categorDetailRecyclerView.visibility =
                                                    View.GONE
                                                Toast.makeText(
                                                    applicationContext,
                                                    e.message.toString(),
                                                    Toast.LENGTH_SHORT
                                                ).show()


                                            }


                                        } else if (option[position].equals("Fruit")) {


                                            try {

                                                var nameList = ArrayList<CDdetai_Model>()
                                                binding.categoryDetailProgressbar.visibility =
                                                    View.VISIBLE
                                                binding.categorDetailRecyclerView.visibility =
                                                    View.GONE

                                                // clear old data
                                                nameList.clear()
                                                adapter.notifyDataSetChanged()

                                                val fruit = image.getJSONArray("fruit")

                                                for (i in 0 until fruit.length()) {

                                                    var imgindex = fruit.getJSONObject(i)

                                                    var iurl = imgindex.getString("image_url")
                                                    var cp = imgindex.getString("copyright")

                                                    Log.d("image", iurl)
                                                    Log.d("Image", cp)

                                                    val plant = CDdetai_Model(iurl, cp)

                                                    nameList.add(plant)


                                                }
                                                val adapter = categoryDetail_Adapter(
                                                    this@category_Detail,
                                                    nameList
                                                )
                                                binding.categorDetailRecyclerView.adapter = adapter

                                                adapter.notifyDataSetChanged()

                                                binding.categoryDetailProgressbar.visibility =
                                                    View.GONE
                                                binding.categorDetailRecyclerView.visibility =
                                                    View.VISIBLE


                                            } catch (e: Exception) {

                                                e.printStackTrace()
                                                binding.categoryDetailProgressbar.visibility =
                                                    View.GONE
                                                binding.categorDetailRecyclerView.visibility =
                                                    View.GONE
                                                Toast.makeText(
                                                    applicationContext,
                                                    e.message.toString(),
                                                    Toast.LENGTH_SHORT
                                                ).show()


                                            }


                                        } else if (option[position].equals("Bark")) {


                                            try {


                                                var nameList = ArrayList<CDdetai_Model>()
                                                binding.categoryDetailProgressbar.visibility =
                                                    View.VISIBLE
                                                binding.categorDetailRecyclerView.visibility =
                                                    View.GONE

                                                // clear old data
                                                nameList.clear()
                                                adapter.notifyDataSetChanged()

                                                val bark = image.getJSONArray("bark")

                                                for (i in 0 until bark.length()) {

                                                    var imgindex = bark.getJSONObject(i)

                                                    var iurl = imgindex.getString("image_url")
                                                    var cp = imgindex.getString("copyright")

                                                    Log.d("image", iurl)
                                                    Log.d("Image", cp)

                                                    val plant = CDdetai_Model(iurl, cp)

                                                    nameList.add(plant)


                                                }
                                                val adapter = categoryDetail_Adapter(
                                                    this@category_Detail,
                                                    nameList
                                                )
                                                binding.categorDetailRecyclerView.adapter = adapter
                                                adapter.notifyDataSetChanged()

                                                binding.categoryDetailProgressbar.visibility =
                                                    View.GONE
                                                binding.categorDetailRecyclerView.visibility =
                                                    View.VISIBLE


                                            } catch (e: Exception) {

                                                e.printStackTrace()
                                                binding.categoryDetailProgressbar.visibility =
                                                    View.GONE
                                                binding.categorDetailRecyclerView.visibility =
                                                    View.GONE
                                                Toast.makeText(
                                                    applicationContext,
                                                    e.message.toString(),
                                                    Toast.LENGTH_SHORT
                                                ).show()


                                            }


                                        } else if (option[position].equals("Habit")) {

                                            try {
                                                var nameList = ArrayList<CDdetai_Model>()


                                                binding.categoryDetailProgressbar.visibility =
                                                    View.VISIBLE
                                                binding.categorDetailRecyclerView.visibility =
                                                    View.GONE

                                                // clear old data
                                                nameList.clear()
                                                adapter.notifyDataSetChanged()

                                                val habit = image.getJSONArray("habit")

                                                for (i in 0 until habit.length()) {

                                                    var imgindex = habit.getJSONObject(i)

                                                    var iurl = imgindex.getString("image_url")
                                                    var cp = imgindex.getString("copyright")

                                                    Log.d("image", iurl)
                                                    Log.d("Image", cp)

                                                    val plant = CDdetai_Model(iurl, cp)

                                                    nameList.add(plant)


                                                }
                                                val adapter = categoryDetail_Adapter(
                                                    this@category_Detail,
                                                    nameList
                                                )
                                                binding.categorDetailRecyclerView.adapter = adapter
                                                adapter.notifyDataSetChanged()

                                                binding.categoryDetailProgressbar.visibility =
                                                    View.GONE
                                                binding.categorDetailRecyclerView.visibility =
                                                    View.VISIBLE


                                            } catch (e: Exception) {

                                                e.printStackTrace()
                                                binding.categoryDetailProgressbar.visibility =
                                                    View.GONE
                                                binding.categorDetailRecyclerView.visibility =
                                                    View.GONE
                                                Toast.makeText(
                                                    applicationContext,
                                                    e.message.toString(),
                                                    Toast.LENGTH_SHORT
                                                ).show()


                                            }


                                        } else if (option[position].equals("Leaf")) {

                                            try {
                                                var nameList = ArrayList<CDdetai_Model>()
                                                binding.categoryDetailProgressbar.visibility =
                                                    View.VISIBLE
                                                binding.categorDetailRecyclerView.visibility =
                                                    View.GONE

                                                // clear old data
                                                nameList.clear()
                                                adapter.notifyDataSetChanged()


                                                val leaf = image.getJSONArray("leaf")

                                                for (i in 0 until leaf.length()) {

                                                    var imgindex = leaf.getJSONObject(i)

                                                    var iurl = imgindex.getString("image_url")
                                                    var cp = imgindex.getString("copyright")

                                                    Log.d("image", iurl)
                                                    Log.d("Image", cp)

                                                    val plant = CDdetai_Model(iurl, cp)

                                                    nameList.add(plant)


                                                }
                                                val adapter = categoryDetail_Adapter(
                                                    this@category_Detail,
                                                    nameList
                                                )
                                                binding.categorDetailRecyclerView.adapter = adapter
                                                adapter.notifyDataSetChanged()

                                                binding.categoryDetailProgressbar.visibility =
                                                    View.GONE
                                                binding.categorDetailRecyclerView.visibility =
                                                    View.VISIBLE


                                            } catch (e: Exception) {

                                                e.printStackTrace()
                                                binding.categoryDetailProgressbar.visibility =
                                                    View.GONE
                                                binding.categorDetailRecyclerView.visibility =
                                                    View.GONE
                                                Toast.makeText(
                                                    applicationContext,
                                                    e.message.toString(),
                                                    Toast.LENGTH_SHORT
                                                ).show()


                                            }


                                        }else if (option[position].equals("Other")) {

                                            try {
                                                var nameList = ArrayList<CDdetai_Model>()
                                                binding.categoryDetailProgressbar.visibility =
                                                    View.VISIBLE
                                                binding.categorDetailRecyclerView.visibility =
                                                    View.GONE

                                                // clear old data
                                                nameList.clear()
                                                adapter.notifyDataSetChanged()


                                                val leaf = image.getJSONArray("")

                                                for (i in 0 until leaf.length()) {

                                                    var imgindex = leaf.getJSONObject(i)

                                                    var iurl = imgindex.getString("image_url")
                                                    var cp = imgindex.getString("copyright")

                                                    Log.d("image", iurl)
                                                    Log.d("Image", cp)

                                                    val plant = CDdetai_Model(iurl, cp)

                                                    nameList.add(plant)


                                                }
                                                val adapter = categoryDetail_Adapter(
                                                    this@category_Detail,
                                                    nameList
                                                )
                                                binding.categorDetailRecyclerView.adapter = adapter
                                                adapter.notifyDataSetChanged()

                                                binding.categoryDetailProgressbar.visibility =
                                                    View.GONE
                                                binding.categorDetailRecyclerView.visibility =
                                                    View.VISIBLE


                                            } catch (e: Exception) {

                                                e.printStackTrace()
                                                binding.categoryDetailProgressbar.visibility =
                                                    View.GONE
                                                binding.categorDetailRecyclerView.visibility =
                                                    View.GONE
                                                Toast.makeText(
                                                    applicationContext,
                                                    e.message.toString(),
                                                    Toast.LENGTH_SHORT
                                                ).show()


                                            }


                                        }
                                    }


                                    override fun onNothingSelected(parent: AdapterView<*>) {
                                        // write code to perform some action


                                    }

                                }

                        }


                        var cname = data.getString("common_name")
                        var year: String = data.getString("year")
                        var sintificname: String = data.getString("scientific_name")
                        var bibigraphy: String = data.getString("bibliography")
                        var auther: String = data.getString("author")
                        var status: String = data.getString("status")
                        var rank: String = data.getString("rank")
                        var familycommonName: String = data.getString("family_common_name")
                        var family: String = data.getString("family")
                        var vegetable: String = data.getString("vegetable")
                        var observations: String = data.getString("observations")

                        binding.sintificNameTitle.setText(sintificname)
                        binding.commonName.setText(cname)
                        binding.year.setText(year)
                        binding.sintificName.setText(sintificname)
                        binding.bibilography.setText(bibigraphy)
                        binding.auther.setText(auther)
                        binding.status.setText(status)
                        binding.rank.setText(rank)
                        binding.familyCommonName.setText(familycommonName)
                        binding.family.setText(family)
                        binding.vegetable.setText(vegetable)
                        binding.observation.setText(observations)


                    } catch (e: Exception) {
                        e.printStackTrace()
                        Log.d("helo", e.message.toString())

                    }


                }

                override fun onError(p0: ANError?) {
                    TODO("Not yet implemented")
                }
            })


    }

}
//
//    override fun onItemSelected(
//        item: AdapterView<*>?, view: View,
//        position: Int, id: Long
//    ) {
//
////         which is selected in spinner
//
//        Toast.makeText(applicationContext, option[position], Toast.LENGTH_SHORT).show()
//        if(option[position].equals("Flower")){
//
//
//
//
//
//
//
//
//
//
//        }
//        Log.d("helo", option[position])
//
//
//    }
//
//
//   override fun onNothingSelected(parent: AdapterView<*>?) {}
//
//
//
//}

