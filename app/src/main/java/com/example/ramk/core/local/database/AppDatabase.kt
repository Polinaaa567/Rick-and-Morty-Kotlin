package com.example.ramk.core.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ramk.core.local.dao.CharacterDao
import com.example.ramk.core.local.entity.CharacterEntity

@Database(
    entities = [(CharacterEntity::class)],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase: RoomDatabase() {
    abstract fun characterDao(): CharacterDao

    @Volatile
    private var INSTANCE: AppDatabase? = null

    fun getInstance(context: Context): AppDatabase {
        return INSTANCE ?: synchronized(lock = this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                klass = AppDatabase::class.java,
                name = "rickandmorty_database"
            )
                .fallbackToDestructiveMigration()
                .build()

            INSTANCE = instance
            instance
        }
    }
}