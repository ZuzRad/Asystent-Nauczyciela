package com.example.asystent.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.asystent.data.uczen.Uczen
import com.example.asystent.data.uczen.UczenDao
import com.example.asystent.data.zajecia.Zajecia
import com.example.asystent.data.zajecia.ZajeciaDao

@Database(entities = [Uczen::class, Zajecia::class], version = 4, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun uczenDao(): UczenDao
    abstract fun zajeciaDao(): ZajeciaDao

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase{
            val temInstance = INSTANCE
            if(temInstance!= null){
                return temInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }



}