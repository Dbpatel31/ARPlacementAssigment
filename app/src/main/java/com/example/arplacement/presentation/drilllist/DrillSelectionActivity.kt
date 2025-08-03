package com.example.arplacement.presentation.drilllist

import android.content.Intent
import android.os.Bundle
import android.provider.Telephony.Mms.Intents
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.FrameLayout
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.arplacement.R
import com.example.arplacement.domain.model.Drill
import java.util.concurrent.ArrayBlockingQueue

class DrillSelectionActivity : AppCompatActivity() {

    private lateinit var viewModel: DrillViewModel
    private lateinit var selectedDrill: Drill

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_drill_selection)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
        viewModel= ViewModelProvider(this)[DrillViewModel::class.java]
        val spinner: Spinner= findViewById(R.id.spinner)
        val button: Button= findViewById(R.id.btn_start_drill)

        val drills= viewModel.getDrills()
        val adapter= ArrayAdapter(this, android.R.layout.simple_spinner_item, drills.map { it.name })
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter= adapter

        spinner.onItemSelectedListener= object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectedDrill = drills[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        button.setOnClickListener {
         val fragment= DrillDetailsFragment.newInstance(selectedDrill)

            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment).addToBackStack(null).commit()

            findViewById<FrameLayout>(R.id.fragmentContainer).visibility= View.VISIBLE
            val name= fragment.arguments?.getSerializable("drill")
            Log.e("Just Check","$name")
        }

    }
}