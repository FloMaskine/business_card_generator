package com.flomaskine.businesscardgenerator

import android.app.Application
import com.flomaskine.businesscardgenerator.data.local.RoomDB
import com.flomaskine.businesscardgenerator.data.repo.CardsRepository

class App: Application() {
    val database by lazy { RoomDB.getDatabase(this) }
    val repository by lazy { CardsRepository(database.cardsDao()) }
}
