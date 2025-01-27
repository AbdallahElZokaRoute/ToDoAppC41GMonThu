package com.route.todoappc41gmonthu.database.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null, //
    val title: String? = null,
    val date: Date? = null,
    var isDone: Boolean? = false,
) {
    @Ignore
    var description: String? = null
}

val task = Task(title = "Play Basketball", date = Date(), isDone = true)
