package com.reem.goaltrackingproject.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [GoalData::class], version = 1, exportSchema = false)
abstract class GoalLocalDatabase  : RoomDatabase(){

    //TODO GoalDao and companion object
    abstract fun goalDao(): GoalDao

    companion object{

        @Volatile
        private var INSTANCE: GoalLocalDatabase? = null

        fun getDatabase(context: Context): GoalLocalDatabase {
            val tempInstance = INSTANCE

            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GoalLocalDatabase::class.java,
                    "goal_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}