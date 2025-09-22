package com.example.plantpedia.Common_Tree_Plant_Detail

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.plantpedia.Catogray.Category_Detail.CDdetai_Model
import com.example.plantpedia.Catogray.Category_Detail.categoryDetail_Adapter
import com.example.plantpedia.R
import com.example.plantpedia.databinding.ActivityCommonTreeDetailBinding
import org.json.JSONObject
import java.net.URL

class Common_Tree_Detail : AppCompatActivity() {



    private var option = arrayOf(
        "Flower", "Fruit", "Bark", "Habit", "Leaf","Other"
    )

    lateinit var image: JSONObject

    lateinit var adapter: ArrayAdapter<*>
    lateinit var data: JSONObject








    lateinit var binding: ActivityCommonTreeDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommonTreeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var imgload:String = ""

        Log.d("kaliya","yes run ")




        binding.back.setOnClickListener {

            onBackPressed()
        }


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







        binding.commonTreeDetailRecyclerView.layoutManager = GridLayoutManager(this, 1)


        var helo: Intent = intent

//        var name: String? = helo.getStringExtra("name")
//        var imgurl: String? = helo.getStringExtra("url")
        var slug: String? = helo.getStringExtra("slug")



//        Glide.with(this).load(imageurl).into(binding.commonTreeDetialImage)


        binding.back.setOnClickListener {

            onBackPressed()
        }


        // Take the instance of Spinner and
        // apply OnItemSelectedListener on it which
        // tells which item of spinner is clicked

        val spinner = findViewById<Spinner>(R.id.recyclerViewImageHandle)


        var url: String =
            "https://trefle.io/api/v1/species/$slug?token=od-gIUe-eHFoxtSQsJWcdtmtFsRckGQjJJHGP7YslmU"

        AndroidNetworking.get(url).setPriority(com.androidnetworking.common.Priority.HIGH).build()
            .getAsJSONObject(object : JSONObjectRequestListener {



                override fun onResponse(p0: JSONObject?) {



                    try {


                         data = p0!!.getJSONObject("data")

                         image = data.getJSONObject("images") // images ke andar agai

                        if (spinner != null) {

                            adapter = ArrayAdapter(
                                this@Common_Tree_Detail, android.R.layout.simple_spinner_item, option
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







                                        if (option[position].equals("Flower"))

                                            helo("flower")

                                        else if(option[position].equals("Fruit"))

                                            helo("fruit")

                                        else if(option[position].equals("Bark"))
                                            helo("bark")
                                        else if(option[position].equals("Habit"))
                                            helo("habit")
                                        else if(option[position].equals("Leaf"))
                                            helo("leaf")
                                        else if(option[position].equals("Other"))
                                            helo("")
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

                        var imageurl:String = data.getString("image_url")
                        Log.d("helo","$imageurl")












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

                        Glide.with(applicationContext)
                            .load(imageurl)
                            .placeholder(R.drawable.baseline_image_search_24)
                            .error(R.drawable.baseline_running_with_errors_24)
                            .diskCacheStrategy(DiskCacheStrategy.ALL) // Cache all versions
                            .timeout(60000) // 60 seconds timeout
                            .into(binding.commonTreeDetialImage)


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

    fun helo(name:String){


        try {

            var nameList = ArrayList<CDdetai_Model>()
            binding.commonTreeDetailProgressbar.visibility =
                View.VISIBLE
            binding.commonTreeDetailRecyclerView.visibility =
                View.GONE

            // clear old data
            nameList.clear()
            adapter.notifyDataSetChanged()

            val name = image.getJSONArray("$name")






            for (i in 0 until name.length()) {

                var imgindex = name.getJSONObject(i)

                var iurl = imgindex.getString("image_url")
                var cp = imgindex.getString("copyright")

                Log.d("image", iurl)
                Log.d("Image", cp)

                val plant = CDdetai_Model(iurl, cp)

                nameList.add(plant)


            }
            val adapter = categoryDetail_Adapter(
                this@Common_Tree_Detail,
                nameList
            )
            binding.commonTreeDetailRecyclerView.adapter = adapter
            adapter.notifyDataSetChanged()

            binding.commonTreeDetailProgressbar.visibility =
                View.GONE
            binding.commonTreeDetailRecyclerView.visibility =
                View.VISIBLE


        } catch (e: Exception) {

            e.printStackTrace()
            binding.commonTreeDetailProgressbar.visibility =
                View.GONE
            binding.commonTreeDetailRecyclerView.visibility =
                View.GONE
            Toast.makeText(
                applicationContext,
                e.message.toString(),
                Toast.LENGTH_SHORT
            ).show()


        }

    }


}