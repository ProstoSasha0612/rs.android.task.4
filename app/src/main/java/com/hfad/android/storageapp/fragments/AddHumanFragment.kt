package com.hfad.android.storageapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.hfad.android.storageapp.R
import com.hfad.android.storageapp.databinding.AddHumanFragmentBinding
import com.hfad.android.storageapp.model.Human
import com.hfad.android.storageapp.viewmodel.AddHumanViewModel
import com.hfad.android.storageapp.viewmodel.HumanListFragmentViewModel

class AddHumanFragment : Fragment() {

    private lateinit var binding: AddHumanFragmentBinding
    private val viewModel: AddHumanViewModel by lazy {
        ViewModelProviders.of(this).get(AddHumanViewModel::class.java)
    }
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
        (activity as? AppCompatActivity)?.supportActionBar?.setDisplayHomeAsUpEnabled(true) //TODO add navigation

        binding.btnAdd.setOnClickListener {


            if(addToList()){
                parentFragmentManager.popBackStack()
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, HumanListFragment())
                    .commit()
            }
        }

//        if(savedInstanceState !=null){ TODO разобраться почему оно все равно сохраняется
//            with(binding){
//                edittextName.setText(viewModel.name)
////                editTextSurname.setText(viewModel.surname)
//                editTextAge.setText(viewModel.age.toString())
//                if(viewModel.gender == MALE_GENDER){
//                    rbMale.isChecked = true
//                } else {
//                    rbFemale.isChecked = true
//                }
//            }
//
//        }
    }

    private fun addToList():Boolean {
//        with(viewModel){TODO разобраться почему оно все равно сохраняется PART2
//            name = binding.edittextName.text.toString()
//            surname = binding.editTextSurname.text.toString()
//            age = binding.editTextAge.text.toString().toInt()
//            gender = if(binding.rbMale.isChecked) MALE_GENDER else FEMALE_GENDER
//
//        }
        val id = viewModel.lastId++
        val name = binding.edittextName.text.toString()
        val surname = binding.editTextSurname.text.toString()
        val age = binding.editTextAge.text.toString()
        val gender = if (binding.rbMale.isChecked) MALE_GENDER else FEMALE_GENDER

        if (name.isNotEmpty() && surname.isNotEmpty() &&
            age.isNotEmpty() && gender.isNotEmpty()
        ) {
            listViewModel.add(Human(id, name, surname, age.toInt(), gender))
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