package com.hfad.android.storageapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
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
        RecyclerView.ViewHolder(binding.root), View.OnLongClickListener {

        init {
            binding.root.setOnLongClickListener(this)
        }

        var human: Human? = null

        fun bind(human: Human) {
            this.human = human
            binding.ageValue.text = human.age.toString()
            binding.nameValue.text = human.name
            binding.secondNameValue.text = human.secondName
            binding.genderValue.text = human.gender
        }

        override fun onLongClick(v: View?): Boolean {
            Snackbar.make(v ?: binding.root, R.string.hello_text, Snackbar.LENGTH_SHORT).show()
            return true
        }
    }

    companion object {
        private val itemComparator = object : DiffUtil.ItemCallback<Human>() {
            override fun areItemsTheSame(oldItem: Human, newItem: Human): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Human, newItem: Human): Boolean {
                return oldItem == newItem
            }
        }
    }
}
