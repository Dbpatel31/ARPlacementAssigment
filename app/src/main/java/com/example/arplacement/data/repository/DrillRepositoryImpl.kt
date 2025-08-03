package com.example.arplacement.data.repository

import com.example.arplacement.domain.model.Drill
import com.example.arplacement.domain.repository.DrillRepository

class DrillRepositoryImpl : DrillRepository {
    override fun getDrills(): List<Drill> = listOf(
        Drill(1, "Drill 1", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSW70JzaGxQb5J-WnJB9q4ZdvvOM9WHLTz_Qg&s", "Best for concrete", "Hold tight.","drill_1.glb"),
        Drill(2, "Drill 2", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQc7QKqjhwn04jvZyOU_arloQ9TeSR5HZgk6A&s", "Best for wood", "Apply medium pressure.", "drill_2.glb"),
        Drill(3, "Drill 3", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQiUnQ3aXfTqfdNmMnzyA_vc7CllpfRN0jK4A&s", "Best for metal.", "Use oil","drill_3.glb")
    )
}