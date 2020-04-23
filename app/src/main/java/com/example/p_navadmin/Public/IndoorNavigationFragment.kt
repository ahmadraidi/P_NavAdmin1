package com.example.p_navadmin.Public


import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.example.p_navadmin.R
import com.github.chrisbanes.photoview.PhotoView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.getField
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_indoor_navigation.*


/**
 * A simple [Fragment] subclass.
 */
class IndoorNavigationFragment : Fragment(), AdapterView.OnItemSelectedListener {
    var shopFrom = ""
    var shopTo = ""
    val db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_indoor_navigation, container, false)

        val spinner_from = root.findViewById<Spinner>(R.id.spinnerFrom)
        val spinner_to = root.findViewById<Spinner>(R.id.spinnerTo)
        spinner_from.onItemSelectedListener = this
        spinner_to.onItemSelectedListener = this

        val buttonG = root.findViewById<Button>(R.id.buttonG)
        val buttonM = root.findViewById<Button>(R.id.buttonM)
        val button1 = root.findViewById<Button>(R.id.button1)
        val button2 = root.findViewById<Button>(R.id.button2)
        val button3 = root.findViewById<Button>(R.id.button3)
        val photoView = root.findViewById<PhotoView>(R.id.photoView)

        // default floor
        var floor = 'g'

        // store list for the spinner
        val subjects = ArrayList<String>()

        val adapter = ArrayAdapter(
            root.context.applicationContext,
            android.R.layout.simple_spinner_item,
            subjects
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_from.setAdapter(adapter)
        spinner_to.setAdapter(adapter)

        fun getlist(floor: Char) {
            subjects.clear()
            db.collection("Store")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        d("spinner", "id: ${document.id} , field: ${document.getField<String>("lot")} ")
                        val field =document.getField<String>("lot")
                        if (field!!.contains(floor)) {
                            subjects.add(document.id)
                        }
                    }
                    adapter.notifyDataSetChanged()
                }
        }

        // default call
        getlist(floor)
        // show image on photoview
        val imgName = "main_image"
        val id = resources.getIdentifier(imgName, "drawable", context?.packageName)
        photoView.setImageResource(id)

        buttonG.setOnClickListener {
            // change floor to m
            floor = 'g'

            buttonG.setBackgroundResource(R.drawable.common_google_signin_btn_icon_dark_normal_background)
            buttonM.setBackgroundResource(R.drawable.common_google_signin_btn_icon_light_normal_background)
            button1.setBackgroundResource(R.drawable.common_google_signin_btn_icon_light_normal_background)
            button2.setBackgroundResource(R.drawable.common_google_signin_btn_icon_light_normal_background)
            button3.setBackgroundResource(R.drawable.common_google_signin_btn_icon_light_normal_background)

            // recall the floor spinner
            getlist(floor)

            // show image on photoview
            val imgName = "g_floor"
            val id = resources.getIdentifier(imgName, "drawable", context?.packageName)
            photoView.setImageResource(id)

        }

        buttonM.setOnClickListener {
            // change floor to m
            floor = 'm'

            buttonG.setBackgroundResource(R.drawable.common_google_signin_btn_icon_light_normal_background)
            buttonM.setBackgroundResource(R.drawable.common_google_signin_btn_icon_dark_normal_background)
            button1.setBackgroundResource(R.drawable.common_google_signin_btn_icon_light_normal_background)
            button2.setBackgroundResource(R.drawable.common_google_signin_btn_icon_light_normal_background)
            button3.setBackgroundResource(R.drawable.common_google_signin_btn_icon_light_normal_background)

            // recall the floor spinner
            getlist(floor)

            // show image on photoview
            val imgName = "m_floor"
            val id = resources.getIdentifier(imgName, "drawable", context?.packageName)
            photoView.setImageResource(id)

        }

        button1.setOnClickListener {
            // change floor to f
            floor = 'f'

            buttonG.setBackgroundResource(R.drawable.common_google_signin_btn_icon_light_normal_background)
            buttonM.setBackgroundResource(R.drawable.common_google_signin_btn_icon_light_normal_background)
            button1.setBackgroundResource(R.drawable.common_google_signin_btn_icon_dark_normal_background)
            button2.setBackgroundResource(R.drawable.common_google_signin_btn_icon_light_normal_background)
            button3.setBackgroundResource(R.drawable.common_google_signin_btn_icon_light_normal_background)

            // recall the floor spinner
            getlist(floor)

            // show image on photoview
            val imgName = "f_floor"
            val id = resources.getIdentifier(imgName, "drawable", context?.packageName)
            photoView.setImageResource(id)

        }

        button2.setOnClickListener {
            // change floor to f
            floor = 's'

            buttonG.setBackgroundResource(R.drawable.common_google_signin_btn_icon_light_normal_background)
            buttonM.setBackgroundResource(R.drawable.common_google_signin_btn_icon_light_normal_background)
            button1.setBackgroundResource(R.drawable.common_google_signin_btn_icon_light_normal_background)
            button2.setBackgroundResource(R.drawable.common_google_signin_btn_icon_dark_normal_background)
            button3.setBackgroundResource(R.drawable.common_google_signin_btn_icon_light_normal_background)

            // recall the floor spinner
            getlist(floor)

            // show image on photoview
            val imgName = "s_floor"
            val id = resources.getIdentifier(imgName, "drawable", context?.packageName)
            photoView.setImageResource(id)

        }

        button3.setOnClickListener {
            // change floor to f
            floor = 't'

            buttonG.setBackgroundResource(R.drawable.common_google_signin_btn_icon_light_normal_background)
            buttonM.setBackgroundResource(R.drawable.common_google_signin_btn_icon_light_normal_background)
            button1.setBackgroundResource(R.drawable.common_google_signin_btn_icon_light_normal_background)
            button2.setBackgroundResource(R.drawable.common_google_signin_btn_icon_light_normal_background)
            button3.setBackgroundResource(R.drawable.common_google_signin_btn_icon_dark_normal_background)

            // recall the floor spinner
            getlist(floor)

            // show image on photoview
            val imgName = "t_floor"
            val id = resources.getIdentifier(imgName, "drawable", context?.packageName)
            photoView.setImageResource(id)

        }


        return root
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
        d("spinner", "${parent.id}  selected ${parent.getItemAtPosition(pos)}")

        if (parent.id == spinnerFrom.id) {
            d("spinner", "spinner from selected")
            shopFrom = parent.getItemAtPosition(pos).toString()

            if(shopTo.isNotEmpty() && shopFrom != shopTo) {

                var navMap = ""

                db.collection("Store").document("$shopFrom")
                    .get()
                    .addOnSuccessListener { result ->
                        val fromS = result.getField<String>("lot")
                        db.collection("Store").document("$shopTo")
                            .get()
                            .addOnSuccessListener { result ->
                                val toS = result.getField<String>("lot")
                                navMap = fromS+"_"+toS
                                d("spinner", " 1 : $navMap")

                                // show image on photoview
                                val imgName = navMap
                                val id = resources.getIdentifier(imgName, "drawable", context?.packageName)
                                photoView.setImageResource(id)

                            }

                    }
            }
        }
        else {
            d("spinner", "spinner to selected")
            shopTo = parent.getItemAtPosition(pos).toString()

            if(shopFrom.isNotEmpty() && shopFrom != shopTo) {

                var navMap = ""

                db.collection("Store").document("$shopFrom")
                    .get()
                    .addOnSuccessListener { result ->
                        val fromS = result.getField<String>("lot")
                        db.collection("Store").document("$shopTo")
                            .get()
                            .addOnSuccessListener { result ->
                                val toS = result.getField<String>("lot")
                                navMap = fromS+"_"+toS
                                d("spinner2", " 1 : $navMap")

                                val imgName = navMap
                                val id = resources.getIdentifier(imgName, "drawable", context?.packageName)
                                photoView.setImageResource(id)

                            }

                    }
            }
        }
    }


}
