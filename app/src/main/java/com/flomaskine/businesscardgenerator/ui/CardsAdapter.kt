package com.flomaskine.businesscardgenerator.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.flomaskine.businesscardgenerator.databinding.CardItemBinding
import com.flomaskine.businesscardgenerator.model.Card

class CardsAdapter : ListAdapter<Card, CardsAdapter.CardsViewHolder>(DiffCallback()) {

    var listenerShare: (View) -> Unit = {}
    var listenerDelete: (Card) -> Unit = {}



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CardItemBinding.inflate(inflater, parent, false)
        return CardsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CardsViewHolder(private val binding: CardItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Card) {
            binding.tvName.text = item.name
            binding.tvCompany.text = item.company
            binding.tvPhone.text = item.phone
            binding.tvEmail.text = item.email
            binding.cvItem.setCardBackgroundColor(Color.parseColor(item.customBg))
            binding.cvItem.setOnClickListener {
                val builder = AlertDialog.Builder(it.context)
                builder.setTitle("Share or Delete")
                builder.setItems(arrayOf("Share", "Delete")) { _, which ->
                    when (which) {
                        0 -> listenerShare(it)
                        1 -> listenerDelete(item)
                    }
                }
                builder.show()

            }

        }

    }

}

class DiffCallback : DiffUtil.ItemCallback<Card>() {
    override fun areItemsTheSame(oldItem: Card, newItem: Card): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Card, newItem: Card): Boolean {
        return oldItem == newItem
    }
}



