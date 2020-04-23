package com.example.p_navadmin.StoreRetailer

import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log.d
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.example.p_navadmin.R
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import kotlinx.android.synthetic.main.shop_retailer_promotion.*
import java.util.*

class Shop_Retailer_Promotion_Event : AppCompatActivity() {

    companion object {
        private val PERMISSION_PICK_IMAGE = 1000
    }

    val storage = Firebase.storage
    // Create a storage reference from our app
    var storageRef = storage.reference
    // Create a child reference
    // imagesRef now points to "images"
    var imagesRef: StorageReference? = storageRef.child("images")

    internal var filePath: Uri? = null
    var imgName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.shop_retailer_promotion)

        // connect to firestore
        val db = Firebase.firestore



        // get passed store name from login
        val store = intent.getStringExtra("store")
        d("firebase","$store")

        var input_Start = ""
        var input_End = ""

        // Calendar
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        //upload Image / browse button
        buttonBrowse.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            startActivityForResult(Intent.createChooser(intent, "select image"), PERMISSION_PICK_IMAGE)
            d("firebase", "browse")
        }

        // get the start date
        inputStart.setOnClickListener {
            val date = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { datePicker, mYear, mMonth, mDay ->
                inputStart.text = "$mDay/${mMonth + 1}/$mYear"
                input_Start = "$mDay/${mMonth + 1}/$mYear"
            }, year, month, day)

            date.show()
        }

//        get the end date
            inputEnd.setOnClickListener {
                val date = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { datePicker, mYear, mMonth, mDay ->
                    inputEnd.text = "$mDay/${mMonth + 1}/$mYear"
                    input_End = "$mDay/${mMonth + 1}/$mYear"
                }, year, month, day)

                date.show()
            }


        submitEvent.setOnClickListener {
            d("firebase", "just click")

            // get the input
            val inputTitle = inputTitle.text.toString()
            val inputDescription = inputDescription.text.toString()

            // proceed if the text input is not empty
            if (inputTitle.isNotEmpty() && input_Start.isNotEmpty() && input_End.isNotEmpty() && inputDescription.isNotEmpty()
                && imgName.isNotEmpty())
            {
                //upload img
                val uploadTask = imagesRef!!.putFile(filePath!!)
                // upload and get URL
                var url = ""
                val urlTask = uploadTask.continueWithTask { task ->
                    if (!task.isSuccessful) {

                    }
                    imagesRef!!.downloadUrl
                }
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val downloadUri = task.result
                            url = downloadUri.toString()
                            d("faris", "this is url : $url")

                            // set the data
                            val upEvent = hashMapOf(
                                "title" to "$inputTitle",
                                "start" to "$input_Start",
                                "end" to "$input_End",
                                "description" to "$inputDescription",
                                "image name" to "$imgName",
                                "url" to "$url"
                            )

                            // send to db
                            db.collection("Confirmation Event").document("$store")
                                .set(upEvent)
                                .addOnSuccessListener { result ->
                                    d("firebase", "Successfully add new data")
//                                    startActivity(Intent(this, Shop_Retailer_Promotion_Event::class.java))

                                    val intent = Intent(this, Shop_Retailer_Promotion_Event::class.java)
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or
                                            Intent.FLAG_ACTIVITY_NEW_TASK)
                                    startActivity(intent)

                                }
                                .addOnFailureListener { e ->
                                    d("firebase", "error ", e)
                                }
                        }
                    }


            }

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PERMISSION_PICK_IMAGE) {
            d("firebase", "$data")
//            d("faris", " ${resultCode}")
//            d("faris", " ${requestCode}")

            filePath = data!!.data

            val fileName = data.data?.lastPathSegment
            val path = fileName?.toUri()?.pathSegments
            val pa = path?.get(path.size -1)
            imgName = pa.toString()

//            d("faris", "path : $fileName")
//            d("faris", "path : $path")
//            d("faris", "path : $pa")
            d("firebase", "path : $imgName")
            descriptionImage.text = pa

        }
    }



}