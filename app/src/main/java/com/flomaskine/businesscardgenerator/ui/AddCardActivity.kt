package com.flomaskine.businesscardgenerator.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.flomaskine.businesscardgenerator.App
import com.flomaskine.businesscardgenerator.R
import com.flomaskine.businesscardgenerator.databinding.ActivityAddCardBinding
import com.flomaskine.businesscardgenerator.model.Card

class AddCardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddCardBinding
    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddCardBinding.inflate(layoutInflater)

        setContentView(binding.root)

        insertListeners()


    }


    private fun insertListeners() {
        binding.pickColorBtn.setOnClickListener {
            colorPickerDialog()
        }

        binding.ibSaveCard.setOnClickListener {
            val name = binding.etName.editText?.text.toString()
            val phone = binding.etPhone.editText?.text.toString()
            val email = binding.etEmail.editText?.text.toString()
            val company = binding.etCompany.editText?.text.toString()
            val customBg = binding.colorPicked.text.toString()

            val card = Card(
                name = name,
                phone = phone,
                email = email,
                company = company,
                customBg = customBg,
            )
            if (card.name.isEmpty() || card.phone.isEmpty() || card.email.isEmpty() || card.company.isEmpty()) {
                Toast.makeText(
                    this,
                    getString(R.string.fill_all_fields_warning),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                mainViewModel.insertCard(card)
                Toast.makeText(this, getString(R.string.card_added_success), Toast.LENGTH_SHORT)
                    .show()

                finish()
            }

        }


    }

    private fun colorPickerDialog() {
        val colors = arrayOf(
            "#FF7F50", "#FF69B4", "#ADFF2F", "#8B008B", "#4B0082", "#FFA500", "#FFD700", "#FFFF00",
            "#00FF00", "#008000", "#00FFFF", "#00BFFF", "#0000FF", "#FF00FF", "#FF0000", "#8B0000",
        )

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Choose a color")
            .setItems(colors) { _, which ->
                binding.colorPicked.text = colors[which]
                binding.pickColorBtn.setColorFilter(
                    android.graphics.Color.parseColor(colors[which])
                )
            }
            .create().show()

    }


}