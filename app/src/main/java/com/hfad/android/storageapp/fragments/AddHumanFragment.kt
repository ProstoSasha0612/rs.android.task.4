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
import com.hfad.android.storageapp.viewmodel.HumanListFragmentViewModel

class AddHumanFragment : Fragment() {

    private var _binding: AddHumanFragmentBinding? = null
    private val binding
        get() = requireNotNull(_binding)
    private val listViewModel: HumanListFragmentViewModel by lazy {
        ViewModelProviders.of(this).get(HumanListFragmentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AddHumanFragmentBinding.inflate(layoutInflater)
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
            Snackbar.make(
                binding.root,
                getString(R.string.fill_in_all_the_fields_text),
                Snackbar.LENGTH_LONG
            )
                .show()
            return false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    //Yes, i know that better add genders to enum or sealed class, but i need much logic changes,
    //and it won't bring new functionality, therefore i remember this for all my new projects
    companion object {
        const val MALE_GENDER = "Male"
        const val FEMALE_GENDER = "Female"
    }


}