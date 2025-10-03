package com.example.plantpedia.profile

import AppDatabase
import ProfileEntity
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.plantpedia.R
import com.example.plantpedia.databinding.FragmentProfileBinding
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.coroutines.launch

class Profile : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var dialogImage: CircleImageView
    private var selectedImageUri: Uri? = null   // 📌 Save selected image URI

    // 📌 ActivityResultLauncher for Gallery + Camera
    private val imageChooserLauncher =
        registerForActivityResult(androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK && result.data != null) {
                val data = result.data
                val selectedUri: Uri? = data?.data

                if (selectedUri != null) {
                    // Gallery se aaya
                    dialogImage.setImageURI(selectedUri)
                    selectedImageUri = selectedUri
                } else {
                    // Camera se aaya (Bitmap milta hai)
                    val extras = data?.extras
                    val imageBitmap = extras?.get("data") as? Bitmap
                    if (imageBitmap != null) {
                        dialogImage.setImageBitmap(imageBitmap)

                        // bitmap ko temporary save karke URI banani hogi (abhi simple null rakha hai)
                        selectedImageUri = null
                    }
                }
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)



        binding.btnEditProfile.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            val customLayout: View = layoutInflater.inflate(R.layout.edit_profile_dilog, null)
            builder.setView(customLayout)
            val dialog = builder.create()
            dialog.show()

            val imgbuttonBack: ImageButton = customLayout.findViewById(R.id.profiledilogBackButton)
            imgbuttonBack.setOnClickListener { dialog.dismiss() }

            val imgcorrectButton: ImageButton = customLayout.findViewById(R.id.profiledilogCorrect)
            dialogImage = customLayout.findViewById(R.id.profileDilog_image)

            // 📌 Open chooser on click
            dialogImage.setOnClickListener {
                Toast.makeText(context, "Loading Media", Toast.LENGTH_SHORT).show()

                // Gallery intent
                val galleryIntent =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

                // Camera intent
                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

                // Chooser with both
                val chooser = Intent.createChooser(galleryIntent, "Select Image")
                chooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(cameraIntent))

                imageChooserLauncher.launch(chooser)
            }

            imgcorrectButton.setOnClickListener {
                val firstName: EditText = customLayout.findViewById(R.id.edtFirstName)
                val lastName: EditText = customLayout.findViewById(R.id.edtLastName)
                val gender: EditText = customLayout.findViewById(R.id.edtGender)
                val phoneNumber: EditText = customLayout.findViewById(R.id.edtPhone)

                // 📌 Room Database instance
                val db = Room.databaseBuilder(
                    requireContext(),
                    AppDatabase::class.java,
                    "app_db"
                ).build()

                // 📌 Save Profile Data
                lifecycleScope.launch {
                    val profile = ProfileEntity(
                        firstName = firstName.text.toString(),
                        lastName = lastName.text.toString(),
                        gender = gender.text.toString(),
                        phone = phoneNumber.text.toString(),
                        imageUri = selectedImageUri?.toString()
                    )
                    db.profileDao().insertOrUpdate(profile)

                    requireActivity().runOnUiThread {
                        Toast.makeText(requireContext(), "Profile Updated!", Toast.LENGTH_SHORT).show()
                        dialog.dismiss()
                    }
                }
            }
        }

        return binding.root
    }
}
