package com.example.plantpedia.wishList

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.plantpedia.R
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import java.io.ByteArrayOutputStream

class WishList : Fragment() {

    private var imageBitmap: Bitmap? = null
    private lateinit var adapter: Adapter
    private var dataModalArrayList: ArrayList<DataModel> = ArrayList()
    private var takeImageLauncher: ActivityResultLauncher<Intent>? = null

    private var recyclerView: RecyclerView? = null
    private var snapBtn: Button? = null
    private var getResultsBtn: Button? = null
    private var imageView: ImageView? = null

    // 👇 अपनी OpenAI API key डालो
    private val OPENAI_API_KEY = "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"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_wish_list, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        snapBtn = view.findViewById(R.id.snap)
        getResultsBtn = view.findViewById(R.id.getSearchResults)
        imageView = view.findViewById(R.id.image)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView?.layoutManager = LinearLayoutManager(requireContext())
        adapter = Adapter(dataModalArrayList, requireContext())
        recyclerView?.adapter = adapter

        snapBtn?.setOnClickListener { dispatchTakePictureIntent() }
        getResultsBtn?.setOnClickListener { identifyPlantWithAI() }

        takeImageLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { o: ActivityResult ->
            if (o.resultCode == android.app.Activity.RESULT_OK) {
                val data = o.data
                val extras = data?.extras
                imageBitmap = extras?.get("data") as? Bitmap
                if (imageBitmap != null) {
                    Glide.with(requireContext()).load(imageBitmap).into(imageView!!)
                } else {
                    Toast.makeText(requireContext(), "Failed to capture image", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(requireActivity().packageManager) != null) {
            takeImageLauncher?.launch(takePictureIntent)
        }
    }

    // ✅ GPT-4V AI call
    private fun identifyPlantWithAI() {
        if (imageBitmap == null) {
            Toast.makeText(requireContext(), "Please capture an image first.", Toast.LENGTH_SHORT).show()
            return
        }

        // Bitmap -> Base64
        val byteArrayOutputStream = ByteArrayOutputStream()
        imageBitmap!!.compress(Bitmap.CompressFormat.JPEG, 90, byteArrayOutputStream)
        val base64Image = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.NO_WRAP)

        val url = "https://api.openai.com/v1/responses"

        // Request body for GPT-4V
        val requestBody = JSONObject()
        requestBody.put("model", "gpt-4.1-mini")  // GPT-4V model
        val inputArray = org.json.JSONArray()
        val inputObject = JSONObject()
        inputObject.put("role", "user")
        inputObject.put("content", org.json.JSONArray().put(JSONObject().put("type", "input_image").put("image_data", base64Image)))
        inputObject.put("text", "Identify this plant. Give scientific name, common names, family, and description.")
        inputArray.put(inputObject)
        requestBody.put("input", inputArray)

        val queue = Volley.newRequestQueue(requireContext())
        val jsonObjectRequest = object : JsonObjectRequest(
            Method.POST, url, requestBody,
            { response ->
                try {
                    dataModalArrayList.clear()

                    val outputText = response.getJSONArray("output").getJSONObject(0).getString("content")
                    dataModalArrayList.add(
                        DataModel(
                            title = "Plant Info",
                            link = "",
                            displayedLink = "AI Generated",
                            snippet = outputText
                        )
                    )

                    adapter.notifyDataSetChanged()
                } catch (e: Exception) {
                    e.printStackTrace()
                    Toast.makeText(requireContext(), "Parsing error from AI", Toast.LENGTH_SHORT).show()
                }
            },
            { error ->
                error.printStackTrace()
                Toast.makeText(requireContext(), "Failed to call AI API", Toast.LENGTH_SHORT).show()
            }) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["Authorization"] = "Bearer $OPENAI_API_KEY"
                headers["Content-Type"] = "application/json"
                return headers
            }
        }

        queue.add(jsonObjectRequest)
    }
}
