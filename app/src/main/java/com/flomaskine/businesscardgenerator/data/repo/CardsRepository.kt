package com.flomaskine.businesscardgenerator.data.repo

import com.flomaskine.businesscardgenerator.data.local.CardsDao
import com.flomaskine.businesscardgenerator.model.Card
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class CardsRepository(private val cardsDao: CardsDao) {

    fun insert(card: Card) = runBlocking {
        launch(Dispatchers.IO) {
            cardsDao.insert(card)
        }
    }

    fun getAll() = cardsDao.getAll()

    fun delete(card: Card) = runBlocking {
        launch(Dispatchers.IO) {
            cardsDao.delete(card)
        }
    }

}





