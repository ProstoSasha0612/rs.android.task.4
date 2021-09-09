package com.hfad.android.storageapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hfad.android.storageapp.databinding.HumanItemBinding
import com.hfad.android.storageapp.model.Human


class HumanAdapter :
    ListAdapter<Human, HumanAdapter.HumanViewHolder>(
        itemComparator
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HumanViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = HumanItemBinding.inflate(layoutInflater)
        return HumanViewHolder(binding)

    }

    override fun onBindViewHolder(holder: HumanViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class HumanViewHolder(private val binding: HumanItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(human: Human) {
            binding.age.text = "Age: ${human.age}"
            binding.name.text = "Name: ${human.name}"
            binding.secondName.text = "Surname: ${human.secondName}"
            binding.gender.text = "Gender: ${human.gender}"
        }
    }

    companion object {
        private val itemComparator = object : DiffUtil.ItemCallback<Human>() {
            override fun areItemsTheSame(oldItem: Human, newItem: Human): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Human, newItem: Human): Boolean {
                return oldItem.age == newItem.age && oldItem.name == newItem.name &&
                        oldItem.secondName == newItem.secondName &&
                        oldItem.gender == newItem.gender
            }
        }
    }
}
