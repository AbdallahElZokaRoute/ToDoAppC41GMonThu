package com.route.todoappc41gmonthu

import android.app.Application
import com.route.todoappc41gmonthu.database.TasksDatabase

class TaskApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        TasksDatabase.init(this)
    }
}
