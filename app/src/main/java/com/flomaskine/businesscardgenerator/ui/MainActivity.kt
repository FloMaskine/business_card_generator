package com.flomaskine.businesscardgenerator.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.flomaskine.businesscardgenerator.App
import com.flomaskine.businesscardgenerator.R
import com.flomaskine.businesscardgenerator.databinding.ActivityMainBinding
import com.flomaskine.businesscardgenerator.util.Image

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }
    private val mainAdapter: CardsAdapter by lazy { CardsAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvCards.adapter = mainAdapter
        getAllBusinessCards()
        insertListeners()


    }

    private fun getAllBusinessCards() {
        mainViewModel.getAll().observe(this) { cardsList ->
            mainAdapter.submitList(cardsList)
        }
    }

    private fun insertListeners() {
        binding.fabGenerateCard.setOnClickListener {
            val intent = Intent(this@MainActivity, AddCardActivity::class.java)
            startActivity(intent)
        }

        mainAdapter.listenerShare = { card ->
            Image.share(this@MainActivity, card)
        }
        mainAdapter.listenerDelete = { card ->
            mainViewModel.delete(card)
            Toast.makeText(this@MainActivity, getString(R.string.card_deleted_label), Toast.LENGTH_SHORT).show()
        }
    }


}