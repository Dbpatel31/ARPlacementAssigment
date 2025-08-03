package com.example.arplacement.presentation.drilllist

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.arplacement.R
import com.example.arplacement.domain.model.Drill
import com.example.arplacement.presentation.ar.ArDrillActivity


class DrillDetailsFragment : Fragment() {

    private lateinit var  drill: Drill

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            drill= it.getSerializable("drill") as Drill
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_drill_details, container, false)

        val drillImage= view.findViewById<ImageView>(R.id.drillImage)
        val drillDescription= view.findViewById<TextView>(R.id.drillDescription)
        val drillTips= view.findViewById<TextView>(R.id.drillTips)
        val startARButton= view.findViewById<Button>(R.id.btn_start_ar)

       Glide.with(requireContext()).load(drill.imageUrl).into(drillImage)
        drillDescription.text= drill.description
        drillTips.text= "Tips: ${drill.tips}"

        startARButton.setOnClickListener {
            val intent= Intent(requireContext(), ArDrillActivity::class.java)
            intent.putExtra("selected_drill_id", drill.id)
            startActivity(intent)
        }
        return view
    }

    companion object {
        fun  newInstance(drill:Drill): DrillDetailsFragment{
            val fragment= DrillDetailsFragment()
            val args= Bundle()
            args.putSerializable("drill", drill)
            fragment.arguments= args
            return fragment
        }
    }
}