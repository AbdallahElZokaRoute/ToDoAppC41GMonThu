package com.route.todoappc41gmonthu.database.design_patterns

// Singleton

object AppConstants {

}

val appConstant1 = AppConstants  // 123456
val appConstant2 = AppConstants  // 123456

// Design Patters

class Database private constructor() {
    companion object {
        private var instance: Database? = null
        fun getInstance(): Database {
            if (instance == null) {
                instance = Database()
            }
            return instance!!

        }
    }
}


val database1 = Database.getInstance() // 123456
val database2 = Database.getInstance() // 123456

