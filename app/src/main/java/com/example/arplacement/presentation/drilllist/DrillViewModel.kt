package com.example.arplacement.presentation.drilllist

import androidx.lifecycle.ViewModel
import com.example.arplacement.data.repository.DrillRepositoryImpl
import com.example.arplacement.domain.model.Drill

class DrillViewModel : ViewModel() {
    private val repository= DrillRepositoryImpl()

    fun getDrills(): List<Drill> = repository.getDrills()
}