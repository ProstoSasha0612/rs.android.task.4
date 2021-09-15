package com.hfad.android.storageapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.hfad.android.storageapp.R
import com.hfad.android.storageapp.databinding.AddHumanFragmentBinding
import com.hfad.android.storageapp.model.Human
import com.hfad.android.storageapp.viewmodel.AddHumanViewModel
import com.hfad.android.storageapp.viewmodel.HumanListFragmentViewModel

class AddHumanFragment : Fragment() {

    private lateinit var binding: AddHumanFragmentBinding
    private val listViewModel: HumanListFragmentViewModel by lazy {
        ViewModelProviders.of(this).get(HumanListFragmentViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AddHumanFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? AppCompatActivity)?.supportActionBar?.title = "Add new person"
        //TODO add navigation

        binding.btnAdd.setOnClickListener {
            if (addToList()) {
                parentFragmentManager.popBackStack()
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, HumanListFragment())
                    .commit()
            }
        }
    }

    private fun addToList(): Boolean {
        val name = binding.edittextName.text.toString()
        val surname = binding.editTextSurname.text.toString()
        val age = binding.editTextAge.text.toString()
        val gender = if (binding.rbMale.isChecked) MALE_GENDER else FEMALE_GENDER

        if (name.isNotEmpty() && surname.isNotEmpty() &&
            age.isNotEmpty() && gender.isNotEmpty()
        ) {
            listViewModel.add(Human(0, name, surname, age.toInt(), gender))
            return true
        } else {
            Snackbar.make(binding.root, "Please, fill in all the fields", Snackbar.LENGTH_LONG)
                .show()
            return false
        }

    }


    companion object {
        const val MALE_GENDER = "Male"
        const val FEMALE_GENDER = "Female"
    }

}