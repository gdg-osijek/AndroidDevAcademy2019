package osc.ada.terezijaumiljanovic.bestpizzas.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey

@Entity
data class Pizza(
    @PrimaryKey (autoGenerate = true)
    var pizzaDbId : Long? = null,
    var id: Int = 0,
    val type: String,
    val place: String,
    val price: Int,
    var grade: Int
)