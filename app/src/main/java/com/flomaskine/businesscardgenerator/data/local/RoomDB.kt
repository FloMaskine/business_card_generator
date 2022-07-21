package com.flomaskine.businesscardgenerator.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.flomaskine.businesscardgenerator.model.Card

@Database(entities = [Card::class], version = 1)
abstract class RoomDB: RoomDatabase() {
    abstract fun cardsDao(): CardsDao

    companion object {
        @Volatile
        private var INSTANCE: RoomDB? = null

        fun getDatabase(context: Context): RoomDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RoomDB::class.java,
                    "cards_database"
                ).build()
                INSTANCE = instance
                instance

            }
        }
    }
}
