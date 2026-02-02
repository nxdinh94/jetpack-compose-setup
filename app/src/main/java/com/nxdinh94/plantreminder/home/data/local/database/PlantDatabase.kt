package com.nxdinh94.plantreminder.home.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nxdinh94.plantreminder.home.data.local.dao.PlantDao
import com.nxdinh94.plantreminder.home.data.local.entity.PlantEntity


@Database(
    entities = [PlantEntity::class],
    version = 1,
    exportSchema = true
)
abstract class PlantDatabase : RoomDatabase() {
    abstract fun plantDao(): PlantDao

    companion object {
        private const val DATABASE_NAME = "plant_reminder_db"

        @Volatile
        private var INSTANCE: PlantDatabase? = null

        fun getInstance(context: Context): PlantDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PlantDatabase::class.java,
                    DATABASE_NAME
                )
                    .fallbackToDestructiveMigration(false)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
