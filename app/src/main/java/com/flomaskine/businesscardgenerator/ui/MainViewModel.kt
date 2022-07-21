package com.flomaskine.businesscardgenerator.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.flomaskine.businesscardgenerator.data.repo.CardsRepository
import com.flomaskine.businesscardgenerator.model.Card

class MainViewModel(private val cardsRepository: CardsRepository) : ViewModel() {

    fun insertCard(card: Card) {
        cardsRepository.insert(card)
    }

    fun getAll() : LiveData<List<Card>> {
        return cardsRepository.getAll()
    }

    fun delete(card: Card) {
        cardsRepository.delete(card)
    }

}

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val cardsRepository: CardsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(cardsRepository) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }

    }
}