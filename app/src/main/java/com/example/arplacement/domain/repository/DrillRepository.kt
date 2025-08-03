package com.example.arplacement.domain.repository

import com.example.arplacement.domain.model.Drill

interface DrillRepository {
    fun getDrills() : List<Drill>
}