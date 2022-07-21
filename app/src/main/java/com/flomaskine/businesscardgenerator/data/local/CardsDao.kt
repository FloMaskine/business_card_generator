package com.flomaskine.businesscardgenerator.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.flomaskine.businesscardgenerator.model.Card

@Dao
interface CardsDao {

    @Query("SELECT * FROM business_cards")
    fun getAll(): LiveData<List<Card>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(card: Card)

    @Delete
    fun delete(card: Card)


}