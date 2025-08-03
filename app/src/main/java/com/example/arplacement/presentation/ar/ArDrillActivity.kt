package com.example.arplacement.presentation.ar

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.arplacement.R
import com.google.ar.core.HitResult
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.Node
import com.google.ar.sceneform.math.Vector3
import com.google.ar.sceneform.rendering.MaterialFactory
import com.google.ar.sceneform.rendering.ShapeFactory
import com.google.ar.sceneform.ux.ArFragment



class ArDrillActivity : AppCompatActivity() {

    private lateinit var arFragment: ArFragment
    private var placedAnchorNode: AnchorNode ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ar_drill)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
        val selectedDrillId = intent.getIntExtra("selected_drill_id", -1)
        arFragment= supportFragmentManager.findFragmentById(R.id.ar_fragment) as ArFragment

        arFragment.setOnTapArPlaneListener { hitResult: HitResult, _, _ ->
            if (placedAnchorNode != null) return@setOnTapArPlaneListener

            val anchor= hitResult.createAnchor()
            val anchorNode= AnchorNode(anchor)
            anchorNode.setParent(arFragment.arSceneView.scene)

            MaterialFactory.makeOpaqueWithColor(
                this,
                when (selectedDrillId) {
                    1 -> com.google.ar.sceneform.rendering.Color(Color.RED)
                    2 -> com.google.ar.sceneform.rendering.Color(Color.GREEN)
                    3 -> com.google.ar.sceneform.rendering.Color(Color.BLUE)
                    else -> com.google.ar.sceneform.rendering.Color(Color.GRAY)
                }
            )
                .thenAccept { material ->
                    val shape = when (selectedDrillId) {
                        1 -> ShapeFactory.makeCube(Vector3(0.1f, 0.1f, 0.1f), Vector3(0f, 0.05f, 0f), material)
                        2 -> ShapeFactory.makeSphere(0.07f, Vector3(0f, 0.07f, 0f), material)
                        3 -> ShapeFactory.makeCylinder(0.05f, 0.1f, Vector3(0f, 0.05f, 0f), material)
                        else -> ShapeFactory.makeCube(Vector3(0.1f, 0.1f, 0.1f), Vector3(0f, 0.05f, 0f), material)
                    }

                    val node = Node().apply {
                        renderable = shape
                        setParent(anchorNode)
                    }
                }
                .exceptionally { throwable ->
                    Log.e("ARCore", "Renderable error: ${throwable.message}")
                    null
                }
        }
    }
}