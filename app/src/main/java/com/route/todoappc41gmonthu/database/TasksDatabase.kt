package com.route.todoappc41gmonthu.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.route.todoappc41gmonthu.database.dao.TasksDao
import com.route.todoappc41gmonthu.database.model.Task


@Database(entities = arrayOf(Task::class), version = 1)
@TypeConverters(Converters::class)
abstract class TasksDatabase : RoomDatabase() {
    abstract fun getTaskDao(): TasksDao

    companion object {
        private var DATABASE_INSTANCE: TasksDatabase? = null
        fun init(applicationContext: Context) {
            if (DATABASE_INSTANCE == null) {
                DATABASE_INSTANCE =
                    Room.databaseBuilder(
                        applicationContext,
                        TasksDatabase::class.java,
                        "Tasks Database"
                    )  // Coroutines
                        .allowMainThreadQueries()          // Worker  -> Main(UI) Thread
                        .fallbackToDestructiveMigration()
                        .build()
                // Migrations
            }
        }

        fun getInstance(): TasksDatabase {
            return DATABASE_INSTANCE!!
        }

    }
}
